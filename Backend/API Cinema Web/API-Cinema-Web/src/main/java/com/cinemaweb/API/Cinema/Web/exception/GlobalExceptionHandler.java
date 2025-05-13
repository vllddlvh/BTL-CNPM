package com.cinemaweb.API.Cinema.Web.exception;


import com.cinemaweb.API.Cinema.Web.dto.response.ApiResponseError;
import jakarta.transaction.RollbackException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
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


    // Bắt lỗi Http request body
    // VD: Request thiếu body, các attribute trong body bị sai cú pháp, sai kiểu dữ liệu...
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseError> handlingHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(ErrorCode.RUNTIME_EXCEPTION.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponseError);
    }


    // Request bị chặn do chưa được authorized
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


    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity<ApiResponseError> handlingTransactionSystemException(TransactionSystemException e) {
        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(9991)
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponseError);
    }

    @ExceptionHandler(value = RollbackException.class)
    public ResponseEntity<ApiResponseError> handlingRollbackException(RollbackException e) {
        ApiResponseError apiResponseError = ApiResponseError.builder()
                .code(9992)
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponseError);
    }

}
