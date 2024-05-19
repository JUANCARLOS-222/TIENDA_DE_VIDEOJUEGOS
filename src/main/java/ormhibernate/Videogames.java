package ormhibernate;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="videogames")
public class Videogames {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id_videojuego;
	String nombre;
	String informacion;
	@ManyToMany
	@JoinTable(
	name = "console_videogames", // Nombre de la tabla de unión
	joinColumns = @JoinColumn(name = "videogame_id"), // Columna en la tabla de unión que apunta a esta entidad (Videogame)
	inverseJoinColumns = @JoinColumn(name = "console_id") // Columna en la tabla de unión que apunta a la entidad asociada (Console)
	)
	private Set<Console> consoles = new HashSet<Console>();
	
	
	public Videogames(int id_videojuego, String nombre, String informacion) {
		this.nombre = nombre;
		this.id_videojuego = id_videojuego;
		this.informacion = informacion;	
	}
	public Videogames() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getIdVideogame() {
		return id_videojuego;
	}
	
	public void setIdVideogame(int id_videojuego) {
		this.id_videojuego = id_videojuego;
	}
	
	public String getInformacion() {
		return informacion;
	}
	
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
}
