package Cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainCliente {
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//Crear objeto clase Socket
		try {
			for (int i = 0; i < 10; i++) {//  prueba del buvle cuando se ponga exit el servidor se cierra
				Socket sk = new Socket("localhost",3000);
				DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
				
				System.out.println("Esto es una prueba de C-S, introduzca su usuario y contrasena: ");
				System.out.println("USUARIO:");
				String entradaUser = sc.next();
				
				System.out.println("CONTRASENA: ");
				String entradaPassword = sc.next();
				
				String entrada = entradaUser + "," + entradaPassword;
				
				dos.writeUTF(entrada);
				
				dos.close();
				sk.close();
			}
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //definimos el puerto por donde se establece la conexion de escucha

	}

}
