package com.lightbend.akka.tp3.exceptions;

/**
 * @author Celine, Leane et Marine
 */
public class ListNodesMissingException extends Exception {
	private static final long serialVersionUID = 1L;

	/** Create the exception called if the list of nodes is missing */
	public ListNodesMissingException() {
		super("The file is incorrect. The list of nodes must be specified.");
	}

}
