package ormhibernate;

import java.util.InputMismatchException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MAIN {
	static Scanner lector = new Scanner(System.in);

	public static void main(String[] args) {

		menú();

	}

	public static List<User> findAll() {

		Session session = crearSesion();
		UserRepositorio user = new UserRepositorio(session);
		return user.findAll();

	}

	public static Session crearSesion() {
		System.out.println("Iniciando configuración hibernate...");
		final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
		final SessionFactory factory = new MetadataSources(registro).buildMetadata().buildSessionFactory();
		System.out.println("Abriendo conexión a BD...");
		final Session session = factory.openSession();
		System.out.println("Conexión abierta a BD...");
		return session;
	}

	public static void crearUsuario() {
		Scanner scanner = new Scanner(System.in);
		User user = new User();
		try {
			boolean usuarioCorrecto = false;
			do {
				System.out.println("Introduce tu nombre");
				user.setNombre(scanner.nextLine());
				System.out.println("Introduce tu apellido");
				user.setApellido(scanner.nextLine());
				System.out.println("Introduce tu numero de telefono");
				user.setNumeroTelefono(scanner.nextInt());
				scanner.nextLine();
				System.out.println("Introduce tu gmail");
				user.setEmail(scanner.nextLine());
				System.out.println("Introduce tu contraseña");
				user.setContrasenya(scanner.nextLine());
				usuarioCorrecto=datosCorrectosUsuario(user);
				System.out.println(usuarioCorrecto);
				if(usuarioCorrecto) {
					System.out.println(usuarioCorrecto);
					Session session = crearSesion();
					UserRepositorio userrep = new UserRepositorio(session);
					userrep.save(user);
					
					/* Session session = crearSesion(); */
					panelUsuario();
				}
				
			}while(!usuarioCorrecto);
			
		} catch (InputMismatchException e) {
			System.err.println("Introduce el número de teléfono correctamente");
		}
	}
	public static boolean comprobarEmail(String email) {
		Pattern p = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(email);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean datosCorrectosUsuario(User user) {
		
		if ((user.nombre == null || user.nombre.isEmpty()) ||
				(user.apellido == null || user.apellido.isEmpty()) ||
				(user.contrasenya == null || user.contrasenya.isEmpty()) ||
				(user.email == null || user.email.isEmpty())) {
			System.out.println("Propiedades vacias");
			return false;
		}else {
			if(comprobarEmail(user.email)) {
				System.out.println("El email es correcto");
				return true;
			}else {
				System.out.println("El correo es incorrecto");
				return false;
			}
			
		}
	}

	public static configuracionUser loginUsuario(String email, String contrasenya) {
		User login = new User();
		/* Session session = crearSesion(); */
		Session session = crearSesion();
		UserRepositorio userrep = new UserRepositorio(session);
		List<User> listaUser = userrep.findAll();
		boolean contrasenyaCorrecta=false;
		int perfil=0;
		for (User user : listaUser) {
			
			if (user.email.equals(email)) {
				if (user.contrasenya.equals(contrasenya)) {
				contrasenyaCorrecta=true;
				perfil=user.id_perfiles;
				}
			}
		}
		configuracionUser config = new configuracionUser(perfil,contrasenyaCorrecta);
		return config;

	}

	public static void menú() {

		String mensaje = "BIENVENIDO A JAC GAMES";
		int longitud = mensaje.length();

		for (int i = 0; i < longitud + 4; i++) {
			System.out.print("*");
		}
		System.out.println();

		System.out.println("* " + mensaje + " *");

		for (int i = 0; i < longitud + 4; i++) {
			System.out.print("*");
		}
		try {

			System.out.println("");
			System.out.println("¿Que desea hacer?");
			System.out.println("1 Iniciar Sesión");
			System.out.println("2 Registrarse");
			System.out.println("3 Salir");

			int resp = lector.nextInt();
			lector.nextLine();

			switch (resp) {
			case 1:
				System.out.println("Introduce su email");
				String email = lector.nextLine();
				System.out.println("Introduce la contraseña");
				String contrasenya = lector.nextLine();
				configuracionUser config = loginUsuario(email, contrasenya);
				if (config.usuarioCorrecto && config.perfil==1) {
				panelAdministrador();
				} else if (config.usuarioCorrecto && config.perfil==2) {
					System.out.println("Puede acceder a la aplicación");
					// catalogo(session);
					panelUsuario();
				} else {
					System.err.println("Ese usuario no existe");
					menú();
				}
				break;
			case 2:
				crearUsuario();
				break;
			case 3:
				System.out.println("Has salido");
				break;
			default:
				System.err.println("No es una opción válida");
				menú();
				break;
			}
		} catch (InputMismatchException e) {
			System.err.println("Elige una de las opciones");
			lector.nextLine();
			menú();
		}

		/*
		 * if (resp == 2) { crearUsuario(session); } else if (resp == 3) {
		 * System.out.println("Has salido"); } else if (resp == 1) {
		 * System.out.println("Introduce su email"); String email = lector.nextLine();
		 * System.out.println("Introduce la contraseña"); String contrasenya =
		 * lector.nextLine(); boolean existUsuario = loginUsuario(session, email,
		 * contrasenya); if (existUsuario) {
		 * System.out.println("Ya puede acceder a la aplicación"); catalogo(session); }
		 * else { System.out.println("El usuario esta mal escrito"); menú(session); }
		 */

		/*
		 * llamar a FindONeById Verificando email y contrasenya
		 */
	}

	public static void verCatalogo() {

		String mensaje = "Este es nuestro catalogo";
		int longitud = mensaje.length();

		for (int i = 0; i < longitud + 4; i++) {
			System.out.print("*");
		}
		System.out.println();

		System.out.println("* " + mensaje + " *");

		for (int i = 0; i < longitud + 4; i++) {
			System.out.print("*");
		}

		System.out.println("");
		Session session = crearSesion();
		ConsoleRepositorio console = new ConsoleRepositorio(session);
		List<Console> con = console.findAll();

		for (Console console2 : con) {
			System.out.println(console2.id_consola + " " + console2.nombre + " ");
		}
		System.out.println("6 Salir");
	}

	public static void catalogo() {
		try {
			int opcion = 0;
			while (opcion != 6) {

				verCatalogo();
				opcion = lector.nextInt();

				if (opcion == 6) {
					panelUsuario();
				} else {
					Session session = crearSesion();
					VideogameRepositorio videogame = new VideogameRepositorio(session);

					List<Videogames> Allvideojuegos = videogame.findVideogamesByConsoleId(opcion);
					// List<Videogames> Allvideojuegos = videogame.findAll();
					for (Videogames videogames : Allvideojuegos) {
						System.out.println(videogames.nombre);
					}
				}
			}
		} catch (InputMismatchException e) {
			System.err.println("Elige una de las opciones");
			lector.nextLine();
			catalogo();
		}
	}

	public static void panelUsuario() {
		int opcion = 0;
		System.out.println("1 - Ver catálogo");
		System.out.println("2 - Comprar videojuego");
		System.out.println("3 - Vender videojuego");
		System.out.println("4 - Ver carrito");
		System.out.println("5 - Salir");
		opcion = lector.nextInt();
		switch (opcion) {
		case 1:
			catalogo();
			break;
		case 2:
			System.out.println("Comprar videojuego");
			break;
		case 3:
			System.out.println("Vender videojuego");
			break;
		case 4:
			System.out.println("Ver carrito");
			break;
		case 5:
			System.out.println("Que la fuerza te acompañe");
			lector.nextLine();
			menú();
			break;
		default:
			System.out.println("Opción no válida");
			lector.nextLine();
			panelUsuario();
			break;
		}
	}

	public static void panelAdministrador() {
		int opcion = 0;
		System.out.println("1 - Eliminar videojuego");
		System.out.println("2 - Añadir videojuego");
		System.out.println("3 - Modificar catálogo");
		System.out.println("4 - Modificar videojuego");
		System.out.println("5 - Salir");
		opcion = lector.nextInt();
		switch (opcion) {
		case 1:
			System.out.println("Eliminar videojuego");
			break;
		case 2:
			System.out.println("Añadir videojuego");
			break;
		case 3:
			System.out.println("Modificar catálogo");
			break;
		case 4:
			System.out.println("Modificar videojuego");
			break;
		case 5:
			System.out.println("Has salido");
			lector.nextLine();
			menú();
			break;
		default:
			System.out.println("Opción no válida");
			break;
		}

	}

}
