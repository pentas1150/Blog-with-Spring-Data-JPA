package com.jpa.blog.service;

import com.jpa.blog.domain.user.SignUpDTO;
import com.jpa.blog.domain.user.User;
import com.jpa.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insertUser(SignUpDTO signUpDTO) {
        return userRepository.save(signUpDTO.toEntity());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws IllegalAccessException {
        return userRepository.findById(id).orElseThrow(IllegalAccessException::new);
    }

    public User findByUserId(String userId) throws IllegalAccessException {
        return userRepository.findByUserId(userId).orElseThrow(IllegalAccessException::new);
    }

    public User findByUserNickname(String userNickname) throws IllegalAccessException {
        return userRepository.findByUserNickname(userNickname).orElseThrow(IllegalAccessException::new);
    }
}
