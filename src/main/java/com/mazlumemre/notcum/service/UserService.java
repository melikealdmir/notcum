package com.mazlumemre.notcum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mazlumemre.notcum.entity.User;
import com.mazlumemre.notcum.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    ;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> authenticateAndGetUser(String mail, String password) {
        return userRepository.findByMailAndPassword(mail, password);
    }


}
