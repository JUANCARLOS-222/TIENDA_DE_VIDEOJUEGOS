package ormhibernate;

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
	static Session session;
	public static void main(String[] args) {

		session = crearSesion();
		menú(session);
		
		
		
		
		
		
		
		
		
	}
	public static List<User> findAll() {
		
		Session session = crearSesion();
		  UserRepositorio user = new UserRepositorio(session);
		  return user.findAll();
		

		
		  
	}
	public static Session crearSesion() {
		System.out.println("Iniciando configuración hibernate...");
		  final StandardServiceRegistry registro =new
		  StandardServiceRegistryBuilder().configure().build(); 
		  final SessionFactory factory =new MetadataSources(registro).buildMetadata().buildSessionFactory();
		  System.out.println("Abriendo conexión a BD..."); 
		  final Session session=factory.openSession();
		  System.out.println("Conexión abierta a BD..."); 
		  return session;
	}
	
	
	public static void crearUsuario(Session session) {
		Scanner scanner = new Scanner(System.in);
		User user = new User();
		System.out.println("Introduce tu nombre");
		user.setNombre (scanner.nextLine());
		System.out.println("Introduce tu apellido");
		user.setApellido(scanner.nextLine());
		System.out.println("Introduce tu numero de telefono");
		user.setNumeroTelefono(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Introduce tu gmail");
		user.setEmail(scanner.nextLine());
		
		
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(user.email);
		if(m.matches()) {
			System.out.println("Introduce tu contraseña");
			user.setContrasenya(scanner.nextLine());
			
			System.out.println("EXCELSIOR!");
			
			/*Session session = crearSesion();*/
			  UserRepositorio userrep = new UserRepositorio(session);
			  	userrep.save(user);
			  	
		} else {
			System.err.println("Ese usuario no existe");
		}
	}
	
	public static boolean loginUsuario(Session session, String email, String contrasenya) {
		User login = new User();
		/*Session session = crearSesion();*/
		  UserRepositorio userrep = new UserRepositorio(session);
		  List<User> listaUser = userrep.findAll(); 
		  
		  for (User user : listaUser) {
			if(user.email.equals(email)) {
				if(user.contrasenya.equals(contrasenya)) {
					return true;
				}else {
					return false;
				}
			}
		}return false;
		
	}
	
	public static void menú(Session session) {
	
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
	        lector.nextLine();
	        System.out.println("¿Que desea hacer?");
	        lector.nextLine();
	        System.out.println("1.Iniciar Sesión");
			System.out.println("2.Registrarse");
			int resp = lector.nextInt();
			lector.nextLine();
			if(resp==2) {
				crearUsuario(session);
			}else if(resp==1) {
				System.out.println("Introduce su email");
				String email = lector.nextLine();
				System.out.println("Introduce la contraseña");
				String contrasenya= lector.nextLine();
				boolean existUsuario = loginUsuario(session, email, contrasenya);
				if(existUsuario) {
					System.out.println("Ya puede acceder a la aplicación");
					catalogo(session);
				}else {
					System.out.println("El usuario esta mal escrito");
				}
				/*llamar a FindONeById
				 * Verificando email y contrasenya*/
			}
	        
	}
	
	public static void verCatalogo(Session session) {
		
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
        lector.nextLine();
        
        /*Session session = crearSesion();*/
		  ConsoleRepositorio console = new ConsoleRepositorio(session);
		  List<Console> con = console.findAll();
		  
		  for (Console console2 : con) {
			System.out.println(console2.id_consola + " " + console2.nombre + " ");
		}
        
		
	}
	public static void catalogo(Session session) {
		int opcion = 0;
		verCatalogo(session);
		opcion = lector.nextInt();
		VideogameRepositorio videogame = new VideogameRepositorio(session);
		/*videogame.findVideogamesByConsoleId(opcion);*/
		List<Videogames> Allvideojuegos = videogame.findAll();
		for (Videogames videogames : Allvideojuegos) {
			System.out.println(videogames.nombre);
		}
		
		switch (opcion) {
		case 1:
			
		System.out.println("PlayStation 5");
		break;
		case 2:
		System.out.println("PlayStation 4");
		break;
		case 3:
		System.out.println("Xbox Series");
		break;
		case 4:
		System.out.println("Xbox One");
		break;
		case 5:
		System.out.println("PC");
		break;
		case 6:
		System.out.println("Nintendo Switch");
		default:
		System.out.println("Opción no válida");
		break;
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
		System.out.println("Ver catálogo");
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
		default:
		System.out.println("Opción no válida");
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
		System.out.println("Yeeee");
		default:
		System.out.println("Opción no válida");
		break;
		}

		}
	
}

