package com.axxeleris.tdd.repository.impl;

import com.axxeleris.tdd.domain.User;
import com.axxeleris.tdd.repository.UserRepository;

import java.util.Optional;

public class FakeUserRepository implements UserRepository {
    @Override
    public Optional<User> findUser(String user) {
        return Optional.of(
                new User("user", "secret", false)
        );
    }

    @Override
    public User save(User newUser) {
        return null;
    }
}
