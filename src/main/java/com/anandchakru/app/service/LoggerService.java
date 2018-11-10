package com.anandchakru.app.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LoggerService {
	@SuppressWarnings("rawtypes")
	@Before("com.anandchakru.app.model.util.AppJointpoints.app()")
	public void before(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		Class targetClass = signature.getDeclaringType();
		Logger logger = LoggerFactory.getLogger(targetClass);
		logger.debug("* AOP'd " + signature.getName());
	}
}
