package com.dreamx.testproject.validation;

import com.dreamx.testproject.dto.RegistrationDtoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final RegistrationDtoRequest registrationDto = (RegistrationDtoRequest) obj;
        return registrationDto.getPassword().equals(registrationDto.getMatchingPassword());
    }

}
