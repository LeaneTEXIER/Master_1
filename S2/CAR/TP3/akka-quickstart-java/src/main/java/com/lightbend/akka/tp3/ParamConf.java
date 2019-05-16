package com.lightbend.akka.tp3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.lightbend.akka.tp3.actor.GraphNode;
import com.lightbend.akka.tp3.actor.TreeNode;
import com.lightbend.akka.tp3.exceptions.ListNodesMissingException;
import com.lightbend.akka.tp3.exceptions.NodeAlreadyExistException;
import com.lightbend.akka.tp3.exceptions.NodeNotExistException;
import com.lightbend.akka.tp3.exceptions.SameNodeLinkImpossibleException;
import com.lightbend.akka.tp3.exceptions.TypeGraphException;
import com.lightbend.akka.tp3.message.AddChild;
import com.lightbend.akka.tp3.message.AddLink;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Celine, Marine et Leane
 *
 */
public class ParamConf {

	public String type;
	public Map<String, ActorRef> actors;
	final ActorSystem system = ActorSystem.create("TP3");

	/**
	 * Parse the file given (file properties) and create nodes and links
	 * 
	 * @throws TypeGraphException              if the type of the graph is not tree
	 *                                         and not graph
	 * @throws NodeNotExistException           if the node is not in the list of
	 *                                         nodes
	 * @throws ListNodesMissingException       if the list of nodes is missing
	 * @throws NodeAlreadyExistException       if the node is already in the list of
	 *                                         nodes
	 * @throws SameNodeLinkImpossibleException if we want to link a node with itself
	 */
	public ParamConf(String name_file) throws TypeGraphException, NodeNotExistException, ListNodesMissingException,
			NodeAlreadyExistException, SameNodeLinkImpossibleException {
		actors = new HashMap<String, ActorRef>();
		Properties prop = new Properties();
		InputStream input = null;

		String[] nodes, nodesChildren;
		String allNodes, children, childName, nodeName, parentName;
		ActorRef nodeActor, childActor, parentActor;

		try {
			input = new FileInputStream(name_file);
			prop.load(input);
			// Check type and display it
			type = prop.getProperty("type").toLowerCase().trim();
			if (!type.equals("tree") && !type.equals("graph")) {
				throw new TypeGraphException();
			}
			System.out.println("Type of graph is : " + type);

			// The nodes
			allNodes = prop.getProperty("nodes");
			if (allNodes == null) {
				throw new ListNodesMissingException();
			}
			nodes = allNodes.split(",");
			for (String node : nodes) {
				nodeName = node.trim();
				nodeActor = actors.get(nodeName);
				if (nodeActor == null) {
					if (type.equals("graph")) {
						actors.put(nodeName,
								system.actorOf(Props.create(GraphNode.class, nodeName, new ArrayList<ActorRef>())));
					} else {
						actors.put(nodeName,
								system.actorOf(Props.create(TreeNode.class, nodeName, new ArrayList<ActorRef>())));
					}
					System.out.println(nodeName + " create");
				} else {
					throw new NodeAlreadyExistException(node);
				}
			}

			// Add children or link
			for (String parent : nodes) {
				children = prop.getProperty(parent);
				if (children != null) {
					nodesChildren = children.split(",");
					for (String child : nodesChildren) {
						childName = child.trim();
						childActor = actors.get(childName);
						parentName = parent.trim();
						if (childName.equals(parentName)) {
							throw new SameNodeLinkImpossibleException(childName);
						}
						parentActor = actors.get(parentName);
						if (childActor != null) {
							if (type.equals("graph")) {
								parentActor.tell(new AddLink(childActor, childName), ActorRef.noSender());
								childActor.tell(new AddLink(parentActor, parentName), ActorRef.noSender());
							} else {
								actors.get(parent).tell(new AddChild(childActor, childName), ActorRef.noSender());
							}
						} else {
							throw new NodeNotExistException(childName);
						}
					}
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Get the actors
	 * 
	 * @return a map of the name and actorRef of the actors
	 */
	public Map<String, ActorRef> getActors() {
		return this.actors;
	}

	/**
	 * Get the type of the graph
	 * 
	 * @return a string indicating the name of the graph
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Get the ActorSystem
	 * 
	 * @return the ActorSystem
	 */
	public ActorSystem getSystem() {
		return this.system;
	}

}
