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
		

	public ArrayList<Usuarios> leerUsuarioFachada() {		
		usersList = gestorUsuarios.leerBBDDUsuario();
		return usersList;
		//tratamos los datos, el id se generará en el gestor, despues de comprobar si ya existe este usuario y antes de añadirlo a la bbdd o crear la instancia de objeto
	}
	
	public void borrarUsuarioFachada(int idUsuario){
		System.err.println("SE SUPONE QUE ESTOY EN FACHADA");
		for (Usuarios usuarios : usersList) {
			if(usuarios.getId()==idUsuario) {
	            usersList.remove(idUsuario);
			}
		}
		gestorUsuarios.borrarUsuarioGestorUsuarios(idUsuario);
	}


	public ArrayList<Usuarios> recuperarDatosFachada() {
		usersList = gestorUsuarios.leerBBDDUsuario();
		return usersList;
	}
	
	public Usuarios obtenerUsuarioPorId(int idUSuarioAModificar) {
		Usuarios u = gestorUsuarios.buscaUsuarioPorId(idUSuarioAModificar);
		return u;
	}


	public void modificarUsuarioFachada(Usuarios usuarioModificado) {
		gestorUsuarios.modificarUsuario(usuarioModificado);
		
	}

	

}
