package ormhibernate;


import javax.persistence.*;
@Entity
@Table(name="user")
public class User {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id_usuario;
	String nombre;
	String apellido;
	int numero_telefono;
	String email;
	String contrasenya;
	int id_perfiles;
	
	public User(String nombre, String apellido, int numeroTelefono, String email, String contrasenya, int id_perfiles ) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.numero_telefono = numeroTelefono;
		this.email = email;
		this.contrasenya = contrasenya;
		this.id_perfiles = id_perfiles;
			
	}
	
	public User() {
		this.id_perfiles = 2;
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public int getNumeroTelefono() {
		return numero_telefono;
	}
	
	public void setNumeroTelefono(int numeroTelefono) {
		this.numero_telefono = numeroTelefono;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public int getId_perfiles() {
		return id_perfiles;
	}
	
	public void setId_perfiles(int id_perfiles) {
		this.id_perfiles = id_perfiles;
	}
	
	
	
}
