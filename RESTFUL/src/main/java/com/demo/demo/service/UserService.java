package com.demo.demo.service;

import com.demo.demo.model.User;
import com.demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /* Save user */
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    /* Get all users*/
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
}
