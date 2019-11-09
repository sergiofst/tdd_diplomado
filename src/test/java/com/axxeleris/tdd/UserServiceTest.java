package com.axxeleris.tdd;

import com.axxeleris.tdd.domain.User;
import com.axxeleris.tdd.exception.CreateUserException;
import com.axxeleris.tdd.exception.ExistingUserException;
import com.axxeleris.tdd.repository.UserRepository;
import com.axxeleris.tdd.service.UserService;
import com.axxeleris.tdd.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    UserRepository mockUserRepository;
    UserService userService;

    @Before
    public void setUp() throws Exception {
        mockUserRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(mockUserRepository);
    }

    @Test
    public void shouldCreateUser() {

        User persistedUser = new User(
                "new_user_name",
                "password",
                false
        );

        persistedUser.setId(1L);
        when(mockUserRepository.save(any())).thenReturn(persistedUser);

        User newUser = new User(
                "new_user_name",
                "new_password",
                false
        );

        User createdUser = userService.createUser(newUser);

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
    }

    @Test(expected = CreateUserException.class)
    public void shouldFailIfEmptyUsername() {
        User newUser = new User(
                null,
                "password",
                false
        );

        userService.createUser(newUser);
    }

    @Test(expected = ExistingUserException.class)
    public void shouldFailIfExistingUsername() {
        User newUser = new User(
                "existing_username",
                "password",
                false
        );

        when(mockUserRepository.findUser(anyString())).thenReturn(
                Optional.of(new User(
                        "existing_username",
                        "password",
                        false
                ))
        );

        userService.createUser(newUser);
    }
}
