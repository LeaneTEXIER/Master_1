# Passerelle REST.  
Marine DEFFONTAINE, Léane TEXIER, Céline PETITPRE. (groupe 3)  
04/03/2019  
Responsable : Alexandre GARNIER  

## Introduction

Ce TP est une implémentation d'une passerelle REST.  
Le projet a été créé et compilé avec Maven.  
Pour lancer : 
Se placer dans le dossier simple-service  
Puis lancer la commande : mvn exec:java  

L' uml du projet se trouve : 'UML.jpg'  

## Architecture

Tous les fichiers se trouvent dans le package com.tp2
Le serveur est lancé dans Main.java
Les requêtes POST, GET, PUT et DELETE sont gérées dans MyResource.java
Les requêtes au serveur FTP sont gérés par FTP.java
La classe ParamConf.java permet de parser le fichier config.properties afin de récuperer mode, adresse du serveur FTP, username, password et port.

## Exemple de get

	Ls: http://localhost:8080/myapp/myresource/ls

## Code samples

### 1. Parsage du ficher config.properties
```java
	public static void parse() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			adress = prop.getProperty("adress");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			mode = prop.getProperty("mode");
			port = Integer.parseInt(prop.getProperty("port"));
```
### 2. Exemple de GET
```java
/**
	 * Gets the current directory
	 * 
	 * @return String indicating the current directory
	 */
	@GET
	@Path("pwd")
	@Produces(MediaType.TEXT_PLAIN)
	public Response pwd() {
		return Response.ok(this.ftp.pwd(), MediaType.TEXT_PLAIN).build();
	}
```
### 3. Exemple de POST
```java
	/**
	 * Add a directory
	 * 
	 * @param directory
	 *            the new directory
	 * @return the response
	 */
	@POST
	@Path("addDirectory")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addDirectory(@QueryParam("directory") String directory) {
		return Response.ok(this.ftp.createDirectory(directory), MediaType.TEXT_PLAIN).build();
	}
```

### 4. Exemple de PUT

```java
	/**
	 * Rename a file
	 * 
	 * @param file
	 *            the file
	 * @param name
	 *            the new name
	 * @return the response
	 */
	@PUT
	@Path("rename")
	@Produces(MediaType.TEXT_PLAIN)
	public Response rename(@QueryParam("file") String file, @QueryParam("name") String name) {
		return Response.ok(this.ftp.rename(file, name), MediaType.TEXT_PLAIN).build();
	}
```

### 5. Exemple de DELETE

```java
	/**
	 * Delete a file
	 * 
	 * @param pathname
	 *            the pathname of the file
	 * @return the response
	 */
	@DELETE
	@Path("deleteFile/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteFile(@PathParam("param") String pathname) {
		return Response.ok(this.ftp.deleteFile(pathname), MediaType.TEXT_PLAIN).build();
	}
    ```