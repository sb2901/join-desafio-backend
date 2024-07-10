package com.join.test.fullstack.testfullstack.configuration;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorInterceptorConfig {

    @ExceptionHandler(JWTVerificationException.class)
    public ProblemDetail handleJWtExceptions(Exception exception) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
        errorDetail.setProperty("description", exception.getMessage());
        return errorDetail;
    }
}
