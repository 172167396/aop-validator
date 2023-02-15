package com.validator.demoproject.controller;

import com.validator.demoproject.annotation.CheckField;
import com.validator.demoproject.entity.UserEntity;
import com.validator.demoproject.service.BankService;
import com.validator.demoproject.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
public class UserController {

    @Resource
    UserService userService;
    @Resource
    BankService bankService;


    @Resource
    RestTemplate restTemplate;

    @Lazy
    @Resource
    UserController userController;


    @GetMapping("/getUser")
    public UserEntity getUser(String id) {
//        UserService bean = ApplicationContextHolder.getBean(UserService.class);
        return userService.getUser(id);
    }


    @PostMapping("/save")
    public String save(@CheckField UserEntity user, String id, String name) {
        return user.getId();
//        return name;
    }

    @GetMapping("/requireUser")
    public UserEntity requireUser(String id) {
        return userService.requireUser(id);
    }


}
