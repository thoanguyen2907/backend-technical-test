package shopify.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


import shopify.demo.shared.ResponseEntityBuilder;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntityBuilder.getBuilder()
                .setCode(HttpStatus.BAD_REQUEST.value())
                .setMessage("Validation failed")
                .setDetails(errors)
                .build();
    }

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
                .setCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                .setMessage(e.getMessage())
                .build();
    }

}
