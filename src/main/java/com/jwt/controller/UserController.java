package com.jwt.controller;

import com.jwt.dto.UserDto;
import com.jwt.error.CustomErrorResponse;
import com.jwt.model.User;
import com.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomErrorResponse customErrorResponse;

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers().stream()
                .peek(user -> user.setPassword("*****"))
                .collect(Collectors.toList());
    }


    @GetMapping("/user")
    public String getUser(Principal principal) {
        return "Hello " + principal.getName();
    }

    @GetMapping("/admin")
    public String getAdmin(Principal principal) {
        return "Admin " + principal.getName();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        boolean allFieldsFilled = Stream.of(userDto.getUsername(), userDto.getPassword())
                .allMatch(field -> field != null && !field.isEmpty());

        if (!allFieldsFilled) {
            customErrorResponse.setError("Bad Request");
            customErrorResponse.setMessage("Null or empty value");
            return ResponseEntity.status(400).body(customErrorResponse);
        }

        userService.addUser(userDto);
        return ResponseEntity.status(200).body("Registered");
    }



}
