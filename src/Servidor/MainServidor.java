package Servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import BBDD.Fachada;

public class MainServidor {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            String mensaje = "";

            do {
                System.out.println("Estado : ON");
                
                try (Socket socketCliente = serverSocket.accept();
                      DataInputStream dis = new DataInputStream(socketCliente.getInputStream())) {
                	altaUserServer(dis);
                	
                	//   DEJAR ESTA FUNCION POR SI ACASO  AUNQUE HABRA ALGO PARECIDO EN FACHADA ////

//                try (Socket socketCliente = serverSocket.accept();
//                     DataInputStream dis = new DataInputStream(socketCliente.getInputStream())) {
//
//                    mensaje = dis.readUTF();
//                    String[] data = mensaje.split(",");
//
//                    Fachada f = new Fachada();
//                    if (f.comprobarUsuarioFachada(data)) {
//                        System.out.println("Registrado");
//                    } else {
//                        System.out.println("Usuario no registrado");
//                    }
//
//                    // Leemos el mensaje que recibimos
//                    System.out.println("Mensaje recibido de " + socketCliente.getInetAddress().getHostName() + ": " + mensaje + "\n");
//
//                    // Cerrar conexión solo cuando se recibe "exit"
//                    if (mensaje.equals("exit")) {
//                        System.out.println("Cerrando el servidor...");
//                        break;
//                    }
//
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } while (!mensaje.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void altaUserServer(DataInputStream dis) {
    	try {
			String mensaje = dis.readUTF();
			String[] data = mensaje.split(",");
            Fachada f = new Fachada();
            f.altaUsuarioFachada(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    }
}
