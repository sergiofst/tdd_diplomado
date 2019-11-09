package com.axxeleris.tdd.service.impl;

import com.axxeleris.tdd.domain.User;
import com.axxeleris.tdd.exception.CreateUserException;
import com.axxeleris.tdd.exception.ExistingUserException;
import com.axxeleris.tdd.repository.UserRepository;
import com.axxeleris.tdd.service.UserService;
import org.apache.commons.lang3.StringUtils;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User newUser) {
        if(StringUtils.isEmpty(newUser.getUserName())){
            throw new CreateUserException("El nombre de usuario es invalido");
        }

        if(userRepository.findUser(newUser.getUserName()).isPresent()) {
            throw new ExistingUserException();
        }

        return userRepository.save(newUser);
    }
}
