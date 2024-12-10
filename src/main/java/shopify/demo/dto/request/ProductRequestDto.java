package shopify.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {
    private String brand;
    private String model;
    private String socket;
    private Double fanSize;
    private Integer fanSpeed;
    private Double fanNoiseLevel;
    private Integer numberOfFans;
    private Double price;
}
