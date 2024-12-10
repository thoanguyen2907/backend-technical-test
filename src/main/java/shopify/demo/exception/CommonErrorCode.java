package shopify.demo.exception;

import org.springframework.http.HttpStatus;

public interface CommonErrorCode {
    HttpStatus status();

    String message();
}
