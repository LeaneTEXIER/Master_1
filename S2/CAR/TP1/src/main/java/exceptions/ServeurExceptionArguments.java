package exceptions;

/**
 * Class of the exception if there is a problem with the parameters
 * 
 * @authors Marine, Celine et Leane
 *
 */
public class ServeurExceptionArguments extends Exception {
	private static final long serialVersionUID = 1L;

	/** Create the exception called if there is a problem with the parameters */
	public ServeurExceptionArguments() {
		super("usage : [port] [path]");
	}
}
