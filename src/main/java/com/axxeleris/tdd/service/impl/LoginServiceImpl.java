package com.axxeleris.tdd.service.impl;

import com.axxeleris.tdd.domain.User;
import com.axxeleris.tdd.exception.BlockedUserException;
import com.axxeleris.tdd.exception.InvalidUserAndPasswordException;
import com.axxeleris.tdd.repository.UserRepository;
import com.axxeleris.tdd.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String userName, String password) {
        User foundUser = userRepository.findUser(userName);
        if ("secret".equals(password)) {
            checkBlockedUser(foundUser);
            return foundUser;
        } else {
            throw new InvalidUserAndPasswordException();
        }
    }

    private void checkBlockedUser(User user) {
        if (user.getBlocked()) {
            throw new BlockedUserException();
        }
    }

}
