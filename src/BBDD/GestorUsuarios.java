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
	 private ArrayList<Usuarios> usersListRead = new ArrayList<Usuarios>();

////////////////////////////      TAMLA DE USUARIOS          //////////////////////////////////////////////

	public ArrayList<Usuarios> altaUsuario(ArrayList<Usuarios> listaUsuarios, String[] data) {

		// variables que generamos: id, hass y contraseñaHasseada:
		// boolean existe = false;

		// aqui comprobamos si existe
		if (!comprobarUsuario(data)) {
			// existe = true;
			int ultimoIdBbdd = comprobarUltimoIdBbdd();// este id valdra para guardarlo en java, en mySQL se incrementa
			// solo
			// comprobar ultimo id de la bbdd
			byte[] salt = generateSalt();
			// generar el hass
			String hassPswd = hashPassword(data[5], salt);
			// generar la contraseñaHasseada

			Usuarios usuario = new Usuarios(ultimoIdBbdd + 1, data[0], data[1], data[2], data[3], data[4], hassPswd,
					salt);
			// introducir a bbdd y a la clase java
			listaUsuarios.add(usuario);

			InsertarBBDD(usuario);

		} else {
			System.out.println("ususario existente principal flujo");
		}
		return listaUsuarios;
	}

	private static boolean comprobarUsuario(String[] data) {
		boolean coincide = false;

		try (Conexion con = Conexion.getInstance()) {
			String query = "SELECT * FROM USUARIO WHERE nombre = ?";
			PreparedStatement ps = con.getConnection().prepareStatement(query);

			ps.setString(1, data[0]);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString(2);
				// System.out.println(nombre);
				// System.out.println("DataArray = " + data[0]);
				String apellido = rs.getString(3);
				// System.out.println(apellido);
				// System.out.println("DataArray = " + data[1]);
				String correo = rs.getString(4);
				// System.out.println(correo);
				// System.out.println("DataArray = " + data[2]);

				if (data[0].equals(nombre) && data[1].equals(apellido) && data[2].equals(correo)) {
					coincide = true;
					System.out.println("Este usuario existía anteriormente, no debería darse de alta.");
				} else {
					System.out.println("Esto es la comprobación, usuario no existente, dar de alta. GestorUsuarios");
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

			// Convertir el hash a una representación en formato Base64 para meterlo a la bbdd
			return Base64.getEncoder().encodeToString(hashedPassword);
		} catch (NoSuchAlgorithmException e) {
			// Manejar la excepción apropiadamente
			e.printStackTrace();
			return null;
		}
	}

	private static void InsertarBBDD(Usuarios usuario) {
		try (Conexion con = Conexion.getInstance()) {
			String query = "INSERT INTO USUARIO (Nombre, Surname, Email, user_name, fecha_nacimiento, hash_contrasena, Salt) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

	
	public ArrayList<Usuarios> leerBBDDUsuario() {
		try (Conexion con = Conexion.getInstance()) {
			String query = "SELECT * FROM USUARIO";
			
			 try (PreparedStatement ps = con.getConnection().prepareStatement(query);
		             ResultSet rs = ps.executeQuery()) {
				 while (rs.next()) {
		                // Obtener datos del resultado y construir un objeto Usuarios
		                int id = rs.getInt("id");
		                String nombre = rs.getString("nombre");
		                String surname = rs.getString("surname");
		                String email = rs.getString("email");
		                String userName = rs.getString("user_name");
		                String fechaNacimiento = rs.getString("fecha_nacimiento");
		                String hashContraseña = rs.getString("hash_contrasena");
		                byte[] salt = rs.getBytes("salt");

		                Usuarios usuario = new Usuarios(id, nombre, surname, email, userName, fechaNacimiento, hashContraseña, salt);
		                usersListRead.add(usuario);
		            }
				 //System.out.println("Se ha leído la base de datos de usuarios.");
		        } catch (SQLException e) {
		            e.printStackTrace();
		            System.out.println("Error al leer la base de datos de usuarios.");
		        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return usersListRead;
	}

	public void borrarUsuarioGestorUsuarios(int idUsuario) {
		try (Conexion con = Conexion.getInstance()) {
			String query = "DELETE FROM USUARIO WHERE id = ?";
			PreparedStatement ps = con.getConnection().prepareStatement(query);
System.err.println("id usuario es en gestor usuarios = " +idUsuario);
			ps.setInt(1, idUsuario);

//			ResultSet rs = ps.executeQuery();
			int filasAfectadas = ps.executeUpdate();
				if (filasAfectadas > 0) {
					System.out.println("Usuario ELIMINADO correctamente en la base de datos.");
					//falta eliminar de el arraylist de usuarios el usuario eliminado: 
				} else {
					System.out.println("Error al ELIMINAR el usuario en la base de datos.");
				}
//			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
