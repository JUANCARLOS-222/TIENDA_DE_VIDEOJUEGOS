package ormhibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class VideogameRepositorio implements repositorio<Videogames> {
	final Session session;

	public VideogameRepositorio(Session session) {
		this.session = session;
	}

	public List<Videogames> findAll() {
		// TODO Auto-generated method stub
		session.beginTransaction();
		Query<Videogames> query = session.createQuery("FROM Videogames", Videogames.class);
		List<Videogames> videojuegos = query.getResultList();
		session.getTransaction().commit();
		return videojuegos;
	}

	public List<Videogames> findVideogamesByConsoleId(int consoleId) {

		session.beginTransaction();
		// String hql = "SELECT v.nombre, v.informacion, v.id_videogame FROM
		// gameshopdb.videogames v LEFT JOIN gameshopdb.console_videogames cv on
		// v.id_videogame = cv.id_videogame left join gameshopdb.console c on
		// c.id_consola = cv.id_consola WHERE cv.id_consola = :consoleId";
		String hql = "SELECT v FROM Videogames v " + "LEFT JOIN v.consoles c " + "WHERE c.id_consola = :consoleId";

		Query<Videogames> query = session.createQuery(hql, Videogames.class);
		query.setParameter("consoleId", consoleId);
		session.getTransaction().commit();
		return query.list();

	}

	public void deleteById(int id_videogame) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		session.clear();
		Videogames videojuego = new Videogames();
		videojuego.setIdVideogame(id_videogame);
		System.out.println(id_videogame);
		session.delete(videojuego);
		session.getTransaction().commit();

	}

	public void save(Videogames videogame) {
		try {
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
		}
	}

	public Videogames findOneById(int id) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		String hql = "FROM Videogames where id_videogame=: id";

		Query<Videogames> query = session.createQuery(hql, Videogames.class);
		query.setParameter("id", id);
		session.getTransaction().commit();
		return new Videogames();
		
	}
	
	public void updateById(int id, Videogames videojuego) {//actualizar por ID
		// TODO Auto-generated method stub
		session.beginTransaction();
		videojuego.setIdVideogame(id);
		session.update(videojuego);
		session.getTransaction().commit();
	}

	
	/*
	 * public List<Videogames> findVideogamesByConsoleId(int consoleId) { try {
	 * session.beginTransaction(); Query<Videogames> query = session.createQuery(
	 * "SELECT v.nombre, v.informacion, v.id_videogame FROM gameshopdb.videogames v  LEFT JOIN gameshopdb.console_videogames cv on v.id_videogame = cv.id_videogame left join gameshopdb.console c on c.id_consola = cv.id_consola  WHERE cv.id_consola =  :consoleId"
	 * , Videogames.class ); query.setParameter("consoleId", consoleId);
	 * List<Videogames> videogames = query.getResultList();
	 * session.getTransaction().commit(); return videogames; } catch (Exception e) {
	 * if (session.getTransaction() != null) session.getTransaction().rollback();
	 * 
	 * e.printStackTrace(); } finally { session.close(); } return null; }
	 */

}