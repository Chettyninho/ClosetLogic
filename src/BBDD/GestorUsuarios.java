package BBDD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import Tablas.Usuarios;

public class GestorUsuarios {
	private ArrayList<Usuarios> usersList = new ArrayList<Usuarios>();

////////////////////////////      TAMLA DE USUARIOS          //////////////////////////////////////////////

	public ArrayList<Usuarios> altaUsuario( ArrayList<Usuarios> listaUsuarios, String[] data) {

		// variables que generamos: id, hass y contraseñaHasseada:
		boolean existe = false;

		// aqui comprobamos si existe
		if (comprobarUsuario(data)) {
			existe = true;
		} else {
			int ultimoIdBbdd = comprobarUltimoIdBbdd();// este id valdra para guardarlo en java, en mySQL se incrementa
													  // solo
			// comprobar ultimo id de la bbdd
			byte[] salt = generateSalt();
			// generar el hass
			String hassPswd = hashPassword(data[5], salt);
			// generar la contraseñaHasseada
			Usuarios usuario = new Usuarios(ultimoIdBbdd+1, data[0], data[1], data[2], data[3], data[4], hassPswd, salt);
			// introducir a bbdd y a la clase java
			listaUsuarios.add(usuario);
			
			InsertarBBDD(usuario);
		}
return listaUsuarios;
	}

	private static boolean comprobarUsuario(String[] data) {// esta funcion la haremos privada para comprobar si existe
		boolean coincide = false; // o no existe en la bbdd y solo entonces se le dará de alta.

		try (Conexion con = Conexion.getInstance()) { // si YA EXISTE se lanza excepcion(?)(Donde gestionamos las
														// excepciones?))

			String query = "SELECT * FROM USUARIOS WHERE nombre = ?";
			PreparedStatement ps = con.getConnection().prepareStatement(query);

			// Establece el valor del parámetro
			ps.setString(1, data[0]);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String pswcheck = rs.getString("hashContraseña");

				// Compara la contraseña proporcionada con la almacenada en la base de datos
				if (data[5].equals(pswcheck)) {
					coincide = true;

					System.out.println("Usuario y contraseña coinciden.");
				} else {
					System.out.println("Contraseña incorrecta.");
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return coincide;
	}

	private static int comprobarUltimoIdBbdd() {

		int ultimoId = -1;
		String query = "SELECT LAST_INSERT_ID()";

		try (Conexion con = Conexion.getInstance()) {
			PreparedStatement ps = con.getConnection().prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ultimoId = rs.getInt(1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return ultimoId;
	}

	private static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16]; // Tamaño recomendado para el salt: 16 bytes
		random.nextBytes(salt);
		return salt;
	}

	private static String hashPassword(String password, byte[] salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// Agregar el salt a la contraseña antes de aplicar el hash
			md.update(salt);
			byte[] hashedPassword = md.digest(password.getBytes());

			// Convertir el hash a una representación en formato Base64
			return Base64.getEncoder().encodeToString(hashedPassword);
		} catch (NoSuchAlgorithmException e) {
			// Manejar la excepción apropiadamente
			e.printStackTrace();
			return null;
		}
	}
	
	private static void InsertarBBDD(Usuarios usuario) {
	    try (Conexion con = Conexion.getInstance()) {
	        String query = "INSERT INTO USUARIOS (Nombre, Surname, Email, userName, fechaNacimiento, hashContraseña, Salt) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = con.getConnection().prepareStatement(query);

	        ps.setString(1, usuario.getNombre());
	        ps.setString(2, usuario.getSurname());
	        ps.setString(3, usuario.getEmail());
	        ps.setString(4, usuario.getUserName());
	        ps.setString(5, usuario.getFechaNacimiento());
	        ps.setString(6, usuario.getHashContraseña());
	        ps.setBytes(7, usuario.getSalt());

	        // Ejecutar la consulta de inserción
	        int filasAfectadas = ps.executeUpdate();

	        if (filasAfectadas > 0) {
	            System.out.println("Usuario insertado correctamente en la base de datos.");
	        } else {
	            System.out.println("Error al insertar el usuario en la base de datos.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	}

}
