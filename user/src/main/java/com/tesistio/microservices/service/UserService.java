package com.tesistio.microservices.service;

import com.tesistio.microservices.model.User;

public interface UserService {
    void storeUser(User user);
    User findByEmail(String email);
    boolean existsUserByEmail(String email);
    void deleteByEmail(String email);
}
