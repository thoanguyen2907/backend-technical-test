package shopify.demo.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode implements CommonErrorCode {
    ID_NOT_FOUND(HttpStatus.NOT_FOUND, "Could not find the Id"),
    ALREADY_EXIST(HttpStatus.BAD_REQUEST, "Item is already exist!");


    private final HttpStatus status;
    private final String message;

    private ErrorCode(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus status() {
        return this.status;
    }

    @Override
    public String message() {
        return this.message;
    }
}
