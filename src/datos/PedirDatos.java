package datos;

import java.util.Scanner;

import usuarios.Juegos;
import usuarios.Juegoshasusuarios;
import usuarios.Usuarios;

public class PedirDatos {

	// En este metodo pido todos los datos necesarios para crear un objeto nuevo de
	// un juego
	public static Juegos pedirJuego() {
		final String CYAN = "\u001B[36m";
		final String RESETEAR = "\u001B[0m";
		Juegos juego;
		String nombre;
		String descripcion;
		float precio;
		int numExist;

		Scanner lector = new Scanner(System.in);

		System.out.println(CYAN + "Introduce el nombre" + RESETEAR);
		nombre = lector.nextLine();

		System.out.println(CYAN + "Introduce una pequeña descripcion" + RESETEAR);
		descripcion = lector.nextLine();

		System.out.println(CYAN + "Introduce el precio" + RESETEAR);
		precio = lector.nextFloat();
		lector.nextLine();

		System.out.println(CYAN + "Introduce el numero de existencias" + RESETEAR);
		numExist = lector.nextInt();
		lector.nextLine();

		juego = new Juegos(nombre, descripcion, precio, numExist);

		return juego;
	}

	// En este metodo pido todos los datos necesarios para crear un objeto nuevo de
	// un usuario
	public static Usuarios pedirUsuario() {
		final String CYAN = "\u001B[36m";
		final String RESETEAR = "\u001B[0m";
		Usuarios user;
		String nombre;
		String apellido;
		String direccion;
		String telefono;

		Scanner lector = new Scanner(System.in);

		System.out.println(CYAN + "Introduce el nombre" + RESETEAR);
		nombre = lector.nextLine();

		System.out.println(CYAN + "Introduce el apellido" + RESETEAR);
		apellido = lector.nextLine();

		System.out.println(CYAN + "Introduce la direccion" + RESETEAR);
		direccion = lector.nextLine();

		System.out.println(CYAN + "Introduce el numero de telefono" + RESETEAR);
		telefono = lector.nextLine();

		user = new Usuarios(nombre, apellido, direccion, telefono);

		return user;
	}

	// En este metodo pido todos los datos necesarios para crear un objeto nuevo de
	// un juego_has_usuario
	public static Juegoshasusuarios Juegos_has_usuarios() {
		final String CYAN = "\u001B[36m";
		final String RESETEAR = "\u001B[0m";
		Juegoshasusuarios intermedia;
		int juegoId = 0;
		int usuarioId = 0;

		Scanner lector = new Scanner(System.in);

		System.out.println(CYAN + "Introduce el ID del Juego que quieras asociar a un usuario" + RESETEAR);
		juegoId = lector.nextInt();
		lector.nextLine();

		System.out.println(
				CYAN + "Introduce el ID del usuario que quieras asociar al juego con id = " + juegoId + RESETEAR);
		usuarioId = lector.nextInt();
		lector.nextLine();

		intermedia = new Juegoshasusuarios(juegoId, usuarioId);

		return intermedia;
	}

}
