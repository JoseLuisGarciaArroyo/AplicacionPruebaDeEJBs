package ejemplo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.tutorial.stateful.bean.ShoppingCartRemote;
import org.jboss.tutorial.stateless.bean.Calculator;
import org.jboss.tutorial.stateless.bean.CalculatorRemote;

//https://docs.jboss.org/author/display/WFLY8/EJB+invocations+from+a+remote+client+using+JNDI

/**
 * Servlet implementation class SrvPrueba
 */
@WebServlet("/SrvPrueba")
public class SrvPrueba extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SrvPrueba() {
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// http://www.arquitecturajava.com/introduccion-a-ejb-3-1-i/
		System.out.println("Estoy en el service");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Clasico Hola mundo!</title>");
		out.println("</head>");
		out.println("<body>");
		int a = 4;
		int b = 7;
		int suma = 0;
		int resta = 0;
		try {
			Properties props = new Properties();
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			Context context = new InitialContext(props);
			Calculator calculator = (CalculatorRemote) context.lookup(
					"java:app/AplicacionPruebaEJB/CalculatorBean!org.jboss.tutorial.stateless.bean.CalculatorRemote");
			suma = calculator.add(a, b);
			resta = calculator.subtract(a, b);
			out.println("<h1>SUMA: " + suma + "</h1>");
			out.println("<h1>RESTA: " + resta + "</h1>");
			//
			ShoppingCartRemote shoppingCartRemote = (ShoppingCartRemote) context.lookup(
					"java:app/AplicacionPruebaEJB/ShoppingCartBean!org.jboss.tutorial.stateful.bean.ShoppingCartRemote");
			shoppingCartRemote.buy("P1", 4);
			shoppingCartRemote.buy("P2", 8);
			shoppingCartRemote.buy("P3", 7);
			shoppingCartRemote.buy("P4", 3);
			out.println("<h1>ShoppingCart: " + shoppingCartRemote.getCartContents() + "</h1>");
			System.out.println(shoppingCartRemote.getCartContents());
			shoppingCartRemote.checkout();

		} catch (NamingException e) {
			e.printStackTrace();
		}

		out.println("</body>");
		out.println("</html>");
	}

}
