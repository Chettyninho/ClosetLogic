package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion implements AutoCloseable {

    private static final String url = "jdbc:mysql://localhost:3306/prueba_cliente_servidor";
    private static final String usuario = "root";
    private static final String pswd = "********" //poner contraseña;

    private static Conexion instancia;
    private Connection connection;

    // Constructor privado para evitar la creación directa de instancias
    private Conexion() {
        try {
            Properties props = new Properties();
            props.setProperty("user", usuario);
            props.setProperty("password", pswd);
            props.setProperty("ssl", "true"); //esto es una metodo de seguridad (Secure Sockets Layer)
System.out.println("conexion control:");	//para la encriptación de los datos transmitidos entre el cliente y el servidor
            this.connection = DriverManager.getConnection(url, props);
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al abrir la conexión", e);
        }
    }

    // Método estático para obtener la única instancia (Singleton)
    public static synchronized Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        return connection;
    }

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void close() throws Exception {
//		// TODO Auto-generated method stub
//		
//	}

    // Implementación del método close() de la interfaz AutoCloseable
//    @Override
//    public void close() {
//        try {
//            if (connection != null && !connection.isClosed()) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
