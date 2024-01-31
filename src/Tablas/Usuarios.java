package Tablas;

import java.io.Serializable;

public class Usuarios implements Serializable{
	private int id;
	private String nombre;
	private String surname;
	private String email;
	private String userName;
	private String fechaNacimiento;// no incluida en la bbdd del anterior commit, al igual que contraseñaHasseada y
									// salt

	private String hashContrasena;// asegurarse de que en la bbdd existan estos dos camposs
	private byte[] salt;// numero que añade seguridad, se aplicará en el hasseo.

	public Usuarios(int id, String nombre, String surname, String email, String userName, String fechaNacimiento,
			String hashContraseña, byte[] salt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.surname = surname;
		this.email = email;
		this.userName = userName;
		this.fechaNacimiento = fechaNacimiento;
		this.hashContrasena = hashContraseña;
		this.salt = salt;
	}

	// Getter && setter

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getHashContraseña() {
		return hashContrasena;
	}

	public byte[] getSalt() {
		return salt;
	}

	@Override
	public String toString() {
	    return String.format(" id=%s, \n| nombre=%s, \n| surname=%s, \n| email=%s, \n| userName=%s, \n| fechaNacimiento=%s, \n| hashContraseña=%s\n-",
	            id, nombre, surname, email, userName, fechaNacimiento, hashContrasena);
	}
}
