package shopify.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ShopifyRuntimeException extends RuntimeException {
    private String message;
    private HttpStatus status;
    public ShopifyRuntimeException(final CommonErrorCode code) {
        this.message = code.message();
        this.status = code.status();
    }
}
