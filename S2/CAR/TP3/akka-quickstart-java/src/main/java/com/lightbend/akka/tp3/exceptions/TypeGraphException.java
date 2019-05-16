package com.lightbend.akka.tp3.exceptions;

/**
 * @author Celine, Leane et Marine
 */
public class TypeGraphException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Create the exception called if there is a problem with the type of the graph
	 */
	public TypeGraphException() {
		super("The file is incorrect. The type must specified and be tree or graph");
	}
}
