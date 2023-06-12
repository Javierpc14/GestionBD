package LevelUpGames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import datos.PedirDatos;
import datos.Transformar;
import usuarios.Juegos;
import usuarios.Juegoshasusuarios;
import usuarios.Usuarios;

public class Main {

	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente

		/**
		 * Hacer relacciones entre tablas (n,m) que se crea tabla intermedia select *
		 * from T1 inner join T1T2 on T1.idT1 = T1T2.idT1 inner join T2 on T1T2.idT2 =
		 * T2.idT2 where ...
		 */

		/**
		 * Estrapolar al mio RECETAS INGREDIENTES varias recetas pueden tener varios
		 * ingredientes, varios ingredientes tienen varias recetas (n,m) T1 ---> Recetas
		 * T2 ---> Ingredientes
		 *
		 * Requisitos:
		 * 
		 * --1 - Tiene que insertar en la entidad T1 
		 * --2 - Tiene que insertar en la T2 asociado a la T1 --> Cuando creo un usuario tiene que asociarse a un juego y si el usuario ya existe se tiene que poder asociar a un juego 
		 * --3 - Se tiene que poder borrar de la T2 directamente y si borro de la T1 implica que borro de la T1T2
		 * --4 - Modificar en la T1 y en la T2 excepto los campos id, estos na vez creados no se pueden modificar
		 * --5 - Se puede asociar un T2 a un T1 (es decir puedo asociar un ingrediente a una nueva receta si ya existe)
		 * --6 - Dado un ingrediente (T2) me dice las recetas en las que esta (T1) --> Dado un usuario me dice que juegos tiene
		 * --7 - Pido una receta concreta (T1) y me dice que ingredientes tiene (T2) --> Introduce un juego y me dice que usuarios lo tienen
		 * --8 - Me tiene que decir cuantas recetas hay (T1)
		 * --9 - Me tiene que decir cual es el ingrediente mas usado (T2) --> Que usuarios ha comprado mas juegos
		 * --10 - Tiene que decir las recetas (T1) sin ingredientes --> Cunatos juegos no han sido comprados por usuarios
		 */

		int opcion;

		Scanner lector = new Scanner(System.in);

		do {
			System.out.println("Introduce la opcion a realizar:");
			System.out.println("1  - Insertar un nuevo videojuego o Usuario");
			System.out.println("2  - Asocia un usuario a un juego");
			System.out.println("3  - Borrar contenido de las tablas Usuarios o Juegos");
			System.out.println("4  - Modificar tabla Juegos o Usuarios");
			System.out.println("5  - Asocia un usuario a un juego solo si el juego y el usuario existen");
			System.out.println("6  - Segun un usuario dado muestra los juegos que tiene");
			System.out.println("7  - Segun un juego dado muestra los usuarios que lo tienen");
			System.out.println("8  - Mostrar el numero de cuantos juegos hay");
			System.out.println("9  - Muestra el usuario que mas ha comprado");
			System.out.println("10 - Muestra los juegos que no han sido comprados por algun usuario");
			System.out.println("11 - Salir");
			opcion = lector.nextInt();
			lector.nextLine();

			switch (opcion) {
			case 1:
				Main.opcion1();
				break;
			case 2:
				Main.opcion2();
				break;
			case 3:
				// Triggers hechos en la base de datos
				Main.opcion3();
				System.out.println();
				break;
			case 4:
				Main.opcion4();
				System.out.println();
				break;
			case 5:
				BD.asociarUsuarioAJuego();
				System.out.println();
				break;
			case 6:
				BD.usuarioDadoJuego();
				System.out.println();
				break;
			case 7:
				BD.JuegoDadoUsuario();
				System.out.println();
				break;
			case 8:
				BD.mostrarNumJuegos();
				System.out.println();
				break;
			case 9:
				BD.UsuariosMasJuegos();
				System.out.println();
				break;
			case 10:
				BD.juegosNoComprados();
				System.out.println();
				break;
			}

		} while (opcion != 11);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");

			conexion.close();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}
	
	public static void opcion1() {
		int opcion;
		String sql = "";
		Juegos miJuego;
		Usuarios miUser;
		
		Scanner lector = new Scanner (System.in);
		
		System.out.println("¿Que deseas insertar?");
		System.out.println("1 - Juego");
		System.out.println("2 - Usuario");
		opcion = lector.nextInt();
		lector.nextLine();
		
		switch(opcion) {
		case 1:
			miJuego = PedirDatos.pedirJuego();
			sql = Transformar.insertarJuego(miJuego);
			BD.modificar(sql);
			System.out.println();
			break;
		case 2:
			miUser = PedirDatos.pedirUsuario();
			sql = Transformar.insertarUser(miUser);
			BD.modificar(sql);
			System.out.println();
			break;
		}
	}

	public static void opcion2() {
		String sql = "";
		Juegoshasusuarios intermedia;

		Scanner lector = new Scanner(System.in);
		
		// Muestro el contenido de las tablas Juegos y Usuarios para que sea mas facil para el usuario
		System.out.println("JUEGOS");
		BD.mostrarDatosJuegos();
		System.out.println("\nUSUARIOS");
		BD.mostrarDatosUsuarios();
		System.out.println();

		intermedia = PedirDatos.Juegos_has_usuarios();
		sql = Transformar.insertarIntermedia(intermedia);
		BD.modificar(sql);
		
		System.out.println("El juego se ha asociado perfectamente al usuario\n");

	}
	
	public static void opcion3() {
		int opcion;
		String sql = "";
		Juegoshasusuarios intermedia;
		
		Scanner lector = new Scanner (System.in);
		
		System.out.println("¿Que deseas eliminar?");
		System.out.println("1 - Usuario");
		System.out.println("2 - Juego");
		opcion = lector.nextInt();
		lector.nextLine();
		
		switch(opcion) {
		case 1:
			BD.mostrarDatosUsuarios();
			sql = Usuarios.delete();
			BD.modificar(sql);
			System.out.println();
			break;
		case 2:
			BD.mostrarDatosJuegos();
			sql = Juegos.delete();
			BD.modificar(sql);
			System.out.println();
			break;
		}
		
		
	}

	public static void opcion4() {
		int opcion;
		String sql = "";

		Scanner lector = new Scanner(System.in);

		System.out.println("Selecciona la tabla que quieras modificar");
		System.out.println("1 - Juegos");
		System.out.println("2 - Usuarios");
		opcion = lector.nextInt();
		lector.nextLine();

		switch (opcion) {
		case 1:
			BD.mostrarDatosJuegos();
			System.out.println();
			sql = Juegos.update();
			BD.modificar(sql);
			break;
		case 2:
			BD.mostrarDatosUsuarios();
			System.out.println();
			sql = Usuarios.update();
			BD.modificar(sql);
			break;
		}
	}

}
