package com.rabbitmq.springbootrabbitmqdemo.controller;

import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import com.rabbitmq.springbootrabbitmqdemo.services.user.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping(value = "/api/message/users")
public class UserController {

    final UserService userService;
    @PostMapping("/user")
    public User saveUser(@RequestBody User user){

        return userService.saveUser(user);
    }

    @GetMapping("/{name}/userID")
    public List<User> getUsersID(@PathVariable("name") String name){
        return userService.findUsersIDAndName(name);
    }
}
