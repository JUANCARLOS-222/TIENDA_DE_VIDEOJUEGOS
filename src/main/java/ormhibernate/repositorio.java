package ormhibernate;

import java.util.List;

public interface repositorio<T> {
	    //Repositorio está parametrizada con un tipo genérico T. Esto significa que puedes usar la interfaz
	    // con diferentes tipos de datos.
	List<T> findAll();
	T findOneById(int id);
	void save(T t);
	/*void updateById(int id, T t);*/
	void deleteById(int id);
	}

