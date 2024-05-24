package ormhibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="perfiles")
public class Perfiles {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id_usuario;
}
