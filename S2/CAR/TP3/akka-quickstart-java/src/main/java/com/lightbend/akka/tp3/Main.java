package com.lightbend.akka.tp3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.lightbend.akka.tp3.actor.GraphNode;
import com.lightbend.akka.tp3.actor.TreeNode;
import com.lightbend.akka.tp3.message.AddChild;
import com.lightbend.akka.tp3.message.AddLink;
import com.lightbend.akka.tp3.message.Message;
import com.lightbend.akka.tp3.message.RemoveChild;
import com.lightbend.akka.tp3.message.RemoveLink;
import com.lightbend.akka.tp3.message.RemoveNode;
import com.lightbend.akka.tp3.message.Visualization;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author Celine, Leane et Marine
 *
 */
public class Main {

	private static Map<String, ActorRef> nodes;
	private static String type;
	private static ActorSystem system;
	private static int idMessage = 0;

	/**
	 * Verify if the node exists
	 */
	private static boolean verif(String node) {
		return nodes.containsKey(node);
	}

	/**
	 * Handle the interactions in the graph
	 * 
	 * @param scanner the scanner
	 */
	private static void interactionInAGraph(Scanner scanner) {
		// What user wants to do
		System.out.println(
				"Please type the type of the message : message / addLink / removeLink / changeLink / addNode / removeNode / visualization");
		String typeAction = scanner.nextLine().toLowerCase().trim();
		while (!typeAction.equals("message") && !typeAction.equals("addlink") && !typeAction.equals("removelink")
				&& !typeAction.equals("changelink") && !typeAction.equals("addnode") && !typeAction.equals("removenode")
				&& !typeAction.equals("visualization")) {
			System.out.println(
					"The type was incorrect, try again please. The type must be one of them : message / addLink / removeLink / changeLink / addNode / removeNode / visualization");
			typeAction = scanner.nextLine().toLowerCase().trim();
		}
		switch (typeAction) {
		case "message":
			sendMessageGraph(scanner);
			break;
		case "addlink":
			addLink(scanner);
			break;
		case "removelink":
			removeLink(scanner);
			break;
		case "changelink":
			changeLink(scanner);
			break;
		case "addnode":
			addNodeGraph(scanner);
			break;
		case "removenode":
			removeNode(scanner);
			break;
		case "visualization":
			visualization();
			break;
		default:
			break;
		}
	}

	/**
	 * Handle the interactions in a tree
	 * 
	 * @param scanner the scanner
	 */
	private static void interactionInATree(Scanner scanner) {
		// What user wants to do
		System.out.println(
				"Please type the type of the message : message / addChild / removeChild /  addNode / removeNode / visualization");
		String typeAction = scanner.nextLine().toLowerCase().trim();
		while (!typeAction.equals("message") && !typeAction.equals("addchild") && !typeAction.equals("removechild")
				&& !typeAction.equals("addnode") && !typeAction.equals("removenode")
				&& !typeAction.equals("visualization")) {
			System.out.println(
					"The type was incorrect, try again please. The type must be one of them : message / addChild / removeChild /  addNode / removeNode / visualization");
			typeAction = scanner.nextLine().toLowerCase().trim();
		}

		switch (typeAction) {
		case "message":
			sendMessageTree(scanner);
			break;
		case "addchild":
			addChildTree(scanner);
			break;
		case "removechild":
			removeChildTree(scanner);
			break;
		case "addnode":
			addNodeTree(scanner);
			break;
		case "removenode":
			removeNode(scanner);
			break;
		case "visualization":
			visualization();
			break;
		default:
			break;
		}
	}

	/**
	 * Get the message to send
	 * 
	 * @param scanner the scanner
	 * @return the message to send
	 */
	private static String getMessageToSend(Scanner scanner) {
		System.out.println("Please type the message you want to send");
		return scanner.nextLine();
	}

	/**
	 * Get the node who sent the message
	 * 
	 * @param scanner the scanner
	 * @return the name of the node who sent the message
	 */
	private static String getNodeWhoSendMessage(Scanner scanner) {
		System.out.println("Please type the node who send the message");
		String node = scanner.nextLine();
		while (!verif(node)) {
			System.out.println("The name of the node is wrong, please try again");
			node = scanner.nextLine();
		}
		return node;
	}

