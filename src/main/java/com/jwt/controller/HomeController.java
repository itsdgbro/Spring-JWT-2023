package com.jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal){
        // this auth will have all current principle data and authorities
        return "Welcome Home";
    }


    @GetMapping("/users")
    public String getUsers(){
        return "All users";
    }

    @GetMapping("/user")
    public String getUser(Principal principal){
        return "Hello " + principal.getName();
    }

    @GetMapping("/admin")
    public String getAdmin(Principal principal){
        return "Admin " + principal.getName();
    }
}
