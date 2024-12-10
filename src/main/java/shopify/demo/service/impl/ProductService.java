package shopify.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shopify.demo.dto.response.ProductResponseDto;
import shopify.demo.mapper.ProductMapper;
import shopify.demo.repository.ProductRepository;
import shopify.demo.service.IProductService;
import shopify.demo.shared.PageList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

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
