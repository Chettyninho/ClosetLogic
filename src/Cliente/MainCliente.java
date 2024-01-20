package Cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainCliente {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int option;

		// Crear objeto clase Socket
		try {
			for (int i = 0; i < 10; i++) {// prueba del buvle cuando se ponga exit el servidor se cierra
				Socket sk = new Socket("localhost", 3000);
				DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
				// Imprimir bienvenida con formato
				System.out.println("+-------------------------------------------+");
				System.out.println("|    BIENVENIDO A GOODCLOSET - EL ARMARIO    |");
				System.out.println("|    DE LOS AUTÉNTICOS TITANES XD           |");
				System.out.println("|    ELIJA UNA OPCIÓN:                      |");
				System.out.println("|    1. ALTA USUARIO                        |");
				System.out.println("+-------------------------------------------+");

				option = sc.nextInt();
				switch (option) {
				case 1:
					altaUsuarioMainCliente(dos);
					break;
					//ya iremos añadiendo casos , tampoco se si esto ira asi realmente.Miguel
				}
				
				
				dos.close();
				sk.close();
			}
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // definimos el puerto por donde se establece la conexion de escucha

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
		
		String entrada = nombre + "," + apellido + "," + email + "," + nombreUsuario + "," + fechaNacimiento + "," + contraseña; 
		try {
			dos.writeUTF(entrada);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
