package ormhibernate;

import java.util.Date;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Factura {
	private List<Videogames> productos;
	private LocalDate fechaCompra;

	public Factura(List<Videogames> productos, LocalDate fechaCompra) {
		this.productos = productos;
		this.fechaCompra = fechaCompra;
	}

	public String generarFactura() {
		StringBuilder sb = new StringBuilder();
		sb.append("Fecha: ").append(fechaCompra).append("\n");
		sb.append("Productos:\n");
		double totalV = 0;
		for (Videogames producto : productos) {
			totalV += producto.precio;
			sb.append("- ").append(producto.nombre).append("\n");
		}
		sb.append("Total: ").append(totalV).append("\n");
		return sb.toString();
	}

	public void escribirEnArchivo(String nombreArchivo) throws IOException {
		FileWriter writer = new FileWriter(nombreArchivo);
		writer.write(generarFactura());
		writer.close();
	}

}
