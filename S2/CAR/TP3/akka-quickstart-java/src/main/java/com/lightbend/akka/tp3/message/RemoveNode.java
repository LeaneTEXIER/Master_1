package com.lightbend.akka.tp3.message;

import java.util.List;

import akka.actor.ActorRef;

/**
 * 
 * @author Celine, Leane et Marine
 *
 */
public class RemoveNode {

	private ActorRef actor;
	private List<ActorRef> actorRefs;

	/**
	 * Message to remove a node
	 * 
	 * @param actorRef the actor
	 * @param name     the name
	 */
	public RemoveNode(ActorRef actor, List<ActorRef> actorRefs) {
		this.actor = actor;
		this.actorRefs = actorRefs;
	}

	/**
	 * Get the list of the actorRefs
	 * 
	 * @return the list of the actorRefs
	 */
	public List<ActorRef> getListActorRefs() {
		return this.actorRefs;
	}

	/**
	 * Get the actor / node to be removed
	 * 
	 * @return the actor
	 */
	public ActorRef getActor() {
		return this.actor;
	}

}
