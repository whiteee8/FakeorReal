package com.study.realworld.service;

import com.study.realworld.domain.User;
import com.study.realworld.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(User user){
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        User findUser = userRepository.findByEmail(email);
        return findUser;
    }

    public User findById(Long id){
        User byId = userRepository.findById(id);
        return byId;
    }

    public void updateFavoriteCnt(Long userId){
        userRepository.updateFavoriteCnt(userId);
    }
}
