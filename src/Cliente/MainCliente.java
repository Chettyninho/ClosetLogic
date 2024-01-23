package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import Tablas.Usuarios;

public class MainCliente {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int option;

		// Crear objeto clase Socket
		try (Socket sk = new Socket("localhost", 3000);
	             DataOutputStream dos = new DataOutputStream(sk.getOutputStream())) {
			
			for (int i = 0; i < 10; i++) {// prueba del buvle cuando se ponga exit el servidor se cierra
				//Socket sk = new Socket("localhost", 3000);
				//DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
				// Imprimir bienvenida con formato
				System.out.println("+-------------------------------------------+");
				System.out.println("|    BIENVENIDO A GOODCLOSET - EL ARMARIO   |");
				System.out.println("|    DE LOS AUTÉNTICOS TITANES XD           |");
				System.out.println("|    ELIJA UNA OPCIÓN:                      |");
				System.out.println("|    1. ALTA USUARIO                        |");
				System.out.println("+-------------------------------------------+");

				option = sc.nextInt();
				System.out.println(option + "En main cliente");
				switch (option) {
				case 1:
					dos.writeUTF(String.valueOf(option));
					altaUsuarioMainCliente(dos);
					break;
				case 2:
					dos.writeUTF(String.valueOf(option));
					 DataInputStream dis = new DataInputStream(sk.getInputStream());
		             listarUsuarios(dis);
					break;
				// ya iremos añadiendo casos , tampoco se si esto ira asi realmente.Miguel
				}
				
//				dos.close();
//				sk.close();
			}
			// Después del bucle for en el cliente
			dos.close();
			sk.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
            ArrayList<Usuarios> listaUsuarios = (ArrayList<Usuarios>) ois.readObject();

            // Mostrar la lista de usuarios en la consola
            System.out.println("Lista de usuarios:");
            for (Usuarios usuario : listaUsuarios) {
              System.out.println(usuario.toString());
            }
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
