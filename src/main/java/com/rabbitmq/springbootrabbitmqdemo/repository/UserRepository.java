package com.rabbitmq.springbootrabbitmqdemo.repository;

import com.rabbitmq.springbootrabbitmqdemo.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByName(String name);
}
