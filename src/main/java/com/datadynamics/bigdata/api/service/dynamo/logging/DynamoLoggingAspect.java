package com.datadynamics.bigdata.api.service.dynamo.logging;

import com.datadynamics.bigdata.api.service.dynamo.DynamoRequestDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DynamoLoggingAspect {

    @Autowired
    DynamoRequestDispatcher dynamoRequestDispatcher;

    @Around("execution(* com.datadynamics.bigdata.api.service.dynamo.commands.*Command.execute(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("start - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        log.info("finished - " + pjp.getSignature().getDeclaringTypeName() + " / " + pjp.getSignature().getName());
        return result;
    }

}