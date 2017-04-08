package org.jboss.tutorial.stateless.bean;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.jboss.tutorial.interceptor.Logueador;

@Stateless
public class CalculatorBean implements CalculatorRemote, CalculatorLocal
{
	@Interceptors(Logueador.class)
   public int add(int x, int y)
   {
      return x + y;
   }

   public int subtract(int x, int y)
   {
      return x - y;
   }
}
