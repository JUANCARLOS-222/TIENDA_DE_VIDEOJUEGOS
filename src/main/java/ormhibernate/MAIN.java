package ormhibernate;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Scanner;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.logging.Level;

public class MAIN {
	static Scanner lector = new Scanner(System.in);

	public static void main(String[] args) {

		Logger.getLogger("org.hibernate").setLevel(Level.OFF);

		menú();

	}

	public static List<User> findAll() {

		Session session = crearSesion();
		UserRepositorio user = new UserRepositorio(session);
		return user.findAll();

	}

	public static Session crearSesion() {
		final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
		final SessionFactory factory = new MetadataSources(registro).buildMetadata().buildSessionFactory();
		LogManager.getLogManager().reset();
		final Session session = factory.openSession();
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
				usuarioCorrecto = datosCorrectosUsuario(user);
				System.out.println(usuarioCorrecto);
				if (usuarioCorrecto) {
					System.out.println(usuarioCorrecto);
					Session session = crearSesion();
					UserRepositorio userrep = new UserRepositorio(session);
					userrep.save(user);

					/* Session session = crearSesion(); */
					panelUsuario();
				}

			} while (!usuarioCorrecto);

		} catch (InputMismatchException e) {
			System.err.println("Introduce el número de teléfono correctamente");
		}
	}

	public static boolean comprobarEmail(String email) {
		Pattern p = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(email);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean datosCorrectosUsuario(User user) {

		if ((user.nombre == null || user.nombre.isEmpty()) || (user.apellido == null || user.apellido.isEmpty())
				|| (user.contrasenya == null || user.contrasenya.isEmpty())
				|| (user.email == null || user.email.isEmpty())) {
			System.out.println("Propiedades vacias");
			return false;
		} else {
			if (comprobarEmail(user.email)) {
				System.out.println("El email es correcto");
				return true;
			} else {
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
		boolean contrasenyaCorrecta = false;
		int perfil = 0;
		for (User user : listaUser) {

			if (user.email.equals(email)) {
				if (user.contrasenya.equals(contrasenya)) {
					contrasenyaCorrecta = true;
					perfil = user.id_perfiles;
				}
			}
		}
		configuracionUser config = new configuracionUser(perfil, contrasenyaCorrecta);
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
				if (config.usuarioCorrecto && config.perfil == 1) {
					panelAdministrador();
				} else if (config.usuarioCorrecto && config.perfil == 2) {
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
		lector.nextLine();
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
		/*
		 * Session session = crearSesion(); ConsoleRepositorio console = new
		 * ConsoleRepositorio(session); List<Console> con = console.findAll();
		 * 
		 * for (Console console2 : con) { System.out.println(console2.id_consola + " " +
		 * console2.nombre + " "); }
		 */
		mostrarConsolas();
		

	}

	public static void mostrarConsolas() {
		Session session = crearSesion();
		ConsoleRepositorio console = new ConsoleRepositorio(session);
		List<Console> con = console.findAll();

		for (Console console2 : con) {
			System.out.println(console2.id_consola + " " + console2.nombre + " ");
		}
	}

	public static void catalogo() {
		try {
			int opcion = Integer.MAX_VALUE;
			while (opcion != 0) {
				verCatalogo();
				System.out.println("0 Salir");

				opcion = lector.nextInt();

				if (opcion == 0) {
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
			System.out.println("Que juego quieres comprar?");
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
		System.out.println("1 - Añadir Videojuego");
		System.out.println("2 - Eliminar Videojuego");
		System.out.println("3 - Modificar Videojuego");
		System.out.println("4 - Añadir Consolas");
		System.out.println("5 - Eliminar Consola");
		System.out.println("6 - Modificar Consola");
		System.out.println("7 - Salir");
		opcion = lector.nextInt();
		switch (opcion) {
		case 1:
			System.out.println("Introduce los datos del juego que deseas añadir:");
			guardarVideojuego();
			break;
		case 2:
			System.out.println("Selecciona el videojuego que deseas borrar:");
			mostrarVideojuegos();
			borrarVideojuegos();
			break;
		case 3:
			System.out.println("Indica el videojuego que deseas modificar:");
			break;
		case 4:
			System.out.println("Introduce los datos de la consola que deseas añadir:");
			guardarConsola();
			break;
		case 5:
			System.out.println("Selecciona la consola que deseas eliminar:");
			mostrarConsolas();
			borrarConsola();
			break;
		case 6:
			System.out.println("Indica la consola que deseas modificar:");
			break;
		case 7:
			System.out.println("Has salido");
			lector.nextLine();
			menú();
			break;
		default:
			System.out.println("Opción no válida");
			break;
		}

	}

	public static void mostrarVideojuegos() {
		Session session = crearSesion();
		VideogameRepositorio videogame = new VideogameRepositorio(session);

		List<Videogames> Allvideojuegos = videogame.findVideogamesByConsoleId(1);
		// List<Videogames> Allvideojuegos = videogame.findAll();
		for (Videogames videogames : Allvideojuegos) {
			System.out.println(videogames.id_videogame + " - " + videogames.nombre);
		}

	}

	public static void borrarVideojuegos() {
		Session session = crearSesion();
		VideogameRepositorio videogame = new VideogameRepositorio(session);

		System.out.println("Que videojuego quieres borrar");
		int opcion = lector.nextInt();

		videogame.deleteById(opcion);

	}

	public static void crearConsola(Console consola) {

		Session session = crearSesion();
		ConsoleRepositorio consolrep = new ConsoleRepositorio(session);
		consolrep.save(consola);
	}

	public static void guardarConsola() {
		Console consola = new Console();
		lector.nextLine();
		System.out.println("Inserte el nombre de la consola:");
		consola.setNombre(lector.nextLine());
		Session session = crearSesion();
		ConsoleRepositorio consolerep = new ConsoleRepositorio(session);
		consolerep.save(consola);
		System.out.println("Se añadido la consola correctamente :)");
		mostrarConsolas();

		System.out.println("");

		panelAdministrador();
	}
	
	public static void borrarConsola() {
		Session session = crearSesion();
		ConsoleRepositorio console = new ConsoleRepositorio(session);
		
		System.out.println("Que consola quieres borrar:");
		int opcion = lector.nextInt();
		
		console.deleteById(opcion);
		
		System.out.println("Se ha borrado correctamente su consola");
		
		mostrarConsolas();
		
		System.out.println("");
		
		panelAdministrador();
		
		}

	public static void crearVideogame(Videogames videogame) {
		Session session = crearSesion();
		VideogameRepositorio videorep = new VideogameRepositorio(session);
		videorep.save(videogame);
	}

	public static void guardarVideojuego() {
		Videogames videojuego = new Videogames();
		lector.nextLine();
		System.out.println("Inserte el nombre del videojuego:");
		videojuego.setNombre(lector.nextLine());
		System.out.println("Inserte la informacion sobre su videojuego:");
		videojuego.setInformacion(lector.nextLine());
		// lector.nextLine();
		System.out.println("Introduce el precio de su videojuego:");
		videojuego.setPrecio(lector.nextFloat());
		lector.nextLine();
		System.out.println("Inserte el precio minimo de su videojuego:");
		videojuego.setPrecio_min(lector.nextFloat());
		lector.nextLine();
		System.out.println("Inserte el precio maximo de su videojuego:");
		videojuego.setPrecio_max(lector.nextFloat());
		lector.nextLine();
		System.out.println("A cuantas consolas va a pertenecer su videojuego");
		int resp = lector.nextInt();
		lector.nextLine();
		mostrarConsolas();

		Session session = crearSesion();
		ConsoleRepositorio consolerep = new ConsoleRepositorio(session);
		List<Console> lista = new ArrayList<Console>();
		for (int i = 0; i < resp; i++) {
			System.out.println("Escoge la consola");
			int eleccion = lector.nextInt();
			// lector.nextInt();
			Console co = consolerep.findOneById(eleccion);
			if (co != null) {
				lista.add(co);
			}
		}
		Set<Console> consolas = new HashSet<Console>(lista);
		videojuego.setConsoles(consolas);
		VideogameRepositorio videorep = new VideogameRepositorio(session);
		videorep.save(videojuego);

		mostrarVideojuegos();

		System.out.println("");

		panelAdministrador();
	}

}
