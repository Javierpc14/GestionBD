package datos;

import usuarios.Juegos;
import usuarios.Juegoshasusuarios;
import usuarios.Usuarios;

public class Transformar {

	// Este metodo retorna la sentencia sql para insertar un juego en la base de
	// datos
	public static String insertarJuego(Juegos juego) {

		return "insert into juegos values(null,'" + juego.getNombre() + "', '" + juego.getDescripcion() + "', "
				+ juego.getPrecio() + ", " + juego.getNumExistencias() + ");";

	}

	// Este metodo retorna la sentencia sql para insertar un usuario en la base de
	// datos
	public static String insertarUser(Usuarios user) {

		return "insert into usuarios values(null, '" + user.getNombre() + "', '" + user.getApelllidos() + "', '"
				+ user.getDireccion() + "', '" + user.getNumTelefono() + "');";

	}

	// Este metodo retorna la sentencia sql para insertar un nuevo registro en la
	// tabla juegos_has_usuarios en la base de datos
	public static String insertarIntermedia(Juegoshasusuarios item) {

		return "insert into juegos_has_usuarios values(null, " + item.getJuegos_id() + ", " + item.getUsuarios_id()
				+ ");";
	}

}
