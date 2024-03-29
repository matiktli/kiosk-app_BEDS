package com.kiosk.handler;

import com.kiosk.exception.BaseKioskException;
import com.kiosk.exception.ObjectNotFoundException;
import com.kiosk.exception.RoomBookingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ObjectNotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest webRequest) {
        return handleException(ex, HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = { UsernameNotFoundException.class, BadCredentialsException.class, AccessDeniedException.class})
    public ResponseEntity<Object> handleSecurityException(Exception ex, WebRequest webRequest) {
        return handleException(ex, HttpStatus.UNAUTHORIZED, webRequest);
    }

    @ExceptionHandler(value = { RoomBookingException.class } )
    public ResponseEntity<Object> handleBadRequestExceptions(Exception ex, WebRequest webRequest) {
        return handleException(ex, HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(value = { Exception.class, BaseKioskException.class })
    public ResponseEntity<Object> handleBasicException(Exception ex, WebRequest webRequest) {
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    private ResponseEntity<Object> handleException(Exception ex, HttpStatus httpStatus, WebRequest webRequest) {
        log.error("Error: {}. ({})", webRequest, ex);

        String errorMessage = ex.getMessage();
        if (ex instanceof MethodArgumentNotValidException) {
            if (!((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().isEmpty()) {
                errorMessage = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            }
        }
        int internalCode = -1;
        if (ex instanceof BaseKioskException) {
            internalCode = ((BaseKioskException) ex).getInternalCode().getValue();
        }
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), internalCode, errorMessage);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, webRequest);
    }
}
