package com.tesistio.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tesistio.microservices.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
    boolean existsUserByEmail(String email);
    void deleteByEmail(String email);
}
