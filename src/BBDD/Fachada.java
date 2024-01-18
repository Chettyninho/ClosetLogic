package BBDD;

public class Fachada {
	GestorUsuarios gestorUsuarios = new GestorUsuarios();
	public boolean comprobarUsuarioFachada(String[] data) {
		boolean coincide = false;
		String usuario= data[0];
		String contrasena = data[1];
		
		if(gestorUsuarios.comprobarUsuario(usuario, contrasena)) {
			coincide = true;
		}
		
		return coincide;
	}
	
	

}
