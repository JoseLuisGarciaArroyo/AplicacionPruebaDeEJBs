package ejemplos.ejb.stateless;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class EjemploStateless implements EjemploStatelessRemote, EjemploStatelessLocal {

	public String hola(String mensaje) {
		return mensaje + " que tal";
	}

}
