package com.lightbend.akka.tp3.message;

import java.util.Map;

import akka.actor.ActorRef;

/**
 * @author Celine, Leane et Marine
 */
public class Visualization {

	private Map<String, ActorRef> nodes;

	/**
	 * Message to get the visualization
	 * 
	 * @param nodes the map which link the ActorRef with its name
	 */
	public Visualization(Map<String, ActorRef> nodes) {
		this.nodes = nodes;
	}

	/**
	 * Get the nodes
	 * 
	 * @return the map which link the ActorRef with its name
	 */
	public Map<String, ActorRef> getNodes() {
		return this.nodes;
	}

}
