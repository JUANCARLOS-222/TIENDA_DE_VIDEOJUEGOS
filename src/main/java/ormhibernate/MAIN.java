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
		  final StandardServiceRegistry registro =new
		  StandardServiceRegistryBuilder().configure().build(); 
		  final SessionFactory factory =new MetadataSources(registro).buildMetadata().buildSessionFactory();
		  System.out.println("Abriendo conexión a BD..."); 
		  final Session session=factory.openSession();
		  System.out.println("Conexión abierta a BD..."); 
		  return session;
	}
	
	
	public static void crearUsuario() {
		Scanner scanner = new Scanner(System.in);
		User user = new User();
		System.out.println("Introduce tu nombre");
		user.setNombre (scanner.nextLine());
		System.out.println("Introduce tu apellido");
		user.setApellido(scanner.nextLine());
		System.out.println("Introduce tu numero de telefono");
		user.setNumeroTelefono(scanner.nextInt());
		System.out.println("Regístrate para entrar en nuestra web");
		scanner.nextLine();
		System.out.println("Introduce tu gmail");
		user.setEmail(scanner.nextLine());
		
		
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(user.email);
		if(m.matches()) {
			System.out.println("Introduce tu contraseña");
			user.setContrasenya(scanner.nextLine());
			
			System.out.println("EXCELSIOR!");
			
			Session session = crearSesion();
			  UserRepositorio userrep = new UserRepositorio(session);
			  System.out.println("El nombre es" + user.nombre);
			  	userrep.save(user);
			System.out.println("¿Qué desea hacer?");
		} else {
			System.err.println("Ese usuario no existe");
		}
	}
	
	public static boolean loginUsuario(String email, String contrasenya) {
		User login = new User();
		Session session = crearSesion();
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
	
	public static void panelUsuario(){
		
	}
	
	public static void menu1() {
			
		User user = new User();
		
		System.out.println("Bienvenido a JAC'Games'");
		lector.nextLine();
		System.out.println("1.Iniciar Sesión");
		System.out.println("2.Registrarse");
		int resp = lector.nextInt();
		lector.nextLine();
		/*Mejorar el menú*/
		if(resp==2) {
			crearUsuario();
		}else if(resp==1) {
			System.out.println("Introduce su email");
			String email = lector.nextLine();
			System.out.println("Introduce la contraseña");
			String contrasenya= lector.nextLine();
			boolean existUsuario = loginUsuario(email, contrasenya);
			if(existUsuario) {
				System.out.println("Ya puede acceder a la aplicación");
			}else {
				System.out.println("El usuario esta mal escrito");
			}
			/*llamar a FindONeById
			 * Verificando email y contrasenya*/
		}
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
	        lector.nextLine();
	        System.out.println("¿Que desea hacer?");
	        lector.nextLine();
	        System.out.println("1.Iniciar Sesión");
			System.out.println("2.Registrarse");
			int resp = lector.nextInt();
			lector.nextLine();
			if(resp==2) {
				crearUsuario();
			}else if(resp==1) {
				System.out.println("Introduce su email");
				String email = lector.nextLine();
				System.out.println("Introduce la contraseña");
				String contrasenya= lector.nextLine();
				boolean existUsuario = loginUsuario(email, contrasenya);
				if(existUsuario) {
					System.out.println("Ya puede acceder a la aplicación");
				}else {
					System.out.println("El usuario esta mal escrito");
				}
				/*llamar a FindONeById
				 * Verificando email y contrasenya*/
			}
	        
	}
	
	public static void mario () {
		
		
		
	}
}

