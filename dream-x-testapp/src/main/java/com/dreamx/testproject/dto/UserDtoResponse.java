package com.dreamx.testproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDtoResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}
