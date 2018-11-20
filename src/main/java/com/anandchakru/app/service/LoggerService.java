package com.anandchakru.app.service;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LoggerService {
	@Around("com.anandchakru.app.model.util.AppJointpoints.app()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Logger logger = LoggerFactory.getLogger(signature.getDeclaringType());
		String method = signature.getMethod().getName();
		Object[] args = joinPoint.getArgs();
		Object ret = null;
		Long t = System.currentTimeMillis();
		try {
			ret = joinPoint.proceed();
			logger.debug("{} ({}) <{}> [{}]", method, paramAndArgs(args), (ret == null ? "-" : ret),
					(System.currentTimeMillis() - t));
		} catch (final Throwable e) {
			logger.debug("{} ({}) <{}> [{}]", method, paramAndArgs(args), (e == null ? "-" : e.toString()),
					(System.currentTimeMillis() - t));
			throw e;
		}
		return ret;
	}
	protected String paramAndArgs(Object[] args) {
		StringBuilder sb = new StringBuilder();
		if (args != null && args.length > 0) {
			int i = 0;
			for (i = 0; i < args.length - 1; i++) {
				sb.append(serializeIfArray(args[i])).append(", ");
			}
			sb.append(serializeIfArray(args[i]));
		}
		return sb.toString();
	}
	private String serializeIfArray(Object arg) {
		return arg == null ? "" : arg.getClass().isArray() ? Arrays.toString((Object[]) arg) : "" + arg;
	}
}
