package com.validator.demoproject.entity;

import com.validator.demoproject.enums.Validator;
import lombok.Value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Value
public class ValidatorWrapper {
    Annotation annotation;
    Validator validator;

    Object target;
    Field field;

    public void process() {
        Object val;
        try {
            field.setAccessible(true);
            val = field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        validator.valid(annotation, val);
    }
}