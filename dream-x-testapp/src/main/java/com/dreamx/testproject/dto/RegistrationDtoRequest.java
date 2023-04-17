package com.dreamx.testproject.dto;

import com.dreamx.testproject.validation.PasswordMatches;
import com.dreamx.testproject.validation.ValidEmail;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@PasswordMatches
public class RegistrationDtoRequest {
    @NotNull
    @Size(min = 1, message = "{registration_dto.size.firstName}")
    private String firstName;
    @NotNull
    @Size(min = 1, message = "{registration_dto.size.lastName}")
    private String lastName;
    @NotNull
    @ValidEmail
    @Size(min = 1, message = "{registration_dto.size.email}")
    private String email;
    @Size(min = 6, message = "{registration_dto.size.password}")
    private String password;
    private String matchingPassword;
}
