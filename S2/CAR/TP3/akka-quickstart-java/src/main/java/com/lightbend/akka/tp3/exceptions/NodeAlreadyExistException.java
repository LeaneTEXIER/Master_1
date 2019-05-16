package com.lightbend.akka.tp3.exceptions;

/**
 * @author Celine, Leane et Marine
 */
public class NodeAlreadyExistException extends Exception {
	private static final long serialVersionUID = 1L;

	/** Create the exception called if the node already exists */
	public NodeAlreadyExistException(String node) {
		super("The file is incorrect. The node " + node + " already exists.");
	}

}
