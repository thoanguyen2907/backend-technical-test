package shopify.demo.product;

import shopify.demo.dto.request.ProductRequestDto;
import shopify.demo.dto.response.ProductResponseDto;
import shopify.demo.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class ProductTestApi {

    public static ProductEntity makeProductForSaving(final ProductRequestDto productRequest) {
        return ProductEntity.builder()
                    .id(UUID.fromString("86286271-7c2b-4fad-9125-a32e2ec9dc7c"))
                    .brand(productRequest.getBrand())
                    .model(productRequest.getModel())
                    .socket(productRequest.getSocket())
                    .fanSize(productRequest.getFanSize())
                    .fanSpeed(productRequest.getFanSpeed())
                    .fanNoiseLevel(productRequest.getFanNoiseLevel())
                    .numberOfFans(productRequest.getNumberOfFans())
                    .price(productRequest.getPrice())
                    .build();
        }
    public static ProductEntity makeProductResponse(final ProductRequestDto productRequest) {
        return ProductEntity.builder()
                .id(UUID.fromString("86286271-7c2b-4fad-9125-a32e2ec9dc7c"))
                .brand(productRequest.getBrand())
                .model(productRequest.getModel())
                .socket(productRequest.getSocket())
                .fanSize(productRequest.getFanSize())
                .fanSpeed(productRequest.getFanSpeed())
                .fanNoiseLevel(productRequest.getFanNoiseLevel())
                .numberOfFans(productRequest.getNumberOfFans())
                .price(productRequest.getPrice())
                .build();
    }
    public static ProductRequestDto prepareProductRequest() {
        return ProductRequestDto.builder()
                .brand("Noctua")
                .model("NH-D15")
                .socket("AM4")
                .fanSize(140.0)
                .fanSpeed(1500)
                .fanNoiseLevel(24.6)
                .numberOfFans(2)
                .price(89.99)
                .build();
    }


    public static List<ProductEntity> createMockProductList() {
        List<ProductEntity> products = new ArrayList<>();
        ProductEntity productFirst = ProductEntity.builder()
                .id(UUID.fromString("86286271-7c2b-4fad-9125-a32e2ec9dc7c"))
                .brand("CoolerMaster")
                .model("Hyper 212")
                .socket("LGA1700")
                .fanSize(120.0)
                .fanSpeed(2000)
                .fanNoiseLevel(24.0)
                .numberOfFans(1)
                .price(39.99)
                .build();

        ProductEntity productSecond = ProductEntity.builder()
                .id(UUID.fromString("17326271-9a2b-4def-8115-b72e2ec9dc7c"))
                .brand("Noctua")
                .model("NH-D15")
                .socket("AM4")
                .fanSize(140.0)
                .fanSpeed(1500)
                .fanNoiseLevel(19.2)
                .numberOfFans(2)
                .price(89.99)
                .build();

        ProductEntity productThird = ProductEntity.builder()
                .id(UUID.fromString("76286271-5a3b-4cad-9235-b32e2ec9dc7c"))
                .brand("Arctic")
                .model("Freezer 34")
                .socket("AM4")
                .fanSize(120.0)
                .fanSpeed(1800)
                .fanNoiseLevel(22.5)
                .numberOfFans(2)
                .price(54.99)
                .build();

        products.add(productFirst);
        products.add(productSecond);
        products.add(productThird);

        return products;

    }
}