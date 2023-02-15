package com.validator.demoproject.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Regx {
    String pattern() default "";

    String msg() default "";
}
