package usuarios;

import java.util.Scanner;

public class Juegos {

	private int juegosId;
	private String nombre;
	private String descripcion;
	private float precio;
	private int numExistencias;

	public Juegos(int juegosId, String nombre, String descripcion, float precio, int numExistencias) {
		super();
		this.juegosId = juegosId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numExistencias = numExistencias;
	}

	public Juegos(String nombre, String descripcion, float precio, int numExistencias) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.numExistencias = numExistencias;
	}

	public int getJuegosId() {
		return juegosId;
	}

	public void setJuegosId(int juegosId) {
		this.juegosId = juegosId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getNumExistencias() {
		return numExistencias;
	}

	public void setNumExistencias(int numExistencias) {
		this.numExistencias = numExistencias;
	}

	@Override
	public String toString() {
		return "Juegos [juegosId=" + juegosId + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio="
				+ precio + ", numExistencias=" + numExistencias + "]";
	}

	// En este metodo me encargo de actualizar un juego segun el id introducido
	public static String update() {
		final String AZUL = "\u001B[36m";
		final String RESETEAR = "\u001B[0m";
		int id = 0;
		int newNumExi = 0;
		float newPrecio = 0;
		String newNombre = "";
		String newDescripcion = "";

		Scanner lector = new Scanner(System.in);

		System.out.println(AZUL + "Introduce el id que quieras modificar" + RESETEAR);
		id = lector.nextInt();
		lector.nextLine();

		System.out.println(AZUL + "Introduce el nuevo nombre" + RESETEAR);
		newNombre = lector.nextLine();

		System.out.println(AZUL + "Introduce la nueva descripcion" + RESETEAR);
		newDescripcion = lector.nextLine();

		System.out.println(AZUL + "Introduce el nuevo precio" + RESETEAR);
		newPrecio = lector.nextFloat();
		lector.nextLine();

		System.out.println(AZUL + "Introduce el nuevo Num. de existencias" + RESETEAR);
		newNumExi = lector.nextInt();
		lector.nextLine();

		// Esta es la sentencia de SQL para que la entienda la base de datos
		return "UPDATE juegos SET nombre = '" + newNombre + "', descripcion = '" + newDescripcion + "', precio = "
				+ newPrecio + ", num_existencias = " + newNumExi + " WHERE juegos.juegos_id = " + id + ";";
	}

	public static String delete() {
		int id = 0;

		Scanner lector = new Scanner(System.in);

		System.out.println("Introduce el id del juego que quieras eliminar");
		id = lector.nextInt();
		lector.nextLine();

		return "DELETE FROM juegos WHERE juegos_id = " + id + ";";
	}

}
