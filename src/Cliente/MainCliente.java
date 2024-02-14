package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Tablas.Usuarios;

public class MainCliente {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int option;
		boolean salir = false;

		try (Socket sk = new Socket("localhost", 9995);
				DataOutputStream dos = new DataOutputStream(sk.getOutputStream())) {
			do {
			System.out.println("+--------------------------------------------+");
			System.out.println("|    BIENVENIDO A GOODCLOSET - EL ARMARIO    |");
			System.out.println("|    DE LOS AUTÉNTICOS TITANES XD            |");
			System.out.println("|    ELIJA UNA OPCIÓN:                       |");
			System.out.println("|    1. ALTA USUARIO                         |");
			System.out.println("|    2. LISTAR USUARIOS                      |");
			System.out.println("|    3. MODIFICAR MI USUARIO                 |");
			System.out.println("|    4. ELIMINAR USUARIO 		     |");
			System.out.println("|    5. SALIR           		     |");
			System.out.println("+--------------------------------------------+");

			if (sc.hasNextInt()) {
				option = sc.nextInt();
				System.out.println(option + " En main cliente");

				switch (option) {
				case 1:
					dos.writeUTF(String.valueOf(option)); // altasUsuario
					altaUsuarioMainCliente(dos);
					break;
				case 2:
				    dos.writeUTF(String.valueOf(option));
				    DataInputStream disListar = new DataInputStream(sk.getInputStream()); // Nuevo flujo de entrada para LISTAR USUARIOS
				    listarUsuarios(disListar);
				    disListar.close(); // Cerrar nuevo DataInputStream después de usarlo
				    break;
				case 3:
				    dos.writeUTF(String.valueOf(option));
				    DataInputStream disModificar = new DataInputStream(sk.getInputStream());
				    listarUsuarios(disModificar);
				    

				    // Lógica para modificar usuario
				    modificarUsuarioMainCliente(dos, sk);
				    disModificar.close();
				    break;

				case 4:
					dos.writeUTF(String.valueOf(option));
					borrarUsuario(dos);
					break;
				case 5:
					salir=true;
					break;
				default:
					System.out.println("Opción no válida. Introduzca un número del 1 al 4.");
					break;
				}
				while(!salir); // ALBERTO NO ME ENTRA EN BUCLE Y NO SE POR QUÉ, TE LO MANDO UN POCO CON LAS PRISAS.
			} else {
				System.out.println("Entrada no válida. Debe ingresar un número.");
			}

			// Cierre del cliente
			dos.close();
			sk.close();
			
			
				
			}while(!salir);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}


	private static void altaUsuarioMainCliente(DataOutputStream dos) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("¡Bienvenido al registro de usuario!");

		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();

		System.out.print("Apellido: ");
		String apellido = scanner.nextLine();

		System.out.print("Correo electrónico: ");
		String email = scanner.nextLine();

		System.out.print("Nombre de usuario: ");
		String nombreUsuario = scanner.nextLine();

		System.out.print("Fecha de nacimiento: ");
		String fechaNacimiento = scanner.nextLine();

		System.out.print("Contraseña: ");
		String contraseña = scanner.nextLine();

		String entrada = nombre + "," + apellido + "," + email + "," + nombreUsuario + "," + fechaNacimiento + ","
				+ contraseña;

		try {
			dos.writeUTF(entrada);
			dos.writeInt(1);
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}
	private static void listarUsuarios(DataInputStream dis) {
		try {
			ObjectInputStream ois = new ObjectInputStream(dis);
			@SuppressWarnings("unchecked") // No se para qué vale esto la verdad, saludos
			ArrayList<Usuarios> listaUsuarios = (ArrayList<Usuarios>) ois.readObject();

			System.out.println("Lista de usuarios:");
			for (Usuarios usuario : listaUsuarios) {
				System.out.println(usuario.toString());
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void borrarUsuario(DataOutputStream dos) {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Introduzca el id del usuario que quiere eliminar:");
			int idDelete = sc.nextInt();
			dos.writeInt(idDelete);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
	
	
	private static void modificarUsuarioMainCliente(DataOutputStream dos, Socket sk) {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Introduce el id del ususario que quiera modificar");
	    int id = sc.nextInt();
	    
	    System.out.println("Nuevo nombre:");
	    String newName = sc.next();

	    try {
			dos.writeInt(id);
			dos.flush();
	        dos.writeUTF(newName);
	        dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    
	}

}
