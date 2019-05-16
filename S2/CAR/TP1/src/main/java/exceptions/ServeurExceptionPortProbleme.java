package exceptions;

/**
 * Class of the exception if there is a problem with the port
 * 
 * @authors Marine, Celine et Leane
 *
 */
public class ServeurExceptionPortProbleme extends Exception {
	private static final long serialVersionUID = 1L;

	/** Create the exception called if there is a problem with the port */
	public ServeurExceptionPortProbleme() {
		super("The first parameter must be an integer");
	}
}
