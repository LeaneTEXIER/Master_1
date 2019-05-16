package com.lightbend.akka.tp3.message;

import akka.actor.ActorRef;

/**
 * 
 * @author Celine, Leane et Marine
 *
 */
public class AddChild {

	private ActorRef child;
	private String name;

	/**
	 * Message to add a child
	 * 
	 * @param actorRef the actor
	 * @param name     the name
	 */
	public AddChild(ActorRef actorRef, String name) {
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
