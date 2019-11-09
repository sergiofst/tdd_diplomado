package com.axxeleris.tdd.repository;

import com.axxeleris.tdd.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUser(String user);

    User save(User newUser);
}
