package shopify.demo.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopify.demo.controller.route.CommonRoute;
import shopify.demo.controller.route.ProductRoute;

import shopify.demo.dto.request.ProductRequestDto;
import shopify.demo.dto.request.ProductUpdateDto;
import shopify.demo.service.IProductService;
import shopify.demo.shared.ResponseEntityBuilder;

import java.util.UUID;


@RestController
@RequestMapping(CommonRoute.BASE_API + CommonRoute.VERSION)
public class ProductController {
    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    @GetMapping(ProductRoute.BASE_URL)
    public ResponseEntity<?> getAllProducts (
            @RequestParam(name = "offset", defaultValue = "0") final Integer offset,
            @RequestParam(name = "limit", defaultValue = "5") final Integer limit) {
        var pageable = PageRequest.of(offset, limit);
        var productList = productService.getAllProducts(pageable);
        return ResponseEntityBuilder
                .getBuilder()
                .setDetails(productList)
                .build();
    }

    @PostMapping(ProductRoute.BASE_URL)
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        var productResponse = productService.createProduct(productRequestDto);
        return ResponseEntity.ok(productResponse);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(ProductRoute.BASE_URL + "/{productId}")
    public ResponseEntity<?> findOneProduct (@PathVariable final UUID productId) {
        var productResponse = productService.findProductById(productId);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping(ProductRoute.BASE_URL + "/{productId}")
    public ResponseEntity<?> deleteOneProduct (@PathVariable final UUID productId) { productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PatchMapping(ProductRoute.BASE_URL + "/{productId}")
    public ResponseEntity<?> updateOneProduct (@PathVariable final UUID productId, @RequestBody ProductUpdateDto productUpdateDto) {
        var productResponse =  productService.updateProduct(productId, productUpdateDto);
        return ResponseEntity.ok(productResponse);
    }
}
