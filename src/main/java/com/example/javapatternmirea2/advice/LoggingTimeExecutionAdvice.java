package com.example.javapatternmirea2.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.util.Calendar;

@Slf4j
@Component
@Aspect
public class LoggingTimeExecutionAdvice {

    @Around("allServiceMethods()")
    public Object  loggingTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = Calendar.getInstance().getTimeInMillis();
        var result = pjp.proceed();
        long end = Calendar.getInstance().getTimeInMillis();
        log.info("finished in {} milliseconds", end-start);
        return result;
    }

    @Pointcut("within(com.example.javapatternmirea2.service.*)")
    public void allServiceMethods() {}
}
