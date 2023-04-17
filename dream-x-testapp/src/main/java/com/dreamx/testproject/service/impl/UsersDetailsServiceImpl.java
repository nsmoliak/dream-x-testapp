package com.dreamx.testproject.service.impl;

import com.dreamx.testproject.model.User;
import com.dreamx.testproject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UsersDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with email = " + email + " not found in the system");
        }
        User user = userOptional.get();
        Set<GrantedAuthority> authorities = new HashSet<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            role.getPrivileges().stream().map(privilege -> new SimpleGrantedAuthority(privilege.getName())).forEach(authorities::add);
        });

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isActive(), true, true, true, authorities);
    }
}
