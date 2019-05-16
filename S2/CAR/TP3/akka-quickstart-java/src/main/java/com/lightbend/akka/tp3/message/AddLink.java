package com.lightbend.akka.tp3.message;

import akka.actor.ActorRef;

/**
 * 
 * @author Celine, Leane et Marine
 *
 */
public class AddLink {

	private ActorRef neighbour;
	private String name;

	/**
	 * Message to add a link
	 * 
	 * @param actorRef the actor
	 * @param name     the name
	 */
	public AddLink(ActorRef actorRef, String name) {
		this.neighbour = actorRef;
		this.name = name;
	}

	/**
	 * Get the neighbour
	 * 
	 * @return the neighbour
	 */
	public ActorRef getNeighbour() {
		return this.neighbour;
	}

	/**
	 * Get the name of the neighbour
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

}
