# Akka – Transfert de données
Marine DEFFONTAINE, Léane TEXIER, Céline PETITPRE. (groupe 3)  
20/03/2019  
Responsable : Alexandre GARNIER  

## Introduction

Le but de ce TP est de mettre en œuvre des acteurs répartis avec Akka.  

## Architecture

Les fichiers principaux tel que le main se trouve dans le package 'com.lightbend.akka.tp3'.  
L'UML associé se trouve dans UML/tp3.jpg.  

Les classes liées aux messages se trouvent dans le package 'com.lightbend.akka.tp3.message'.  
L'UML associé se trouve dans UML/tp3Messages.jpg.  

Nous pouvons retrouver les exceptions dans le package 'com.lightbend.akka.tp3.exceptions'.  
L'UML associé se trouve dans UML/tp3Exceptions.jpg.  

Pour finir les acteurs se trouvent dans le package 'com.lightbend.akka.tp3.actor'.  
L'UML associé se trouve dans UML/tp3Actors.jpg.  

La classe ParamConf.java permet de parser le fichier configTP.properties ou le fichier de properties mis en argument afin de récuperer notre type (graphe ou arbre), les noeuds ainsi que les différents liens.

## Code samples

### 1. Traitement d'un message dans un graphe
```java
    @Override
	protected void treatMessage(Object message) {
		if (message instanceof Message) {
			Message m = (Message) message;
			if (!this.idMessages.contains(m.getId())) {
				System.out.println("\"" + m.getMessage() + "\" was received by the node " + this.name);
				this.idMessages.add(m.getId());
				this.send(m);
			}
		} 
		...
	}

```

### 2. Méthode permettant l'interaction
```java 
private static void interactionInAGraph(Scanner scanner) {
		// What user wants to do
		System.out.println(
				"Please type the type of the message : message / addLink / removeLink / changeLink / addNode / removeNode");
		String typeAction = scanner.nextLine().toLowerCase().trim();
		while (!typeAction.equals("message") && !typeAction.equals("addlink") && !typeAction.equals("removelink")
				&& !typeAction.equals("changelink") && !typeAction.equals("addnode")
				&& !typeAction.equals("removenode")) {
			System.out.println(
					"The type was incorrect, try again please. The type must be one of them : message / addLink / removeLink / changeLink / addNode / removeNode");
			typeAction = scanner.nextLine().toLowerCase().trim();
		}
		switch (typeAction) {
		case "message":
			sendMessageGraph(scanner);
			break;
		...
		}
	}
```

### 3. Exemple de code d'exception 
```java
    public class ListNodesMissingException extends Exception {
    	private static final long serialVersionUID = 1L;
    
    	/** Create the exception called if the list of nodes is missing */
    	public ListNodesMissingException() {
    		super("The file is incorrect. The list of nodes must be specified.");
    	}
    
    }
```

### 4. Parsage du fichier de properties
```java
    public ParamConf(String name_file) throws TypeGraphException, NodeNotExistException, ListNodesMissingException, NodeAlreadyExistException, SameNodeLinkImpossibleException {
    		...
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
    			...
    		}
```

### 5. Exemple de classe du package message
```java
    public class RemoveNode {
    
    	private ActorRef actor;
    	private List<ActorRef> actorRefs;
    
    	public RemoveNode(ActorRef actor, List<ActorRef> actorRefs) {
    		this.actor = actor;
    		this.actorRefs = actorRefs;
    	}
    
    	public List<ActorRef> getListActorRefs() {
    		return this.actorRefs;
    	}
    
    	public ActorRef getActor() {
    		return this.actor;
    	}
    }
```




