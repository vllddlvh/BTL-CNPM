package com.cinemaweb.API.Cinema.Web.exception;


import com.cinemaweb.API.Cinema.Web.dto.response.ApiResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponseError> handlingUnknownException(Exception e) {
        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(ErrorCode.UNKNOWN_EXCEPTION.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(ErrorCode.UNKNOWN_EXCEPTION.getHttpStatusCode())
                .body(apiResponseError);

    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponseError> handlingRuntimeException(RuntimeException e) {
        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(ErrorCode.RUNTIME_EXCEPTION.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(ErrorCode.UNKNOWN_EXCEPTION.getHttpStatusCode())
                .body(apiResponseError);

    }


    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponseError> handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponseError);
    }


    // Validation nhập liệu từ client
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseError> handlingMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorKey = e.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.valueOf(errorKey);

        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponseError);
    }


    // HTTP Request không có body
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseError> handlingHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ErrorCode errorCode = ErrorCode.REQUEST_MISSING_BODY;
        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponseError);
    }


    // Request bị chặn do không được authorized
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResponseError> handlingAccessDeniedException(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponseError);
    }
}
