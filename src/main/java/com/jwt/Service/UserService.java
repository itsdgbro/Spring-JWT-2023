package com.jwt.Service;

import com.jwt.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public UserService(){
        users.add(new User(new Random().nextInt(1000)+1, "dipak", "dg@gmail.com"));
        users.add(new User(new Random().nextInt(1000)+1, "ramesh", "rg@gmail.com"));
        users.add(new User(new Random().nextInt(1000)+1, "akash", "ag@gmail.com"));
        users.add(new User(new Random().nextInt(1000)+1, "sarong", "sg@gmail.com"));
    }

    public List<User> getUsers(){
        return this.users;
    }
}
