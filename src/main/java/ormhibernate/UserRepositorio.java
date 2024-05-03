package ormhibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserRepositorio implements repositorio<User>{
	final Session session;
	public UserRepositorio(Session session) {
		this.session=session;
	}
	
	public List<User> findAll() {
		// TODO Auto-generated method stub
		session.beginTransaction();
		Query<User> query=session.createQuery("FROM User", User.class);
		List<User> usuarios =query.getResultList();
		session.getTransaction().commit();
		  return usuarios;
		

		
		  
	}
	public void save(User user) {
		/*session.beginTransaction();
		System.out.println("hola" + user.nombre);
		session.save(user);
		session.getTransaction().commit();*/
		try {
		    session.beginTransaction();
		    session.save(user);
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
	
	
	
	
}
