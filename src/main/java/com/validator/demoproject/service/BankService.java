package com.validator.demoproject.service;

import com.validator.demoproject.entity.BankEntity;

public interface BankService {
    BankEntity getBank(String userId);
}
