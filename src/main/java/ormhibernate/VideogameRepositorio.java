package ormhibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class VideogameRepositorio implements repositorio<Videogames>{
	final Session session;
	public VideogameRepositorio(Session session) {
		this.session=session;
	}
	
	public List<Videogames> findAll() {
		// TODO Auto-generated method stub
		session.beginTransaction();
		Query<Videogames> query=session.createQuery("FROM Videogames", Videogames.class);
		List<Videogames> videojuegos =query.getResultList();
		session.getTransaction().commit();
		  return videojuegos;
		

		
		  
	}
	public void save(Videogames videogame) {
		/*session.beginTransaction();
		System.out.println("hola" + user.nombre);
		session.save(user);
		session.getTransaction().commit();*/
		/*try {
		    session.beginTransaction();
		    session.save(videogame);
		    session.getTransaction().commit();
		} catch (Exception e) {
		    if (session.getTransaction() != null) {
		        session.getTransaction().rollback();
		    }
		    e.printStackTrace(); // Esto imprimirá la pila de errores si hay uno.
		} finally {
		    session.close(); // Asegúrate de cerrar la sesión.
		}*/
	}
	
	public List<Videogames> findVideogamesByConsoleId(int consoleId) {
	    try {
	        session.beginTransaction();
	        Query<Videogames> query = session.createQuery(
	            "SELECT v.nombre, v.informacion, id_videojuego FROM gameshopdb.videogames v  LEFT JOIN gameshopdb.console_videogames cv on id_videojuego = cv.videojuego_id left join gameshopdb.console c on c.id_consola = cv.console_id  WHERE cv.console_id =  :consoleId",
	            Videogames.class
	        );
	        query.setParameter("consoleId", consoleId);
	        List<Videogames> videogames = query.getResultList();
	        session.getTransaction().commit();
	        return videogames;
	    } catch (Exception e) {
	        if (session.getTransaction() != null) session.getTransaction().rollback();

	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return null;
	}
	
	
	
	
}
