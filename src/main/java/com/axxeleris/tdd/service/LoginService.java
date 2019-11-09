package com.axxeleris.tdd.service;

import com.axxeleris.tdd.domain.User;

public interface LoginService {
    User login(String userName, String password);
}
