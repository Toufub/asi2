package com.asi2.auth.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthError extends RuntimeException {
    public AuthError(String code){
        super(String.format("errors.auth.%s", code));
    }

}



