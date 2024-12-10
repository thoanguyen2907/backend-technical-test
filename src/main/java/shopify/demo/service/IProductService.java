package shopify.demo.service;

import org.springframework.data.domain.Pageable;
import shopify.demo.dto.response.ProductResponseDto;
import shopify.demo.shared.PageList;

public interface IProductService {
    PageList<ProductResponseDto> getAllProducts(Pageable pageable);
}
