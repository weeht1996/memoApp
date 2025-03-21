package com.actrack.services;

import com.actrack.models.User;
import com.actrack.repositories.UserRepository;
import com.actrack.utility.PasswordEncoderUtil;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;
    
    public UserService(UserRepository userRepository, PasswordEncoderUtil passwordEncoderUtil) {
        this.userRepository = userRepository;
        this.passwordEncoderUtil = passwordEncoderUtil;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoderUtil.encodePassword(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean validatePassword(String rawPassword, String hashedPassword) {
        return passwordEncoderUtil.matches(rawPassword, hashedPassword);
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