	/**
	 * Send a Message in a graph
	 * 
	 * @param scanner the scanner
	 */
	private static void sendMessageGraph(Scanner scanner) {
		// Message to send
		String message = getMessageToSend(scanner);

		// Node
		String node = getNodeWhoSendMessage(scanner);

		// Send message
		nodes.get(node).tell(new Message(idMessage++, message), ActorRef.noSender());
	}

	/**
	 * Send a Message in a tree
	 * 
	 * @param scanner the scanner
	 */
	private static void sendMessageTree(Scanner scanner) {
		// Message to send
		String message = getMessageToSend(scanner);

		// Node
		String node = getNodeWhoSendMessage(scanner);

		// Send message
		nodes.get(node).tell(message, ActorRef.noSender());
	}

	/**
	 * Add a child in a tree
	 * 
	 * @param scanner the scanner
	 */
	private static void addChildTree(Scanner scanner) {
		String parent, child;
		System.out.println("Please type the name of the node who want to add a child");
		parent = scanner.nextLine();
		while (!verif(parent)) {
			System.out.println("The name of the node is wrong, please try again");
			parent = scanner.nextLine();
		}
		System.out.println("Please type the name of the node to add");
		child = scanner.nextLine();
		while (!verif(child) || child.equals(parent)) {
			System.out.println("The name of the node is wrong, please try again");
			child = scanner.nextLine();
		}
		nodes.get(parent).tell(new AddChild(nodes.get(child), child), ActorRef.noSender());
	}

	/**
	 * Remove a child in a tree
	 * 
	 * @param scanner the scanner
	 */
	private static void removeChildTree(Scanner scanner) {
		String parent, child;
		System.out.println("Please type the name of the node who want to remove a child");
		parent = scanner.nextLine();
		while (!verif(parent)) {
			System.out.println("The name of the node is wrong, please try again");
			parent = scanner.nextLine();
		}
		System.out.println("Please type the name of the node to removed");
		child = scanner.nextLine();
		while (!verif(child) || child.equals(parent)) {
			System.out.println("The name of the node is wrong, please try again");
			child = scanner.nextLine();
		}
		nodes.get(parent).tell(new RemoveChild(nodes.get(child), child), ActorRef.noSender());
	}

	/**
	 * Get the node to add
	 * 
	 * @param scanner the scanner
	 * @return the name of the node to add
	 */
	private static String getNodeToAdd(Scanner scanner) {
		String node;
		System.out.println("Please type the name of the node you want to add");
		node = scanner.nextLine();
		while (verif(node)) {
			System.out.println("The name of the node was already used, please type a new one");
			node = scanner.nextLine();
		}
		return node;
	}

	/**
	 * Add a node to a tree
	 * 
	 * @param scanner the scanner
	 */
	private static void addNodeTree(Scanner scanner) {
		String nodeName = getNodeToAdd(scanner);
		nodes.put(nodeName, system.actorOf(Props.create(TreeNode.class, nodeName, new ArrayList<ActorRef>())));
	}

	/**
	 * Add a node to a graph
	 * 
	 * @param scanner the scanner
	 */
	private static void addNodeGraph(Scanner scanner) {
		String nodeName = getNodeToAdd(scanner);
		nodes.put(nodeName, system.actorOf(Props.create(GraphNode.class, nodeName, new ArrayList<ActorRef>())));
	}

	/**
	 * Get the node to remove
	 * 
	 * @param scanner the scanner
	 * @return the name of the node to remove
	 */
	private static String getNodeToRemove(Scanner scanner) {
		String node;
		System.out.println("Please type the name of the node you want to remove");
		node = scanner.nextLine();
		while (!verif(node)) {
			System.out.println("The name of the node is wrong, please try again");
			node = scanner.nextLine();
		}
		return node;
	}

	/**
	 * Remove a node
	 * 
	 * @param scanner the scanner
	 */
	private static void removeNode(Scanner scanner) {
		String nodeName = getNodeToRemove(scanner);
		List<ActorRef> allActors = new ArrayList<ActorRef>(nodes.values());
		nodes.get(nodeName).tell(new RemoveNode(nodes.get(nodeName), allActors), ActorRef.noSender());
		nodes.remove(nodeName);
	}

