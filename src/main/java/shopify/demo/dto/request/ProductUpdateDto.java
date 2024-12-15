package shopify.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateDto {
    private String brand;

    private String model;

    private UUID socket;

    private Double fanSize;

    private Integer fanSpeed;

    private Double fanNoiseLevel;

    private Integer numberOfFans;

    private Double price;
}
