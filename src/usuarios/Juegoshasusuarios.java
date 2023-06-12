package usuarios;

import java.util.Scanner;

public class Juegoshasusuarios {

	private int id;
	private int juegos_id;
	private int usuarios_id;

	public Juegoshasusuarios(int id, int juegos_id, int usuarios_id) {
		super();
		this.id = id;
		this.juegos_id = juegos_id;
		this.usuarios_id = usuarios_id;
	}

	public Juegoshasusuarios(int juegos_id, int usuarios_id) {
		super();
		this.juegos_id = juegos_id;
		this.usuarios_id = usuarios_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJuegos_id() {
		return juegos_id;
	}

	public void setJuegos_id(int juegos_id) {
		this.juegos_id = juegos_id;
	}

	public int getUsuarios_id() {
		return usuarios_id;
	}

	public void setUsuarios_id(int usuarios_id) {
		this.usuarios_id = usuarios_id;
	}

	@Override
	public String toString() {
		return "Juegoshasusuarios [id=" + id + ", juegos_id=" + juegos_id + ", usuarios_id=" + usuarios_id + "]";
	}

	// En este metodo me encargo de actualizar un registro de la tabla
	// juegos_has_usuarios
	public static String update() {
		int id = 0;
		int juegoId = 0;
		int usuarioId = 0;

		Scanner lector = new Scanner(System.in);

		System.out.println("Introduce el id que quieras modificar");
		id = lector.nextInt();
		lector.nextLine();

		System.out.println("Introduce el nuevo ID del juego");
		juegoId = lector.nextInt();
		lector.nextLine();

		System.out.println("Introduce el nuevo ID del usuario");
		juegoId = lector.nextInt();
		lector.nextLine();

		// Esta es la sentencia de SQL para que la entienda la base de datos
		return "UPDATE juegos_has_usuarios SET juegos_id = " + juegoId + ", usuarios_id = " + usuarioId
				+ " WHERE juegos_has_usuarios.id = " + id + ";";
	}

}
