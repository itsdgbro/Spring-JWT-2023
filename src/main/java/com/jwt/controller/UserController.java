package com.jwt.controller;

import com.jwt.model.User;
import com.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public List<User> getUsers() {
        List<User> users = userService.getUsers().stream()
                .peek(user -> user.setPassword("*****"))
                .collect(Collectors.toList());
        return users;
    }


    @GetMapping("/user")
    public String getUser(Principal principal) {
        return "Hello " + principal.getName();
    }

    @GetMapping("/admin")
    public String getAdmin(Principal principal) {
        return "Admin " + principal.getName();
    }


}
