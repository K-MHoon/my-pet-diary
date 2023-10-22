package com.kmhoon.service.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("within(com.kmhoon.service.controller..*)")
    public void controller() {}

    @Before("controller()")
    public void beforeRequest(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        sb.append("method [").append(method.getName()).append("] ")
                .append("called by [").append(getCallUserEmail()).append("] ");

        sb.append("parameters [");
        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < parameters.length; i++) {
            sb.append(parameters[i].getName())
                    .append(" = ")
                    .append(args[i]);

            if((i-1) != parameters.length) {
                sb.append(", ");
            }
        }
        sb.append("]");

        log.info(sb.toString());
    }

    private String getCallUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return "None";
        }
        return authentication.getName();
    }
}
