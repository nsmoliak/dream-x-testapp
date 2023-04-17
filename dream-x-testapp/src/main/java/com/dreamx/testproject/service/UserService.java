package com.dreamx.testproject.service;

import com.dreamx.testproject.dto.RegistrationDtoRequest;
import com.dreamx.testproject.dto.UserDtoResponse;

public interface UserService {
    UserDtoResponse register(RegistrationDtoRequest registrationDto);

//    UserDto findByEmail(String email);
}
