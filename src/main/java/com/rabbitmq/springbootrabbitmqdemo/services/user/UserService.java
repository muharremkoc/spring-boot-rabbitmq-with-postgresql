package com.rabbitmq.springbootrabbitmqdemo.services.user;

import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import org.webjars.NotFoundException;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User findUserById(long userID) throws NotFoundException;
    List<User> findUsersIDAndName(String name);
}
