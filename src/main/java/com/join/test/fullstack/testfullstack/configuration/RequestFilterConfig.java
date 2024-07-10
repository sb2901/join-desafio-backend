package com.join.test.fullstack.testfullstack.configuration;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.join.test.fullstack.testfullstack.auth.JwtTokenService;
import com.join.test.fullstack.testfullstack.entity.User;
import com.join.test.fullstack.testfullstack.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Arrays;

@Component
public class RequestFilterConfig extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (isNonAuthenticatedPath(request)) {
                    tryAuthentic(request);
            }
        filterChain.doFilter(request, response);
        }catch (JWTVerificationException ex){
            resolver.resolveException(request,response,null,ex);
        }
    }

    /**
     *
     * @param request
     */
    private void tryAuthentic(HttpServletRequest request){

        String token = recoveryToken(request);
        if (token != null) {
            String subject = jwtTokenService.getSubjectFromToken(token);
            User user = userRepository.findByEmail(subject).get();

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new JWTVerificationException("Token não informado!");
        }
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean isNonAuthenticatedPath(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.NO_AUTHENTICATION).contains(requestURI);
    }

}