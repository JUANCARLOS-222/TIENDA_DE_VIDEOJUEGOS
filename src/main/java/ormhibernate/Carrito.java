package ormhibernate;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

	ArrayList<Videogames> listaVideojuegos = new ArrayList<Videogames>();
	
	public Carrito() {
	}
	
	public void añadirVideojuego(Videogames videogame) {
		listaVideojuegos.add(videogame);
	}
	
	public void eliminarVideojuego(int id_videogame) {
		//for (Videogames videogames : listaVideojuegos) {
			//if (videogames.id_videogame == id_videogame) {
				listaVideojuegos.remove(id_videogame);
			//}
		//}
		
	}
	
	public void verCarrito() {
		for (Videogames videogames : listaVideojuegos) {
			System.out.println("El carrito contiene: " + videogames.getNombre() + " " + videogames.getPrecio() + " " + videogames.getInformacion());
		}
	}
	
	
	
	
}
