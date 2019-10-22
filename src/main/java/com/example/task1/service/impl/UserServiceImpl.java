package com.example.task1.service.impl;

import com.example.task1.model.User;
import com.example.task1.repository.UserRepository;
import com.example.task1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
