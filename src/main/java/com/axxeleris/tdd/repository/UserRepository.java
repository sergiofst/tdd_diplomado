package com.axxeleris.tdd.repository;

import com.axxeleris.tdd.domain.User;

public interface UserRepository {
    User findUser(String user);
}
