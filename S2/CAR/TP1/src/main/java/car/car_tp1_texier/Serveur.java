package car.car_tp1_texier;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import exceptions.ServeurExceptionArguments;
import exceptions.ServeurExceptionPortProbleme;

/**
 * Class to represent a server
 * 
 * @authors Marine, Celine et Leane
 *
 */
public class Serveur {

	private static int port;
	private static String path;

	/**
	 * Create a new connexion and run the thread for FTP requests
	 * 
	 * @param args the port and the path
	 * @throws ServeurExceptionArguments    if there isn't 2 arguments
	 * @throws ServeurExceptionPortProbleme if the port is less than 1024
	 */
	public static void main(String[] args) throws ServeurExceptionArguments, ServeurExceptionPortProbleme {
		// Ecouter les demandes de connexion sur un port TCP > 1023
		if (args.length != 2) {
			throw new ServeurExceptionArguments();
		} else {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex) {
				throw new ServeurExceptionPortProbleme();
			}
			if (port < 1023) {
				System.out.println("Wrong port! Default port : 1024");
				port = 1024;
			}
		}
		try {
			ServerSocket serveur = new ServerSocket(port);
			path = args[1];
			System.out.println("Server started on port : " + port + "!");
			while (true) {
				try {
					Socket connection = serveur.accept();
					System.out.println("A client is connected!");
					Thread ftp = new FtpRequest(connection, path);
					ftp.start();
				} catch (IOException ex) {
					System.err.println("Impossible to connect on the server!");
				}
			}
		} catch (IOException ex) {
			System.err.println("Impossible to start the server!");
		}
	}
}