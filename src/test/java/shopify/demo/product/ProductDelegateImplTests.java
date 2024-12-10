package shopify.demo.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import shopify.demo.dto.request.ProductRequestDto;
import shopify.demo.dto.response.ProductResponseDto;
import shopify.demo.exception.ErrorCode;
import shopify.demo.exception.ShopifyRuntimeException;
import shopify.demo.mapper.ProductMapper;
import shopify.demo.model.entity.ProductEntity;
import shopify.demo.repository.ProductRepository;
import shopify.demo.shared.PageList;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static shopify.demo.product.ProductTestApi.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ProductDelegateImplTests {
    private static final String API_URL = "/api/v1/products";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void givenValidProductData_whenCreatingNewProduct_thenReturnProductResponse() throws Exception {
        var productRequest = ProductRequestDto.builder()
                .brand("Noctua")
                .model("NH-D15")
                .socket("AM4")
                .fanSize(140.0)
                .fanSpeed(1500)
                .fanNoiseLevel(24.6)
                .numberOfFans(2)
                .price(89.99)
                .build();
        ProductEntity savedProductEntity = makeProductForSaving(productRequest);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(savedProductEntity);
        var expectedProductResponse = productMapper.toProductResponse(savedProductEntity);

        mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
                .content(objectMapper.writeValueAsString(prepareProductRequest()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedProductResponse.getId().toString()))
                .andExpect(jsonPath("$.brand").value(expectedProductResponse.getBrand()));
    }
    @Test
    public void givenProductId_whenFindProduct_thenReturnProductResponse() throws Exception {
        var productRequest = ProductRequestDto.builder()
                .brand("Noctua")
                .model("NH-D15")
                .socket("AM4")
                .fanSize(140.0)
                .fanSpeed(1500)
                .fanNoiseLevel(24.6)
                .numberOfFans(2)
                .price(89.99)
                .build();

        var mockProductId =  UUID.fromString("86286271-7c2b-4fad-9125-a32e2ec9dc7c");

        when(productRepository.findById(mockProductId)).thenReturn(Optional.of(makeProductForSaving(productRequest)));

        var expectedProductResponse = productMapper.toProductResponse(makeProductResponse(productRequest));

        mockMvc.perform(MockMvcRequestBuilders.get(API_URL + "/" + mockProductId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedProductResponse.getId().toString()));
    }
    @Test
    public void givenInvalidProductId_whenGetProductById_thenThrowException() throws Exception {
        UUID productIdRandom = UUID.randomUUID();

        when(productRepository.findById(productIdRandom))
                .thenThrow(new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.get(API_URL + "/" + productIdRandom)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.details.message").value("Could not find the Id"));;
    }

    @Test
    public void givenProductUpdate_whenUpdatingProduct_thenReturnUpdatedProduct() throws Exception {
        var mockProductId =  UUID.fromString("86286271-7c2b-4fad-9125-a32e2ec9dc7c");
        var productRequest = ProductRequestDto.builder()
                .brand("Noctua")
                .model("NH-D15")
                .socket("AM4")
                .fanSize(140.0)
                .fanSpeed(1500)
                .fanNoiseLevel(24.6)
                .numberOfFans(2)
                .price(89.99)
                .build();
        when(productRepository.findById(mockProductId)).thenReturn(Optional.of(makeProductForSaving(productRequest)));

        var updateProduct = makeProductForSaving(productRequest);
        updateProduct.setPrice(50.00);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(updateProduct);

        var expectedProductResponse = productMapper.toProductResponse(updateProduct);

        mockMvc.perform(MockMvcRequestBuilders.patch(API_URL + "/" + mockProductId)

        .content(objectMapper.writeValueAsString(updateProduct))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedProductResponse.getPrice()));

    }

    @Test
    public void givenRequestForListProduct_whenRequestListProduct_thenReturnsListProduct() throws Exception {
        int offset = 0;
        int limit = 10;
        var productList = createMockProductList();
        var productResponseList = createMockProductResponseList();
        var totalRecords = productList.size();
        Pageable pageable = PageRequest.of(offset, limit);
        Page<ProductEntity> page = new PageImpl<>(productList, pageable, totalRecords);

        var productPageList = PageList.<ProductResponseDto>builder()
                .records(productResponseList)
                .limit(pageable.getPageSize())
                .offset(pageable.getPageNumber())
                .totalRecords(totalRecords)
                .totalPage((int) Math.ceil(totalRecords * 1.0 / pageable.getPageSize()))
                .build();

        when(productRepository.findAll(pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get(API_URL)
                .param("offset", String.valueOf(offset))
                .param("limit", String.valueOf(limit))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.details.totalRecords", is(totalRecords)))
                .andExpect(jsonPath("$.details.records[0].brand",
                        is(productPageList.getRecords().get(0).getBrand())))
                .andExpect(jsonPath("$.details.records[1].brand",
                        is(productPageList.getRecords().get(1).getBrand())));
    }
}
