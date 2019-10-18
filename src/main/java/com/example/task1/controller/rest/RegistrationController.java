package com.example.task1.controller.rest;

import com.example.task1.model.User;
import com.example.task1.model.constants.RoleConstants;
import com.example.task1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;


@RestController
public class RegistrationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/registration")
    public RedirectView addUserFromDb(User user, RedirectAttributes redirAttrs, HttpServletResponse response) {
        User userFromDb = userService.getUserByLogin(user.getLogin());
        if (userFromDb != null) {
            redirAttrs.addFlashAttribute("message", "This username already exists");
            return new RedirectView("/registration");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleConstants.ROLE_ADMIN);
        userService.save(user);
        return new RedirectView("/login");
    }
}
