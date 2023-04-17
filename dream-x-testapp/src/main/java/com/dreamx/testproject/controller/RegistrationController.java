package com.dreamx.testproject.controller;

import com.dreamx.testproject.dto.RegistrationDtoRequest;
import com.dreamx.testproject.dto.UserDtoResponse;
import com.dreamx.testproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtoResponse> registerUserAccount(@RequestBody @Valid RegistrationDtoRequest registrationDto) {
        return new ResponseEntity<>(userService.register(registrationDto), HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}
