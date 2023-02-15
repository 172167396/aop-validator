package com.validator.demoproject.service.impl;

import com.validator.demoproject.entity.BankEntity;
import com.validator.demoproject.entity.UserEntity;
import com.validator.demoproject.service.BankService;
import com.validator.demoproject.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    BankService bankService;

    @Override
    public UserEntity getUser(String id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setName("派大星");
//        BankEntity bank = ((UserService)AopContext.currentProxy()).getBank(id);
        BankEntity bank = this.getBank(id);
        userEntity.setBank(bank);
        return userEntity;
    }

    @Override
    public BankEntity getBank(String id) {
        return bankService.getBank(id);
    }

    @Override
    public UserEntity requireUser(String id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setName("派大星");
//        BankEntity bank = ((UserService)AopContext.currentProxy()).getBank(id);
        userEntity.setBank(null);
        return userEntity;
    }
}
