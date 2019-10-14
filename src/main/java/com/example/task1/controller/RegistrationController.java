package com.example.task1.controller;

import com.example.task1.model.User;
import com.example.task1.service.RoleService;
import com.example.task1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/registration")
    public String showRegPage() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUserFromDb(User user, RedirectAttributes redirAttrs) {
        User userFromDb = userService.getUserByLogin(user.getLogin());
        if (userFromDb != null) {
            redirAttrs.addFlashAttribute("message", "This username already exists");
            return "redirect:/registration";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(roleService.getRoleById(1L));
        userService.save(user);

        return "redirect:/login";
    }
}
