package Tablas;

public class Usuarios {
	private int id;
	private String nombre;
	private String surname;
	private String email;
	private String userName;
	private String fechaNacimiento;//no incluida en la bbdd del anterior commit, al igual que contraseñaHasseada y salt
	
	private String hashContraseña;//asegurarse de que en la bbdd existan estos dos  camposs
    private String salt;//numero que añade seguridad, se aplicará en el hasseo.
    
    
	public Usuarios(int id, String nombre, String surname, String email, String userName, String hashContraseña,
			String salt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.surname = surname;
		this.email = email;
		this.userName = userName;
		this.hashContraseña = hashContraseña;
		this.salt = salt;
	}
	
	
    
    

}