	/**
	 * Add a link
	 * 
	 * @param scanner the scanner
	 */
	private static void addLink(Scanner scanner) {
		String node1, node2;
		System.out.println("Please type the name of the first node you want to link");
		node1 = scanner.nextLine();
		while (!verif(node1)) {
			System.out.println("The name of the node is wrong, please try again");
			node1 = scanner.nextLine();
		}
		System.out.println("Please type the name of the second node you want to link");
		node2 = scanner.nextLine();
		while (!verif(node2) || node2.equals(node1)) {
			System.out.println("The name of the node is wrong, please try again");
			node2 = scanner.nextLine();
		}
		nodes.get(node1).tell(new AddLink(nodes.get(node2), node2), ActorRef.noSender());
		nodes.get(node2).tell(new AddLink(nodes.get(node1), node1), ActorRef.noSender());
	}

	/**
	 * Remove a link
	 * 
	 * @param scanner the scanner
	 */
	private static void removeLink(Scanner scanner) {
		String node1, node2;
		System.out.println("Please type the name of the first node");
		node1 = scanner.nextLine();
		while (!verif(node1)) {
			System.out.println("The name of the node is wrong, please try again");
			node1 = scanner.nextLine();
		}
		System.out.println("Please type the name of the second node");
		node2 = scanner.nextLine();
		while (!verif(node2) || node2.equals(node1)) {
			System.out.println("The name of the node is wrong, please try again");
			node2 = scanner.nextLine();
		}
		nodes.get(node1).tell(new RemoveLink(nodes.get(node2), node2), ActorRef.noSender());
		nodes.get(node2).tell(new RemoveLink(nodes.get(node1), node1), ActorRef.noSender());
	}

	/**
	 * Change a link
	 * 
	 * @param scanner the scanner
	 */
	private static void changeLink(Scanner scanner) {
		String node1, node2, node3;
		System.out.println(
				"Please type the name of the first node (the node you want to remove a link and add a new one instead) ");
		node1 = scanner.nextLine();
		while (!verif(node1)) {
			System.out.println("The name of the node is wrong, please try again");
			node1 = scanner.nextLine();
		}
		System.out.println("Please type the name of the node you want to remove the link");
		node2 = scanner.nextLine();
		while (!verif(node2) || node2.equals(node1)) {
			System.out.println("The name of the node is wrong, please try again");
			node2 = scanner.nextLine();
		}
		System.out.println("Please type the name of the node you want to link");
		node3 = scanner.nextLine();
		while (!verif(node3) || node3.equals(node1) || node3.equals(node2)) {
			System.out.println("The name of the node is wrong, please try again");
			node3 = scanner.nextLine();
		}
		nodes.get(node1).tell(new RemoveLink(nodes.get(node2), node2), ActorRef.noSender());
		nodes.get(node2).tell(new RemoveLink(nodes.get(node1), node1), ActorRef.noSender());
		nodes.get(node1).tell(new AddLink(nodes.get(node3), node3), ActorRef.noSender());
		nodes.get(node3).tell(new AddLink(nodes.get(node1), node1), ActorRef.noSender());
	}

	/**
	 * Print the visualization
	 */
	private static void visualization() {
		for (String name : nodes.keySet()) {
			nodes.get(name).tell(new Visualization(nodes), ActorRef.noSender());
		}
	}

	/**
	 * Main method : create nodes and links depending on the config file and send
	 * messages
	 * 
	 * @param args the config file (not necessary)
	 */
	public static void main(String[] args) {
		// Creation du systeme suivant le fichier de config
		try {
			String nameFile = "configTP.properties";
			if (args.length == 1) {
				nameFile = args[0];
			}
			ParamConf paramConf = new ParamConf(nameFile);
			nodes = paramConf.getActors();
			type = paramConf.getType();
			system = paramConf.getSystem();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (type.equals("graph")) {
				interactionInAGraph(scanner);
			} else if (type.equals("tree")) {
				interactionInATree(scanner);
			}
		}
	}
}
