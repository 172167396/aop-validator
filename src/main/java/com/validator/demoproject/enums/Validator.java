package com.validator.demoproject.enums;

import com.validator.demoproject.annotation.Regx;
import com.validator.demoproject.annotation.SmallThan;
import com.validator.demoproject.entity.ValidatorWrapper;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

public enum Validator {


    REGX() {
        @Override
        public void valid(Annotation annotation, Object val) {
            if(val == null) return;
            if (val instanceof String) {
                String valString = (String) val;
                Regx regx = (Regx) annotation;
                String pattern = regx.pattern();
                if (ObjectUtils.isEmpty(pattern)) {
                    return;
                }
                Pattern pt = Pattern.compile(pattern);
                boolean matches = pt.matcher(valString).matches();
                if (!matches) {
                    throw new RuntimeException(regx.msg());
                }
            }
        }
    },

    SMALL_THAN() {
        @Override
        public void valid(Annotation annotation, Object val) {
            if(val == null) return;
            String s = val.toString();
            BigDecimal decimalValue;
            try {
                decimalValue = new BigDecimal(s);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

            SmallThan smallThan = (SmallThan) annotation;
            if (decimalValue.compareTo(new BigDecimal(smallThan.value())) > 0) {
                throw new RuntimeException(smallThan.msg());
            }
        }

    };

    public abstract void valid(Annotation annotation, Object val);


    public static Optional<ValidatorWrapper> parseFieldAnnotation(Object target, Field field) {
        Regx annotation = field.getAnnotation(Regx.class);
        if (annotation != null) {
            return Optional.of(new ValidatorWrapper(annotation, REGX, target, field));
        }
        SmallThan smallThan = field.getAnnotation(SmallThan.class);
        if (smallThan != null) {
            return Optional.of(new ValidatorWrapper(smallThan, SMALL_THAN, target, field));
        }
        return Optional.empty();
    }


}
