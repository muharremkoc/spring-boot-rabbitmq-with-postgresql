package com.rabbitmq.springbootrabbitmqdemo.services.user;

import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import com.rabbitmq.springbootrabbitmqdemo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService{

    final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(long userID) throws NotFoundException {
        return userRepository.findById(userID).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<User> findUsersIDAndName(String name) {
          return userRepository.findAll().stream().filter(user -> user.getName().equals(name)).collect(Collectors.toList());
    }
}
