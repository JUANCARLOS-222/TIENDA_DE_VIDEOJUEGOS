package ormhibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class ConsoleRepositorio implements repositorio<Console>{
	final Session session;
	public ConsoleRepositorio(Session session) {
		this.session=session;
	}
	
	public List<Console> findAll() {
		// TODO Auto-generated method stub
		session.beginTransaction();
		Query<Console> query=session.createQuery("FROM Console", Console.class);
		List<Console> consolas =query.getResultList();
		session.getTransaction().commit();
		  return consolas;
		

		
		  
	}
	public void save(Console console) {
		/*session.beginTransaction();
		System.out.println("hola" + user.nombre);
		session.save(user);
		session.getTransaction().commit();*/
		try {
		    session.beginTransaction();
		    session.save(console);
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
	
	public void deleteById(int id_consola) {
        // TODO Auto-generated method stub
        session.beginTransaction();
        session.clear();
        Console consola = new Console();
        consola.setIdConsola(id_consola);
        session.delete(consola);
        session.getTransaction().commit();

    }
	
	public Console findOneById(int id) {
		// TODO Auto-generated method stub
		session.beginTransaction();
		String hql = "FROM Console where id_consola=: id";

		Query<Console> query = session.createQuery(hql, Console.class);
		
		
		query.setParameter("id", id);
		Console co = query.uniqueResult();
		session.getTransaction().commit();
		return co;
		
	}
	
	
	
	
}
