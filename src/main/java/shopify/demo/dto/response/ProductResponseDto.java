package shopify.demo.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private UUID id;
    private String brand;
    private String model;
    private String socket;
    private Double fanSize;
    private Integer fanSpeed;
    private Double fanNoiseLevel;
    private Integer numberOfFans;
    private Double price;
}
