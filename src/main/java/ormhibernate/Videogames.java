package ormhibernate;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name="videogames")
public class Videogames {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id_videogame;
	String nombre;
	String informacion;
	float precio;
	float precio_min;
	float precio_max;
	
    @ManyToMany
    @JoinTable(
        name = "console_videogames",
        joinColumns = @JoinColumn(name = "id_videogame"),
        inverseJoinColumns = @JoinColumn(name = "id_consola")
    )
	private Set<Console> consoles = new HashSet<Console>();
	
	
	public Videogames(int id_videojuego, String nombre, String informacion) {
		this.nombre = nombre;
		this.id_videogame = id_videojuego;
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
		return id_videogame;
	}
	
	public  void setIdVideogame(int id_videojuego) {
		this.id_videogame = id_videojuego;
	}
	
	public String getInformacion() {
		return informacion;
	}
	
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	
    public void setConsoles(Set<Console> consoles) {
        this.consoles = consoles;
    }
    
    public void setPrecio(float precio) {
    	this.precio = precio;
    }
    
    public void setPrecio_min(float precio_min) {
    	this.precio_min = precio_min;
    }
    
    public void setPrecio_max(float precio_max) {
    	this.precio_max = precio_max;
    }
}
