package org.jboss.tutorial.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Logueador {

	@AroundInvoke
	public Object modifyGreeting(InvocationContext ctx) throws Exception {
		System.out.println("Antes del add");
		try {
			Object o = ctx.proceed();
			System.out.println("Después del add");
			return o;
		} catch (Exception e) {
			return 0;
		}
		
	}

}
