package org.bees.admin.advice;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.constraints.NotNull;
import org.bees.admin.dto.error.ErrorResponse;
import org.bees.admin.exception.JwtException;
import org.spring.generic.advice.GenericAdviceController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AdviceController extends GenericAdviceController {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex, WebRequest request){
        String path = request.getDescription(false);
        ErrorResponse error = new ErrorResponse(new Date(), path.substring("uri=".length()), String.valueOf(HttpStatus.UNAUTHORIZED.value()),HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException ex){
        ErrorResponse error = new ErrorResponse(new Date(), "", String.valueOf(HttpStatus.UNAUTHORIZED.value()),HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> getServerExceptionHandler(Exception exception) {
        if (exception instanceof ExpiredJwtException) {
            ErrorResponse error = new ErrorResponse(new Date(), "","Token expired",HttpStatus.UNAUTHORIZED.getReasonPhrase(), "Token expired");
            return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
        }
        ErrorResponse error = new ErrorResponse(new Date(), exception.getLocalizedMessage(),"Token expired",HttpStatus.UNAUTHORIZED.getReasonPhrase(), exception.getLocalizedMessage());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
