package Servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import BBDD.Fachada;
import Tablas.Usuarios;

public class MainServidor {

	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(3000)) {
			String mensaje = "";

			do {
				System.out.println("Estado : ON");

				try (Socket socketCliente = serverSocket.accept();
                        DataInputStream dis = new DataInputStream(socketCliente.getInputStream())) {
                    int opcion = Integer.parseInt(dis.readUTF());
                    
                    System.out.println(opcion + "en servidor");// Lee la opción enviada por el cliente
                    
                    procesarOpcion(opcion, socketCliente);
                } catch (IOException e) {
                    e.printStackTrace();
                }

			} while (!mensaje.equals("exit"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 private static void procesarOpcion(int opcion, Socket socketCliente) {
		 System.out.println(opcion);
	        switch (opcion) {
	            case 1:
	            	altaUserServer(socketCliente);
	                break;
	            case 2:
	            	System.out.println("entra en 2");
	                leerUsuariosDesdeBBDD(socketCliente);
	                break;
	            default:
	                System.out.println("Opción no válida.");
	        }
	        try {
				socketCliente.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	private static void altaUserServer(Socket socketCliente) {
		try {
			DataInputStream dis = new DataInputStream(socketCliente.getInputStream());
			String mensaje = dis.readUTF();
			String[] data = mensaje.split(",");
			Fachada f = new Fachada();
			f.altaUsuarioFachada(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void leerUsuariosDesdeBBDD(Socket socketCliente) {
        Fachada f = new Fachada();
        ArrayList<Usuarios> listaUsuarios = f.leerUsuarioFachada();

//        // Ahora puedes hacer lo que quieras con la lista de usuarios
//        System.out.println("Lista de usuarios leída desde la base de datos:");
//        for (Usuarios usuario : listaUsuarios) {
//            System.out.println(usuario.toString());
//        }
//        
     // Enviar la lista al cliente
        ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(socketCliente.getOutputStream());
			oos.writeObject(listaUsuarios);
	        oos.flush();
		} catch (IOException e) {
			System.out.println("\n\t FALLO EN EL OUTPOUTsTREAM\n\n");
			e.printStackTrace();
		}
        
    }
}
