package ormhibernate;

import javax.persistence.*;

@Entity
@Table(name = "visa")

public class VISA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int numeroTarjeta;
	String titularTarjeta;
	String fechaCaducidad;
	String numeroCVV;

	@OneToOne(mappedBy="visa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;
	
	public VISA(int numeroTarjeta, String titularTarjeta, String fechaCaducidad, String numeroCVV) {
		this.titularTarjeta = titularTarjeta;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaCaducidad = fechaCaducidad;
		this.numeroCVV = numeroCVV;
	}

	public VISA() {

	}

	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTitularTarjeta() {
		return titularTarjeta;
	}

	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getNumeroCVV() {
		return numeroCVV;
	}

	public void setNumeroCVV(String numeroCVV) {
		this.numeroCVV = numeroCVV;
	}
	
	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
