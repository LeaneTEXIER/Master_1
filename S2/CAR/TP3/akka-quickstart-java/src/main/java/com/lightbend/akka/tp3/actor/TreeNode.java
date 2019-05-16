package com.lightbend.akka.tp3.actor;

import java.util.List;
import java.util.Map;

import com.lightbend.akka.tp3.message.AddChild;
import com.lightbend.akka.tp3.message.RemoveChild;
import com.lightbend.akka.tp3.message.RemoveNode;
import com.lightbend.akka.tp3.message.Visualization;

import akka.actor.ActorRef;

/**
 * 
 * @author Celine, Leane et Marine
 *
 */
public class TreeNode extends Node {

	/**
	 * Create a node in a tree
	 * 
	 * @param name     the name of the node
	 * @param children the children of the node
	 */
	public TreeNode(String name, List<ActorRef> children) {
		super(name, children);
	}

	/**
	 * Send the message to all children of the node
	 * 
	 * @param message the message
	 */
	protected void send(String message) {
		for (ActorRef actorRef : actorsLinked) {
			actorRef.forward(message, getContext());
		}
	}

	/**
	 * Add a child
	 * 
	 * @param actor the actor to be added
	 */
	private void addChild(AddChild actor) {
		ActorRef child = actor.getChild();
		if (!this.actorsLinked.contains(child)) {
			this.actorsLinked.add(child);
			System.out.println("The child node " + actor.getName() + " was added to the node " + this.name);
		} else {
			System.out.println("The child node " + actor.getName() + " was not added to the node " + this.name
					+ " beacuse it's already a child of this node");
		}
	}

	/**
	 * Remove a child
	 * 
	 * @param actor the actor to be removed
	 */
	private void removeChild(RemoveChild actor) {
		ActorRef child = actor.getChild();
		if (this.actorsLinked.contains(child)) {
			this.actorsLinked.remove(child);
			System.out.println("The child node " + actor.getName() + " was removed from the node " + this.name);
		} else {
			System.out.println("The child node " + actor.getName() + " is not a child of the node " + this.name);
		}
	}

	/**
	 * Remove a node
	 * 
	 * @param nodes the node to be removed
	 */
	private void removeNode(RemoveNode nodes) {
		for (ActorRef parent : nodes.getListActorRefs()) {
			parent.tell(new RemoveChild(nodes.getActor(), name), ActorRef.noSender());
		}
	}

	/**
	 * Print the visualization of the node with its children
	 * 
	 * @param v the visualization which contains a map which linked the ActorRef and
	 *          its name
	 */
	private void visualization(Visualization v) {
		if (actorsLinked.isEmpty()) {
			System.out.println("No child for node " + this.name + "\n");
		} else {
			String res = "Children of node " + this.name + " are : \n";
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
		if (message instanceof String) {
			System.out.println("\"" + message + "\" was received by the node " + this.name);
			send((String) message);
		} else if (message instanceof AddChild) {
			AddChild c = (AddChild) message;
			this.addChild(c);
		} else if (message instanceof RemoveChild) {
			RemoveChild c = (RemoveChild) message;
			this.removeChild(c);
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
