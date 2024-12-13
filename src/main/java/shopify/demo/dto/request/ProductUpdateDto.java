package shopify.demo.dto.request;

import jakarta.validation.constraints.NotNull;
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
public class ProductUpdateDto {
    @NotNull(message = "Brand cannot be null")
    private String brand;
    @NotNull(message = "Model cannot be null")
    private String model;
    @NotNull(message = "Socket cannot be null")
    private String socket;
    @NotNull(message = "Fansize cannot be null")
    private Double fanSize;
    @NotNull(message = "Fanspeed cannot be null")
    private Integer fanSpeed;
    @NotNull(message = "FanNoiseLevel cannot be null")
    private Double fanNoiseLevel;
    @NotNull(message = "NumberOfFans cannot be null")
    private Integer numberOfFans;
    @NotNull(message = "Price cannot be null")
    private Double price;
}
