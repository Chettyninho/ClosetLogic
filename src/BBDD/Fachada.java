package BBDD;

import java.util.ArrayList;

import Tablas.Usuarios;

public class Fachada {
	ArrayList<Usuarios> usersList = new ArrayList<Usuarios>();
	//tantos ArrayList como tablas haya creo que iria bien para tratar mas facil los datos(?), preguntar Alberto
	GestorUsuarios gestorUsuarios = new GestorUsuarios();
	
	public void altaUsuarioFachada(String [] data) {		
		usersList = gestorUsuarios.altaUsuario(usersList, data);
		//tratamos los datos, el id se generará en el gestor, despues de comprobar si ya existe este usuario y antes de añadirlo a la bbdd o crear la instancia de objeto
	}
		

}
