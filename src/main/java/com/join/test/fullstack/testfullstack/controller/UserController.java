package com.join.test.fullstack.testfullstack.controller;

import com.join.test.fullstack.testfullstack.dto.JwtDto;
import com.join.test.fullstack.testfullstack.dto.LoginDto;
import com.join.test.fullstack.testfullstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtDto> authenticateUser(@RequestBody LoginDto loginDto) {
        JwtDto token = userService.authenticateUser(loginDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody LoginDto loginDto) {
        userService.createUser(loginDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}