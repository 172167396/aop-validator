package com.validator.demoproject.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Map<String, String> handle(Exception e) {
        log.error(e.getMessage(), e);
        Map<String, String> result = new HashMap<>();
        result.put("code", "500");
        result.put("msg", e.getMessage());
        result.put("data", null);
        return result;
    }
}
