package ormhibernate;

import java.util.List;

import org.hibernate.Session;

public class VisaRepositorio implements repositorio<VISA>{
	final Session session;

	public VisaRepositorio(Session session) {
		this.session = session;
	}

	public List<VISA> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public VISA findOneById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(VISA t) {
		// TODO Auto-generated method stub
		
	}

	public void updateById(int id, VISA t) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
