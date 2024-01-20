package BBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Tablas.Usuarios;

public class GestorUsuarios {
	private ArrayList<Usuarios> usersList = new ArrayList<Usuarios>();

////////////////////////////      TAMLA DE USUARIOS          //////////////////////////////////////////////
	
	public void altaUsuario(String []data) {
		
		//aqui comprobamos si existe
		
		//despues si NO existe extraemos el ultmo id de la base de datos para generar el nuevo id,
		//creamos una funcion que nos devuelva el hass,
		//en otraa funcion se pasan el id, el hass, y la contraseña, alli se hasseara y devolverá la contraseña hasseada
		
		//con estos datos crearemos la instancia de java de la clase cliente y la inserccion en la bbdd.
	}
    public boolean comprobarUsuario(ArrayList<Usuarios> usersList, String user, String passw) {//esta funcion la haremos privada para comprobar si existe
        boolean coincide = false;															//o no existe en la bbdd y solo entonces se le dará de alta.

        try (Conexion con = Conexion.getInstance()) {										//si YA EXISTE se lanza excepcion(?)(Donde gestionamos las excepciones?))
           
            String query = "SELECT * FROM USUARIOS WHERE Name = ?";
            PreparedStatement ps = con.getConnection().prepareStatement(query);
            
            // Establece el valor del parámetro
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String psw = rs.getString("pswd");

                // Compara la contraseña proporcionada con la almacenada en la base de datos
                if (passw.equals(psw)) {
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
}
