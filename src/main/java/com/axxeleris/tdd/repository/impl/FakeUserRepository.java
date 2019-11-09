package com.axxeleris.tdd.repository.impl;

import com.axxeleris.tdd.domain.User;
import com.axxeleris.tdd.repository.UserRepository;

public class FakeUserRepository implements UserRepository {
    @Override
    public User findUser(String user) {
        return new User("user", "secret", false);
    }
}
