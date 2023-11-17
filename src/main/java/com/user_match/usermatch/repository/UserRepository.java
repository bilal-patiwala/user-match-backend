package com.user_match.usermatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user_match.usermatch.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByLocations(String location);
    Optional<User> findByBodyType(String body_type);
}
