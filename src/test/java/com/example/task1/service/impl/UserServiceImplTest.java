package com.example.task1.service.impl;

import com.example.task1.model.Role;
import com.example.task1.model.User;
import com.example.task1.repository.UserRepository;
import com.example.task1.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl(userRepository);

    @Before
    public void setUp() {
        Mockito.when(userRepository.getUserByLogin("testUser"))
                .thenReturn(new User("testUser", "testPassword", new Role(1L,"ROLE_TEST")));
        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenReturn(new User("testUser", "testPassword", new Role(1L,"ROLE_TEST")));
    }

    @Test
    public void getUserByLoginTest() {
        User userTest = userService.getUserByLogin("testUser");
        System.out.println(userTest);
        Assert.assertEquals("testUser", userTest.getLogin());
    }

    @Test
    public void saveTest() {
        User testUser = new User();
        userService.save(testUser);

        verify(userRepository, Mockito.times(1)).save(testUser);
    }
}