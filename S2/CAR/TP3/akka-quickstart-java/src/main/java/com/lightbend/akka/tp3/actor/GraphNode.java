package com.lightbend.akka.tp3.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lightbend.akka.tp3.message.AddLink;
import com.lightbend.akka.tp3.message.Message;
import com.lightbend.akka.tp3.message.RemoveLink;
import com.lightbend.akka.tp3.message.RemoveNode;
import com.lightbend.akka.tp3.message.Visualization;

import akka.actor.ActorRef;

/**
 * 
 * @author Celine, Leane et Marine
 */
public class GraphNode extends Node {

	private List<Integer> idMessages;

	/**
	 * Create a node in a graph
	 * 
	 * @param name      the name of the node
	 * @param neighbors a list of the neighbors of the node
	 */
	public GraphNode(String name, List<ActorRef> neighbors) {
		super(name, neighbors);
		this.idMessages = new ArrayList<>();
	}

	/**
	 * Send the message to all neighbors
	 * 
	 * @param message the message
	 */
	protected void send(Message message) {
		for (ActorRef actorRef : actorsLinked) {
			actorRef.forward(message, getContext());
		}
	}

	/**
	 * Add a link
	 * 
	 * @param actor the actor we want to link
	 */
	private void addLink(AddLink actor) {
		ActorRef child = actor.getNeighbour();
		if (!this.actorsLinked.contains(child)) {
			this.actorsLinked.add(child);
			System.out.println("The node " + actor.getName() + " was added to the neighbors of the node " + this.name);
		} else {
			System.out.println("The node " + actor.getName() + " was not added to the node " + this.name
					+ " because it's already one of the neighbors of this node");
		}
	}

	/**
	 * Remove a link
	 * 
	 * @param actor the actor we want to remove of the neighbors
	 */
	private void removeLink(RemoveLink actor) {
		ActorRef child = actor.getNeighbour();
		if (this.actorsLinked.contains(child)) {
			this.actorsLinked.remove(child);
			System.out
					.println("The node " + actor.getName() + " was removed to the neighbors of the node " + this.name);
		} else {
			System.out.println("The node " + actor.getName() + " is not one of the neighbors of the node " + this.name);
		}
	}

	/**
	 * Remove a node
	 * 
	 * @param nodes the node to be removed
	 */
	private void removeNode(RemoveNode nodes) {
		for (ActorRef neighbour : actorsLinked) {
			neighbour.tell(new RemoveLink(nodes.getActor(), name), ActorRef.noSender());
		}
	}

	/**
	 * Print the visualization of the node with its neighbors
	 * 
	 * @param v the visualization which contains a map which linked the ActorRef and
	 *          its name
	 */
	private void visualization(Visualization v) {
		if (actorsLinked.isEmpty()) {
			System.out.println("No neighbour for node " + this.name + "\n");
		} else {
			String res = "Neighbors of node " + this.name + " are : \n";
			Map<String, ActorRef> actors = v.getNodes();
			for (String nameActor : actors.keySet()) {
				ActorRef actor = actors.get(nameActor);
				if (actorsLinked.contains(actor)) {
					res += "Node " + nameActor + "\n";
				}
			}
			System.out.println(res);
		}
	}

	@Override
	protected void treatMessage(Object message) {
		if (message instanceof Message) {
			Message m = (Message) message;
			if (!this.idMessages.contains(m.getId())) {
				System.out.println("\"" + m.getMessage() + "\" was received by the node " + this.name);
				this.idMessages.add(m.getId());
				this.send(m);
			}
		} else if (message instanceof AddLink) {
			AddLink c = (AddLink) message;
			this.addLink(c);
		} else if (message instanceof RemoveLink) {
			RemoveLink c = (RemoveLink) message;
			this.removeLink(c);
		} else if (message instanceof RemoveNode) {
			RemoveNode c = (RemoveNode) message;
			this.removeNode(c);
		} else if (message instanceof Visualization) {
			Visualization c = (Visualization) message;
			this.visualization(c);
		} else {
			System.out.println("The message received is not right");
		}
	}
}
