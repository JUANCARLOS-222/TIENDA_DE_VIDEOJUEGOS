package ormhibernate;

import javax.persistence.*;

@Entity
@Table(name = "visa")

public class VISA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int numero_tarjeta;
	String titular_tarjeta;
	String fecha_caducidad;
	String numeroCVV;

	@OneToOne
	@JoinColumn(name="id_usuario")
	private User user;
	
	public VISA(int numero_tarjeta, String titular_tarjeta, String fecha_caducidad, String numeroCVV) {
		this.titular_tarjeta = titular_tarjeta;
		this.numero_tarjeta = numero_tarjeta;
		this.fecha_caducidad = fecha_caducidad;
		this.numeroCVV = numeroCVV;
	}

	public VISA() {

	}

	public int getNumeroTarjeta() {
		return numero_tarjeta;
	}

	public void setNumeroTarjeta(int numero_tarjeta) {
		this.numero_tarjeta = numero_tarjeta;
	}

	public String getTitularTarjeta() {
		return titular_tarjeta;
	}

	public void setTitularTarjeta(String titular_tarjeta) {
		this.titular_tarjeta = titular_tarjeta;
	}

	public String getFechaCaducidad() {
		return fecha_caducidad;
	}

	public void setFechaCaducidad(String fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}

	public String getNumeroCVV() {
		return numeroCVV;
	}

	public void setNumeroCVV(String numeroCVV) {
		this.numeroCVV = numeroCVV;
	}
		
	/*public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}*/

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
