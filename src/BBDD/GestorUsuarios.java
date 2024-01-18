package BBDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorUsuarios {

    public boolean comprobarUsuario(String user, String passw) {
        boolean coincide = false;

        try (Conexion con = Conexion.getInstance()) {
           
            String query = "SELECT * FROM USUARIO WHERE Usuario = ?";
            PreparedStatement ps = con.getConnection().prepareStatement(query);
            
            // Establece el valor del parámetro
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String psw = rs.getString("Password");

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
