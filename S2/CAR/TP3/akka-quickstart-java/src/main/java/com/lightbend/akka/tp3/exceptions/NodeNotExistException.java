package com.lightbend.akka.tp3.exceptions;

/**
 * @author Celine, Leane et Marine
 */
public class NodeNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	/** Create the exception called if the node doesn't exist */
	public NodeNotExistException(String node) {
		super("The file is incorrect. The node " + node + " don't exist.");
	}
}
