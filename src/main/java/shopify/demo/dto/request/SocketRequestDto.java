package shopify.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocketRequestDto {
    @NotNull(message = "name cannot be null")
    private String name;
}
