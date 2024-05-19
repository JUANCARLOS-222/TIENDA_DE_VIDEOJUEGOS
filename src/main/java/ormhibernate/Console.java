package ormhibernate;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="console")
public class Console {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id_consola;
    public String nombre;

    @ManyToMany(mappedBy = "consoles")
    private Set<Videogames> videogames = new HashSet<Videogames>();
    
	public Console(int id_consola, String nombre) {
		this.nombre = nombre;
		this.id_consola = id_consola;
			
	}
	public Console() {
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getIdConsola() {
		return id_consola;
	}
	
	public void setIdConsola(int id_consola) {
		this.id_consola = id_consola;
	}
	
    public void setVideogames(Set<Videogames> videogames) {
        this.videogames = videogames;
    }
	
	
	
	
}
