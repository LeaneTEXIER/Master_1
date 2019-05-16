package com.lightbend.akka.tp3.message;

import akka.actor.ActorRef;

/**
 * 
 * @author Celine, Leane et Marine
 *
 */
public class RemoveChild {

	private ActorRef child;
	private String name;

	/**
	 * Message to remove a child
	 * 
	 * @param actorRef the actor
	 * @param name     the name
	 */
	public RemoveChild(ActorRef actorRef, String name) {
		this.child = actorRef;
		this.name = name;
	}

	/**
	 * Get the child
	 * 
	 * @return the child
	 */
	public ActorRef getChild() {
		return this.child;
	}

	/**
	 * Get the name of the child
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

}
