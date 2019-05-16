package com.lightbend.akka.tp3.actor;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;

/**
 * @author Celine, Leane et Marine
 *
 */
public abstract class Node extends UntypedAbstractActor {

	protected String name;
	protected List<ActorRef> actorsLinked = new ArrayList<ActorRef>();

	/**
	 * Create a Node
	 * 
	 * @param name
	 *            the name of the node
	 * @param actorsLinked
	 *            the actors linked
	 */
	public Node(String name, List<ActorRef> actorsLinked) {
		this.name = name;
		this.actorsLinked = actorsLinked;
	}

	/**
	 * Treat the message
	 * 
	 * @param message
	 *            the Message
	 */
	protected abstract void treatMessage(Object message);

	/**
	 * Return a list of the linked actors
	 * 
	 * @return a list of the linked actors
	 */
	protected List<ActorRef> getActorsLinked() {
		return this.actorsLinked;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see akka.actor.UntypedAbstractActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object message) throws Throwable {
		this.treatMessage(message);
	}

}
