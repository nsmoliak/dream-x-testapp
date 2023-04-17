package com.dreamx.testproject.config;

import com.dreamx.testproject.validation.EmailValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {
    @Bean
    public EmailValidator usernameValidator() {
        return new EmailValidator();
    }
}
