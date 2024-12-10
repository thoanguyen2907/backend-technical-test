package shopify.demo.shared;

import lombok.*;

@Getter
@Setter
public class GetAllOptions {
    public int limit  = 10;
    public int offset = 0;
}
