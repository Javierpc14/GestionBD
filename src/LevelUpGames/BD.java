package LevelUpGames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BD {

	// Con este metodo muestro todo el contenido de la tabla juegos
	public static void mostrarDatosJuegos() {
		final String AMARILLO = "\u001B[33m";
		final String RESETEAR = "\u001B[0m";
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "Select * from juegos;";

		try {
			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			rs = sentenciaSQL.executeQuery(sql);

			while (rs.next()) {
				// Si hay resultados obtengo el valor.

				System.out.println(AMARILLO + "Id: " + RESETEAR + rs.getInt("juegos_id") + AMARILLO + " Nombre: "
						+ RESETEAR + rs.getString("nombre") + AMARILLO + " Descripcion: " + RESETEAR
						+ rs.getString("descripcion") + AMARILLO + " Precio: " + RESETEAR + rs.getFloat("precio")
						+ AMARILLO + " Num. existencias: " + RESETEAR + rs.getInt("num_existencias"));
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// Con este metodo muestro todo el contenido de la tabla usuarios
	public static void mostrarDatosUsuarios() {
		final String AMARILLO = "\u001B[33m";
		final String RESETEAR = "\u001B[0m";
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "Select * from usuarios;";

		try {
			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			rs = sentenciaSQL.executeQuery(sql);

			while (rs.next()) {
				// Si hay resultados obtengo el valor.

				System.out.println(AMARILLO + "Id: " + RESETEAR + rs.getInt("usuarios_id") + AMARILLO + " Nombre: "
						+ RESETEAR + rs.getString("nombre") + AMARILLO + " Apellido: " + RESETEAR
						+ rs.getString("apellidos") + AMARILLO + " Direccion: " + RESETEAR + rs.getString("direccion")
						+ AMARILLO + " Num. telefono: " + RESETEAR + rs.getString("num_telefono"));
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// Con este metodo muestro todo el contenido de la tabla juegos_has_usuarios
	public static void mostrarDatosIntermedia() {
		final String AMARILLO = "\u001B[33m";
		final String RESETEAR = "\u001B[0m";
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "Select * from juegos_has_usuarios;";

		try {
			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			rs = sentenciaSQL.executeQuery(sql);

			while (rs.next()) {
				// Si hay resultados obtengo el valor.

				System.out.println(AMARILLO + "Id: " + RESETEAR + rs.getInt("id") + AMARILLO + " Juegos ID: " + RESETEAR
						+ rs.getInt("juegos_id") + AMARILLO + " Usuarios ID: " + RESETEAR
						+ rs.getString("usuarios_id"));
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// Metodo para modificar
	public static int modificar(String sql) {
		// TODO Auto-generated method stub
		Connection conexion = null;
		Statement sentenciaSQL = null;
		int resultado = 0;

		try {
			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			resultado = sentenciaSQL.executeUpdate(sql);

		} catch (SQLException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} catch (ClassNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return resultado;

	}

	// Apartado 5 Se puede asociar un T2 a un T1
	public static void asociarUsuarioAJuego() {
		final String AMARILLO = "\u001B[33m";
		final String RESETEAR = "\u001B[0m";
		Connection conexion = null;
		// creo tantos Statement porque son necesarios porque hago consultas a las 3
		// tablas en este método y como necesito un valor de cada tabla hago una
		// variable Statement por tabla para almacenar el valor de cada tabla
		Statement sentenciaSQL = null;
		Statement sentenciaSQLJuegos = null;
		Statement sentenciaSQLUsuarios = null;
		Statement sentenciaSQLInsercion = null;
		// creo estos 2 resultset para poder almacenar el id de la tabla juegos en uno y
		// el id de la tabla usuarios en otro
		ResultSet rsJuegos;
		ResultSet rsUsuarios;
		String sql;
		String nombreJuego;
		String nombreUsuario;
		int juegoId;
		int usuarioId;

		Scanner lector = new Scanner(System.in);

		try {
			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// Solicito el nombre del juego al usuario
			BD.mostrarDatosJuegos();
			System.out.println("\nIntroduce el nombre del juego:");
			nombreJuego = lector.nextLine();

			// Consulto la base de datos para comprobar que exista el juego
			sql = "SELECT * FROM juegos WHERE nombre='" + nombreJuego + "';";
			sentenciaSQLJuegos = conexion.createStatement();

			rsJuegos = sentenciaSQLJuegos.executeQuery(sql);

			if (rsJuegos.next()) {
				// Si existe el juego, el usuario podra asociarlo a un usuario
				BD.mostrarDatosUsuarios();
				System.out.println("Introduce el nombre del usuario:");
				nombreUsuario = lector.nextLine();

				// Consulto la base de datos para comprobar que exista el usuario
				sql = "SELECT * FROM usuarios WHERE nombre='" + nombreUsuario + "';";
				sentenciaSQLUsuarios = conexion.createStatement();

				rsUsuarios = sentenciaSQLUsuarios.executeQuery(sql);

				if (rsUsuarios.next()) {
					// Si existe el usuario, lo asocio a un juego
					sql = "INSERT INTO juegos_has_usuarios (id, juegos_id, usuarios_id) VALUES (null, "
							+ rsJuegos.getInt("juegos_id") + ", " + rsUsuarios.getInt("usuarios_id") + ");";
					sentenciaSQLInsercion = conexion.createStatement();

					sentenciaSQLInsercion.executeUpdate(sql);
					System.out.println("El usuario " + nombreUsuario + " ha sido asociado al juego " + nombreJuego
							+ " correctamente");
				} else {
					System.out.println("El usuario " + nombreUsuario + " no existe en la base de datos.");
				}
			} else {
				System.out.println("El juego " + nombreJuego + " no existe en la base de datos.");
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	// Apartado 6 Dado un usuario me dice que juegos tiene
	public static void usuarioDadoJuego() {
		final String AMARILLO = "\u001B[33m";
		final String RESETEAR = "\u001B[0m";
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int id = 0;

		Scanner lector = new Scanner(System.in);

		try {

			BD.mostrarDatosUsuarios();
			System.out.println();

			System.out.println("Introduce el id del usuario que quieras comprobar");
			id = lector.nextInt();
			lector.nextLine();

			String sql = "select juegos.*\r\n" + "from juegos\r\n" + "inner join juegos_has_usuarios\r\n"
					+ "on juegos_has_usuarios.juegos_id = juegos.juegos_id\r\n"
					+ "where juegos_has_usuarios.usuarios_id = " + id + ";";

			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			rs = sentenciaSQL.executeQuery(sql);

			// Si hay resultados obtengo el valor.
			// if () {
			while (rs.next()) {
				System.out.println(AMARILLO + "Id: " + RESETEAR + rs.getInt("juegos_id") + AMARILLO + " Nombre: "
						+ RESETEAR + rs.getString("nombre") + AMARILLO + " Descripcion: " + RESETEAR
						+ rs.getString("descripcion") + AMARILLO + " Precio: " + RESETEAR + rs.getFloat("precio")
						+ AMARILLO + " Num. existencias: " + RESETEAR + rs.getInt("num_existencias"));
			}
			// } else {
			// System.out.println("El usuario con id = " + id + " no tiene ningun juego");
			// }
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// Apartado 7 Introduce un juego y me dice que usuarios lo tienen
	public static void JuegoDadoUsuario() {
		final String AMARILLO = "\u001B[33m";
		final String RESETEAR = "\u001B[0m";
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql;
		int id = 0;

		Scanner lector = new Scanner(System.in);

		try {

			BD.mostrarDatosJuegos();
			System.out.println();

			System.out.println("Introduce el id del juego que quieras comprobar");
			id = lector.nextInt();
			lector.nextLine();

			System.out.println("El juego con ID = " + id + " lo tienen:");

			// le doy el valor de la consulta a la variable sql
			sql = "Select usuarios.*\r\n" + "from usuarios\r\n" + "inner join juegos_has_usuarios\r\n"
					+ "on juegos_has_usuarios.usuarios_id = usuarios.usuarios_id\r\n"
					+ "where juegos_has_usuarios.juegos_id = " + id + ";";

			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			rs = sentenciaSQL.executeQuery(sql);

			// Si hay resultados obtengo el valor.
			while (rs.next()) {
				System.out.println(AMARILLO + "Id: " + RESETEAR + rs.getInt("usuarios_id") + AMARILLO + " Nombre: "
						+ RESETEAR + rs.getString("nombre") + AMARILLO + " Apellido: " + RESETEAR
						+ rs.getString("apellidos") + AMARILLO + " Direccion: " + RESETEAR + rs.getString("direccion")
						+ AMARILLO + " Num. telefono: " + RESETEAR + rs.getString("num_telefono"));
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// Apartado 8
	public static void mostrarNumJuegos() {
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "select count(*) from juegos;";

		Scanner lector = new Scanner(System.in);

		try {

			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			rs = sentenciaSQL.executeQuery(sql);
			rs.next();
			// Si hay resultados obtengo el valor.
			System.out.println("Hay un total de " + rs.getInt(1) + " juegos");

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// Apartado 9 Que usuarios ha comprado mas juegos
	public static void UsuariosMasJuegos() {
		final String AMARILLO = "\u001B[33m";
		final String RESETEAR = "\u001B[0m";
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		String sql = "select usuarios.*\r\n" + "from juegos_has_usuarios\r\n" + "inner join usuarios\r\n"
				+ "on juegos_has_usuarios.usuarios_id = usuarios.usuarios_id \r\n"
				+ "group by juegos_has_usuarios.usuarios_id order by count(*) desc limit 1;";

		Scanner lector = new Scanner(System.in);

		try {

			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			rs = sentenciaSQL.executeQuery(sql);
			rs.next();
			// Si hay resultados obtengo el valor.
			System.out.println("El usuario que mas ha comprado es:");
			System.out.println(AMARILLO + "Id: " + RESETEAR + rs.getInt("usuarios_id") + AMARILLO + " Nombre: "
					+ RESETEAR + rs.getString("nombre") + AMARILLO + " Apellido: " + RESETEAR
					+ rs.getString("apellidos") + AMARILLO + " Direccion: " + RESETEAR + rs.getString("direccion")
					+ AMARILLO + " Num. telefono: " + RESETEAR + rs.getString("num_telefono"));

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	// Apartado 10 Cuantos juegos no han sido comprados por usuarios
	public static void juegosNoComprados() {
		final String AMARILLO = "\u001B[33m";
		final String RESETEAR = "\u001B[0m";
		Connection conexion = null;
		Statement sentenciaSQL = null;
		ResultSet rs;
		int contador = 0;
		String sql = "select *" + "from juegos where juegos_id not in(select juegos_id from juegos_has_usuarios);";

		Scanner lector = new Scanner(System.in);

		try {

			// conectar con la base de datos
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/levelupgames", "root", "");

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			rs = sentenciaSQL.executeQuery(sql);
			System.out.println("Los juegos que no han sido vendidos son:");
			while (rs.next()) {
				// Si hay resultados obtengo el valor.
				System.out.println(AMARILLO + "Id: " + RESETEAR + rs.getInt("juegos_id") + AMARILLO + " Nombre: "
						+ RESETEAR + rs.getString("nombre") + AMARILLO + " Descripcion: " + RESETEAR
						+ rs.getString("descripcion") + AMARILLO + " Precio: " + RESETEAR + rs.getFloat("precio")
						+ AMARILLO + " Num. existencias: " + RESETEAR + rs.getInt("num_existencias"));
				contador++;
			}
			if(contador == 0) {
				System.out.println("No hay ningun juego sin vender");
			}
			
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} finally {
			try {
				sentenciaSQL.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
