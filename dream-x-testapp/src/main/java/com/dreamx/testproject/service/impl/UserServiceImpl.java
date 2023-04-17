package com.dreamx.testproject.service.impl;

import com.dreamx.testproject.dto.RegistrationDtoRequest;
import com.dreamx.testproject.dto.UserDtoResponse;
import com.dreamx.testproject.exception.RoleNotFoundException;
import com.dreamx.testproject.exception.UserAlreadyExistException;
import com.dreamx.testproject.model.Role;
import com.dreamx.testproject.model.User;
import com.dreamx.testproject.repository.RoleRepository;
import com.dreamx.testproject.repository.UserRepository;
import com.dreamx.testproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public UserDtoResponse register(RegistrationDtoRequest registrationDto) {
        log.debug("Register new user = " + registrationDto);
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User with this email already exists: " + registrationDto.getEmail());
        }

        User user = new User()
                .setFirstName(registrationDto.getFirstName())
                .setLastName(registrationDto.getLastName())
                .setId(UUID.randomUUID())
                .setPassword(passwordEncoder.encode(registrationDto.getPassword()))
                .setEmail(registrationDto.getEmail())
                .setRoles(List.of(getRoleByName("USER")))
                .setActive(true);
        UserDtoResponse response = modelMapper.map(userRepository.save(user), UserDtoResponse.class);
        log.debug("New user = " + response + " was successfully register");
        return response;
    }

    private Role getRoleByName(String name) {
        log.debug("Get role by name = " + name);
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role with name=" + name + "not found!");
        }
        return role.get();
    }
}
