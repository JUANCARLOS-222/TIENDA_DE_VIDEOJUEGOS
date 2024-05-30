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
	
	public void deleteById(int id_usuario) {
        // TODO Auto-generated method stub
        session.beginTransaction();
        session.clear();
        User usuario = new User();
        usuario.setId_usuario(id_usuario);
        session.delete(usuario);
        session.getTransaction().commit();

    }
	public User findOneById(int id) {
		// TODO Auto-generated method stub
		
		String hql = "FROM user where id_usuario=: id";

		Query<User> query = session.createQuery(hql, User.class);
		query.setParameter("id", id);
		session.getTransaction().commit();
		return new User();
		
	}
	
	public void updateById(int id, User usuario) {//actualizar por ID
		// TODO Auto-generated method stub
		session.beginTransaction();
		usuario.setId_usuario(id);
		session.update(usuario);
		session.getTransaction().commit();
	}
	
	
	
}
