package com.example.task1.service;

import com.example.task1.model.User;

public interface UserService {

    User getUserByLogin(String login);

    void save(User user);
}
