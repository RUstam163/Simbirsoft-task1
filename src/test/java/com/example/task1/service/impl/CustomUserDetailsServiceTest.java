package com.example.task1.service.impl;

import com.example.task1.model.Role;
import com.example.task1.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {

    @Mock
    UserServiceImpl userService;

    @InjectMocks
    CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();

    @Before
    public void setUp() {
        Mockito.when(userService.getUserByLogin("test"))
                .thenReturn(new User("test", "testPass", new Role(1L, "ROLE_USER")));
        Mockito.when(userService.getUserByLogin("test2"))
                .thenReturn(null)
                .thenThrow(new UsernameNotFoundException("username " + "test2" + " not found"));
    }


    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameTest_UserNotFoundExeption() {
        customUserDetailsService.loadUserByUsername("test2");
    }

    @Test
    public void loadUserByUserNameTest_newUserDetail() {
        User user = userService.getUserByLogin("test");
        UserDetails userDetails = new org.springframework.security.core.userdetails
                .User(user.getLogin(), user.getPassword(), true, true, true, true,
                Stream.of("ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_USER")
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        UserDetails userDetails1 = customUserDetailsService.loadUserByUsername("test");
        Assert.assertEquals(userDetails.getUsername(), userDetails1.getUsername());
        Assert.assertEquals(userDetails.getAuthorities(), userDetails1.getAuthorities());

    }

    @Test
    public void getAuthoritiesTest() {

    }
}