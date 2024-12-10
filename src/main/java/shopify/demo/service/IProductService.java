package shopify.demo.service;

import org.springframework.data.domain.Pageable;
import shopify.demo.dto.request.ProductRequestDto;
import shopify.demo.dto.response.ProductResponseDto;
import shopify.demo.shared.PageList;

import java.util.UUID;

public interface IProductService {
    PageList<ProductResponseDto> getAllProducts(Pageable pageable);
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);
    ProductResponseDto findProductById(UUID productId);
}
