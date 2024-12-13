package shopify.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shopify.demo.dto.request.ProductRequestDto;
import shopify.demo.dto.request.ProductUpdateDto;
import shopify.demo.dto.response.ProductResponseDto;
import shopify.demo.exception.ErrorCode;
import shopify.demo.exception.ShopifyRuntimeException;
import shopify.demo.mapper.ProductMapper;
import shopify.demo.model.entity.SocketEntity;
import shopify.demo.repository.ProductRepository;
import shopify.demo.repository.SocketRepository;
import shopify.demo.service.IProductService;
import shopify.demo.shared.PageList;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SocketRepository socketRepository;

    @Override
    public PageList<ProductResponseDto> getAllProducts(Pageable pageable) {
        var productList = productRepository.findAll(pageable);
        var productResponseList = productList.getContent()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
        logger.info("Get all products successfully");
        return buildPaginatingResponse(productResponseList, pageable.getPageSize(), pageable.getPageNumber(), productList.getTotalElements());
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        var product = productMapper.toProductEntity(productRequestDto);
        SocketEntity socket = socketRepository.findById(productRequestDto.getSocket())
                .orElseThrow(() -> new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND)
                );
        product.setSocket(socket);
         var createdProduct = productRepository.save(product);
        logger.info("Create product successfully");

        return productMapper.toProductResponse(createdProduct);
    }

    @Override
    public ProductResponseDto findProductById(UUID productId) throws ShopifyRuntimeException {
        var foundProduct = productRepository.findById(productId).orElse(null);
            if(foundProduct == null) {
                throw new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND);
            }
            logger.info("Find product successfully");
        return productMapper.toProductResponse(foundProduct);
    }

    @Override
    public void deleteProduct(UUID productId) throws ShopifyRuntimeException {
        var foundProduct = productRepository.findById(productId).orElse(null);
        if(foundProduct == null) {
            throw new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND);
        }
        productRepository.delete(foundProduct);
        logger.info("Delete product successfully");
    }

    @Override
    public ProductResponseDto updateProduct(UUID productId, ProductUpdateDto productUpdateDto) throws ShopifyRuntimeException {

        var foundProduct = productRepository.findById(productId).orElse(null);
        if(foundProduct == null) {
            throw new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND);
        }
        if(foundProduct.getSocket().getId() != productUpdateDto.getSocket()) {
            SocketEntity socket = socketRepository.findById(productUpdateDto.getSocket())
                    .orElseThrow(() -> new ShopifyRuntimeException(ErrorCode.ID_NOT_FOUND)
                    );
            foundProduct.setSocket(socket);
        }
        productMapper.updateProductEntityFromProductUpdate(productUpdateDto, foundProduct);

        var updatedProduct = productRepository.save(foundProduct);
        return productMapper.toProductResponse(updatedProduct);
    }

    private PageList<ProductResponseDto> buildPaginatingResponse(final List<ProductResponseDto> responses,
                                                               final int pageSize,
                                                               final int currentPage,
                                                               final long total) {
        return PageList.<ProductResponseDto>builder()
                .records(responses)
                .limit(pageSize)
                .offset(currentPage)
                .totalRecords(total)
                .totalPage((int) Math.ceil(total * 1.0 / pageSize))
                .build();
    }
}
