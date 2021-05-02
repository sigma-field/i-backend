package com.bb.recipes.controller;

import java.util.List;

import com.bb.recipes.entities.Role;
import com.bb.recipes.entities.User;
import com.bb.recipes.service.RoleService;
import com.bb.recipes.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private UserService userService;
    private final RoleService roleService;

    public UserRegistrationController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user) {
        user.setRole(roleService.findById(user.getRole().getId()));
        userService.save(user);
        return "redirect:/registration?success";

    }
}