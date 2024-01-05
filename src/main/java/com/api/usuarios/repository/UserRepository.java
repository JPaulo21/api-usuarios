package com.api.usuarios.repository;

import com.api.usuarios.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<UserDetails> findByUsername(String username);

}
