package com.anandchakru.app.model.util;

import org.aspectj.lang.annotation.Pointcut;

public class AppJointpoints {
	@Pointcut("execution(* com.anandchakru.app..*.*(..))")
	public void allApp() {
	}
	@Pointcut("execution(* com.anandchakru.app.service.LoggerService.*(..))")
	public void logger() {
	}
	@Pointcut("execution(* com.anandchakru.app.model..*.*(..))")
	public void model() {
	}
	@Pointcut("allApp() && !logger() && !model()")
	public void app() {
	}
}
