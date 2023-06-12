package usuarios;

import java.util.Scanner;

public class Usuarios {

	private int id;
	private int juegoId;
	private String nombre;
	private String apelllidos;
	private String direccion;
	private String numTelefono;

	public Usuarios(int id, String nombre, String apelllidos, String direccion, String numTelefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apelllidos = apelllidos;
		this.direccion = direccion;
		this.numTelefono = numTelefono;
	}

	public Usuarios(String nombre, String apelllidos, String direccion, String numTelefono) {
		super();
		this.nombre = nombre;
		this.apelllidos = apelllidos;
		this.direccion = direccion;
		this.numTelefono = numTelefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApelllidos() {
		return apelllidos;
	}

	public void setApelllidos(String apelllidos) {
		this.apelllidos = apelllidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", nombre=" + nombre + ", apelllidos=" + apelllidos + ", direccion=" + direccion
				+ ", numTelefono=" + numTelefono + "]";
	}

	// En este metodo me encargo de actualizar un usuario segun el id introducido
	public static String update() {
		final String AZUL = "\u001B[36m";
		final String RESETEAR = "\u001B[0m";
		int id = 0;
		String newNombre = "";
		String newApellido = "";
		String newDireccion = "";
		String newTelefono = "";

		Scanner lector = new Scanner(System.in);

		System.out.println(AZUL + "Introduce el id que quieras modificar" + RESETEAR);
		id = lector.nextInt();
		lector.nextLine();

		System.out.println(AZUL + "Introduce el nuevo nombre" + RESETEAR);
		newNombre = lector.nextLine();

		System.out.println(AZUL + "Introduce el nuevo apellido" + RESETEAR);
		newApellido = lector.nextLine();

		System.out.println(AZUL + "Introduce la nueva direccion" + RESETEAR);
		newDireccion = lector.nextLine();

		System.out.println(AZUL + "Introduce el nuevo numero de telefono" + RESETEAR);
		newTelefono = lector.nextLine();

		// Esta es la sentencia de SQL para que la entienda la base de datos
		return "UPDATE usuarios SET nombre = '" + newNombre + "', apellidos = '" + newApellido + "', direccion = '"
				+ newDireccion + "', num_telefono = '" + newTelefono + "' WHERE usuarios.usuarios_id = " + id + ";";
	}

	// con este metodo me encargo de eliminar un usuario segun el id que se
	// introduzca
	public static String delete() {
		int id = 0;

		Scanner lector = new Scanner(System.in);

		System.out.println("Introduce el id del usuario que quieras eliminar");
		id = lector.nextInt();
		lector.nextLine();

		return "DELETE FROM usuarios WHERE usuarios_id = " + id + ";";
	}

}
