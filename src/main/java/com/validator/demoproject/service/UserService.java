package com.validator.demoproject.service;

import com.validator.demoproject.entity.BankEntity;
import com.validator.demoproject.entity.UserEntity;

public interface UserService {

    UserEntity getUser(String id);

    BankEntity getBank(String id);

    UserEntity requireUser(String id);
}
