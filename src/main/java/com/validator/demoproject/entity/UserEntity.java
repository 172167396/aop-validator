package com.validator.demoproject.entity;

import com.validator.demoproject.annotation.Regx;
import com.validator.demoproject.annotation.SmallThan;
import com.validator.demoproject.service.CommonInterface;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEntity implements CommonInterface {

    private String id;

    private String name;

    private BankEntity bank;

    @SmallThan(value = 120, msg = "年龄不能超过120")
    private int age;

    @Regx(pattern = "^\\d{11}$", msg = "手机号码必须为11位数字")
    private String mobile;

    public void eat() {
        System.out.println("i'm eating orange");
    }

    @Override
    public String get() {
        return this.toString();
    }
}
