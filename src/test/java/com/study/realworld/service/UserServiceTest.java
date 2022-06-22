package com.study.realworld.service;

import com.study.realworld.domain.User;
import com.study.realworld.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void join(){
        User user = new User();
        user.setEmail("simononze@naver.com");
        user.setUsername("양승훈");
        user.setPassword("123456");
        user.setBio("남");
        Date date = new Date();
        user.setCreated_at(date);
        user.setImage("명품그림");

        User savedUser = userService.join(user);

        User findUser = userService.findByEmail("simononze@naver.com");

        System.out.println(savedUser.getUsername());
        System.out.println(findUser.getUsername());
    }



}