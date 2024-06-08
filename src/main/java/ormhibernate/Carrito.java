package ormhibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carrito {

	ArrayList<Videogames> listaVideojuegos = new ArrayList<Videogames>();
	Scanner lector = new Scanner(System.in);
	public Carrito() {
	}
	
	public void añadirVideojuego(Videogames videogame) {
		listaVideojuegos.add(videogame);
	}
	
	public void eliminarVideojuego(int id_videogame) {
		if (id_videogame >= 0 && id_videogame < listaVideojuegos.size()) {
			listaVideojuegos.remove(id_videogame);
		}
		
	}
	
	public void vaciarCarrito() {
		listaVideojuegos.clear();
	}
	
	/*public void verCarrito() {
		for (Videogames videogames : listaVideojuegos) {
			System.out.println("El carrito contiene: " + videogames.getNombre() + " " + videogames.getPrecio() + " " + videogames.getInformacion());
		}
	}*/
	 public void verCarrito() {
	        if (listaVideojuegos.isEmpty()) {
	            System.out.println("El carrito está vacío.");
	        } else {
	            System.out.println("Videojuegos en el carrito:");
	            for (Videogames videojuego : listaVideojuegos) {
	                System.out.println("- " + videojuego.getNombre());
	            }

				MAIN.seguirComprando();
	        }
	    }
	 
	 public List<Videogames> getCarrito() {
		 return listaVideojuegos;
	 }
}
