package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isUsernameValid(String username) {
        if (username != null && !username.isEmpty()){
            return !userRepository.findById(username).isPresent();
        }
        return false;
    }

    // Other methods for user management...

}