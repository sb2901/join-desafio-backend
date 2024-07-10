package com.join.test.fullstack.testfullstack.service;

import com.join.test.fullstack.testfullstack.auth.JwtTokenService;
import com.join.test.fullstack.testfullstack.configuration.SecurityConfiguration;
import com.join.test.fullstack.testfullstack.dto.JwtDto;
import com.join.test.fullstack.testfullstack.dto.LoginDto;
import com.join.test.fullstack.testfullstack.entity.User;
import com.join.test.fullstack.testfullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public JwtDto authenticateUser(LoginDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        User userDetails = (User) authentication.getPrincipal();
        return new JwtDto(jwtTokenService.generateToken(userDetails));
    }

    public void createUser(LoginDto createUserDto) {

        User newUser = User.builder()
                .email(createUserDto.email())
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                .build();
        userRepository.save(newUser);
    }
}