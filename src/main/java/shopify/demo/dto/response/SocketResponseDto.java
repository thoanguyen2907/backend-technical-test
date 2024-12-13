package shopify.demo.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocketResponseDto {
    private UUID id;
    private String name;
}
