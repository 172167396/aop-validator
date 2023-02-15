package com.validator.demoproject.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({METHOD, TYPE, PARAMETER, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SmallThan {
    int value() default 0;

    String msg() default "";
}
