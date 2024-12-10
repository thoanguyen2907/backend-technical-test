package shopify.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shopify.demo.shared.ResponseEntityBuilder;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShopifyRuntimeException.class)
    public ResponseEntity<?> handleEcommerceRunTimeException(final ShopifyRuntimeException ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(ex.getStatus())
                .message(ex.getMessage())
                .build();

        return ResponseEntityBuilder.getBuilder()
                .setCode(ex.getStatus())
                .setMessage(ex.getMessage())
                .setDetails(errorResponse)
                .build();
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        return ResponseEntityBuilder
                .getBuilder()
                .setCode(e.hashCode())
                .setMessage(e.getMessage())
                .build();
    }

}
