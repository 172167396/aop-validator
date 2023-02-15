package com.validator.demoproject.service.impl;

import com.validator.demoproject.entity.BankEntity;
import com.validator.demoproject.service.BankService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankServiceImpl implements BankService {

    @Override
    public BankEntity getBank(String userId) {
        BankEntity bankEntity = new BankEntity();
        bankEntity.setBalance(new BigDecimal(1880000));
        bankEntity.setName("汇丰银行");
        bankEntity.setLocation("火星");
        return bankEntity;
    }
}
