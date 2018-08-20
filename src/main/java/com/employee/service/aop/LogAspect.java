package com.employee.service.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

	Log log = LogFactory.getLog(LogAspect.class);
	
	@Around("@annotation(GenericLog)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();
	 
	    Object proceed = joinPoint.proceed();
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    log.info(joinPoint.getSignature() + "=" + executionTime);
	    return proceed;
	}
	
	@Before("@annotation(GenericLog)")
	public void logBefore(JoinPoint joinPoint) throws Throwable {
	 
	 
	    log.info("Entering... " + joinPoint.getSignature());
	}
	
	@After("@annotation(GenericLog)")
	public void logAfter(JoinPoint joinPoint) throws Throwable {
	 
	 
	    log.info("Exiting... " + joinPoint.getSignature());
	}
	
}


