package com.example.task1.service.impl;

import com.example.task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.example.task1.model.User domainUser = userService.getUserByLogin(login);
        if (domainUser == null) {
            throw new UsernameNotFoundException("username " + login + " not found");
        }
        return new User(
                domainUser.getLogin(),
                domainUser.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(domainUser.getRole().getId())
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Long role) {
        return RoleMapServise.getListRole(role).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
