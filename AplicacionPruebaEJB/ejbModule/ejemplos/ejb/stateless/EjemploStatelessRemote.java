package ejemplos.ejb.stateless;

import javax.ejb.Remote;

@Remote
public interface EjemploStatelessRemote {

	public String hola(String mensaje);
	

}
