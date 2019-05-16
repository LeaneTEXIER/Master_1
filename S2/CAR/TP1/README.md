# Implémentation d'un client FTP.  
Marine DEFFONTAINE, Léane TEXIER, Céline PETITPRE. (groupe 3)  
27/02/2019  
Responsable : Alexandre GARNIER  

## Introduction

Ce TP est une implémentation d'un client FTP. 
Le projet a été créé et compilé avec Maven.  
Pour compiler : mvn package  
Pour lancer le serveur : java -cp target/car-tp1-texier-0.0.1-SNAPSHOT.jar car.car_tp1_texier.Serveur [port] [path]  
Ensuite, pour tester le projet, utiliser login = "user" et password = "password" dans FileZilla.

## Architecture
2 classes d'exceptions pour la gestion d'erreur ont été créées : 
- ServeurExceptionPortProbleme (en cas de mauvais numéro de port)  
- ServeurExceptionArguments (en cas de mauvais arguments)  

Ces classes se trouvent dans le package Exceptions.
Le reste du code se trouve dans le package car.car_tp1_texier.
Des tests unitaires ont également été réalisés avec Junit.

## Code samples

##### 1.  Le Mock permettant de réaliser les tests
```java
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ftp = Mockito.mock(FtpRequest.class);
		output = Mockito.mock(OutputStreamWriter.class);
		Mockito.doCallRealMethod().when(ftp).setSendRequest(output);
		ftp.setSendRequest(output);
		connection = Mockito.mock(Socket.class);
		Mockito.doCallRealMethod().when(ftp).setConnection(connection);
		ftp.setConnection(connection);
	}
	
````

##### 2. Un exemple de test : 

```java

@Test
	public void testProcessUserOK() throws IOException {
		Mockito.doCallRealMethod().when(ftp).processUSER(Mockito.anyString());
		String reponse = ftp.processUSER("texierl");
		assertEquals("331 User OK, password required\n", reponse);
		
```


##### 3. Vérifier la correspondance Password/User en une seule ligne grâce à la HashMap :

```java
public static boolean passwordIsOk(String login, String password) {
		return password.equals(userPwd.get(login));
	}
```
	
##### 4. Non duplication du sendRequest.write() :

```java
/**
	 * Closes the connexion
	 * 
	 * @throws IOException
	 * @return the string indicating the state of the request
	 */
	public String processQUIT() throws IOException {
		String res = "";
		try {
			connection.close();
			res = "221 Deconnexion\n";
		} catch (IOException e) {
			e.printStackTrace();
			res = "500 The requested action did not take place\n";
		}
		open = false;
		sendRequest.write(res);
		sendRequest.flush();
		return res;
	}
```
##### 5. Utilisation d'un Switch/Case pour la gestion des commandes reçues :

```java
/**
	 * Process the request
	 * 
	 * @param request
	 *            the request to be processed
	 * @throws IOException
	 */
	public void processRequest(String request) throws IOException {
		String[] param = request.split("\\s+", 2);
		System.out.println("Execution of : " + request);
		switch (param[0]) {
		case "USER":
			login = param[1];
			this.processUSER(login);
			break;
		case "PASS":
			this.processPASS(login, param[1]);
			break;
		case "RETR":
			this.processRETR(param[1]);
			break;
		...
		default:
			this.processNotImplemented();
			break;
		}
	}

```
			

