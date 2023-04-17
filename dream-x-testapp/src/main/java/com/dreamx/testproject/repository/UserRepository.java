package com.dreamx.testproject.repository;

import com.dreamx.testproject.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @EntityGraph(value = "user.roles")
    Optional<User> findByEmail(String email);
}
