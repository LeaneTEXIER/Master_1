package com.lightbend.akka.tp3.exceptions;

/**
 * @author Celine, Leane et Marine
 */
public class SameNodeLinkImpossibleException extends Exception {
	private static final long serialVersionUID = 1L;

	/** Create the exception called if the 2 nodes are the same */
	public SameNodeLinkImpossibleException(String node) {
		super("The link is impossible because it's the same node. Problem with the node " + node);
	}

}
