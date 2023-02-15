package com.validator.demoproject.aspect;

import com.validator.demoproject.annotation.CheckField;
import com.validator.demoproject.entity.ValidatorWrapper;
import com.validator.demoproject.enums.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ValidFieldAspect {

    ////annotation inside class
//    @Pointcut("execution(* *(..,@com.validator.demoproject.annotation.CheckField *,..))")
    @Pointcut("execution(* *(..,@com.validator.demoproject.annotation.CheckField (*),..))")
    public void pointCut() {
        //ignore
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ObjectUtils.isEmpty(args)) {
            return;
        }
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            if (ObjectUtils.isEmpty(parameterAnnotations)) {
                return;
            }
            for (int i = 0; i < parameterAnnotations.length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    if (annotation instanceof CheckField) {
                        check(args[i]);
                    }
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


    private void check(Object obj) {
        //简单过滤下类型
        if (obj instanceof String || obj instanceof Integer) {
            return;
        }
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        List<String> errors = new ArrayList<>(declaredFields.length);
        for (Field field : declaredFields) {
            try {
                Validator.parseFieldAnnotation(obj, field).ifPresent(ValidatorWrapper::process);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(",", errors));
        }
    }


}
