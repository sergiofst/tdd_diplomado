package com.axxeleris.tdd;

import com.axxeleris.tdd.exception.UserNotFoundException;
import com.axxeleris.tdd.repository.UserRepository;
import com.axxeleris.tdd.domain.User;
import com.axxeleris.tdd.exception.BlockedUserException;
import com.axxeleris.tdd.exception.InvalidUserAndPasswordException;
import com.axxeleris.tdd.repository.impl.FakeUserRepository;
import com.axxeleris.tdd.repository.impl.UserRepositoryImpl;
import com.axxeleris.tdd.service.impl.LoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    UserRepository userRepository;
    LoginServiceImpl loginService;

    User expectedUser = new User("user", "secret", false);
    User userWithWrongPassword = new User("user", "wrong password", false);

    @Before
    public void setUp() {
        userRepository = mock(UserRepositoryImpl.class);
        loginService = new LoginServiceImpl(userRepository);
    }

    @Test
    public void shouldLoginWithUserAndPassword() {

        // Stub
        when(userRepository.findUser(Mockito.anyString()))
                .thenReturn(Optional.of(expectedUser));

        User loggedUser = loginService.login("user", "secret");

        assertEquals(loggedUser, expectedUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void failOnInvalidUsername() {

        // Stub
        when(userRepository.findUser(Mockito.anyString()))
                .thenReturn(Optional.empty());

        LoginServiceImpl loginService = new LoginServiceImpl(userRepository);
        loginService.login("user", "wrong_password");
    }

    @Test(expected = InvalidUserAndPasswordException.class)
    public void throwInvalidUserAndPasswordExceptionOnInvalidPassword() {

        // Stub
        when(userRepository.findUser(Mockito.anyString()))
                .thenReturn(Optional.of(userWithWrongPassword));

        LoginServiceImpl loginService = new LoginServiceImpl(userRepository);
        loginService.login("user", "wrong_password");
    }

    @Test(expected = BlockedUserException.class)
    public void throwBlockedUserExceptionOnBlockedUser() {
        User blockedUser = new User("user", "secret", true);

        // Stub
        when(userRepository.findUser(Mockito.anyString()))
                .thenReturn(Optional.of(blockedUser));

        LoginServiceImpl loginService = new LoginServiceImpl(userRepository);
        loginService.login("blocked_user", "secret");
    }

    @Test
    public void shouldLoginWithUserAndPasswordFake() {
        // Fake
        userRepository = new FakeUserRepository();
        loginService = new LoginServiceImpl(userRepository);

        loginService.login("user", "secret");
    }

    @Test
    public void shouldLoginWithUserAndPasswordMock() {
        // Mock
        LoginServiceImpl mockLoginService = mock(LoginServiceImpl.class);
        mockLoginService.login("user", "secret");
    }


}
