package shopify.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopify.demo.controller.route.CommonRoute;
import shopify.demo.controller.route.ProductRoute;

import shopify.demo.dto.request.ProductRequestDto;
import shopify.demo.service.IProductService;
import shopify.demo.shared.ResponseEntityBuilder;


@RestController
@RequestMapping(CommonRoute.BASE_API + CommonRoute.VERSION)
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping(ProductRoute.BASE_URL)
    public ResponseEntity<?> getAllProducts (@RequestParam(name = "offset", defaultValue = "0") final Integer offset,
                                                                    @RequestParam(name = "limit", defaultValue = "10") final Integer limit) {
        var pageable = PageRequest.of(offset, limit);
        var productList = productService.getAllProducts(pageable);
        return ResponseEntityBuilder
                .getBuilder()
                .setDetails(productList)
                .build();

    }
    @PostMapping(ProductRoute.BASE_URL)
    public ResponseEntity<?> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        var productResponse = productService.createProduct(productRequestDto);
        return ResponseEntity.ok(productResponse);
    }
}
