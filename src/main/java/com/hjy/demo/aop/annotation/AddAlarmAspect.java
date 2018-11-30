package com.hjy.demo.aop.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * example
 *
 * @author hjy
 * @date 2018/11/27
 **/
@Aspect
@Component
public class AddAlarmAspect {

    public static final Logger logger = LoggerFactory.getLogger(AddAlarmAspect.class);

    @Pointcut("@annotation(com.hjy.demo.aop.annotation.AddAlarm)")
    public void addAlarm() {
    }

//    public Object  TODO

}
