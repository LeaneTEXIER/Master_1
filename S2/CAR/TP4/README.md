# Java EE
Marine DEFFONTAINE, Léane TEXIER, Céline PETITPRE. (groupe 3)  
29/04/2019  
Responsable : Alexandre GARNIER  

## Introduction
Le but de ce TP est de mettre en œuvre une application répartie client/serveur 3 tiers avec Java EE. 

## Architecture
Les fichiers se trouvent dans le répertoire car-tp4/car-tp4.    
Pour lancer l'application il faut lancer mvn clean package tomee:run dans ce répertoire.  

Les fichiers .jsp se trouvent dans car-tp4/car-tp4/src/main/webapp/jsp.  

Les fichiers .java entity ou servlet se trouvent dans car-tp4/car-tp4/src/main/java/car/tp4/entity ou car-tp4/car-tp4/src/main/java/car/tp4/servet respectivement.  

L'UML UML_Entity_Bean correspond aux entity et aux fichiers bean.    
L'UML UML_Servlet correspond aux Servlet.  

## Exemples d'URL

Pour afficher tous les livres :  
http://localhost:8080/books  

Pour ajouter 2 livres prédefinis :  
http://localhost:8080/init  

Pour trier les livres par années croissantes :  
http://localhost:8080/yearsort

Pour afficher tous les auteurs :  
http://localhost:8080/authors

Pour ajouter un livre :  
http://localhost:8080/addBook

Pour passer commande :  
http://localhost:8080/basket

Pour faire une recherche par titre ou partie de titre :  
http://localhost:8080/bookauthorsandtitle?title=fadette

Pour faire une recherche par auteur :  
http://localhost:8080/bookauthorsandtitle?author=george%20sand

Pour faire une recherche par auteur et titre ou partie de titre :  
http://localhost:8080/bookauthorsandtitle?author=george%20sand&title=fadette 
 


## Code samples

##### 1.  Un exemple de code java embarqué dans de l'html (addBook.jsp)
```
<%
if (request.getParameter("title") != null){
        try {
            out.println("<p>");
            out.println("Book added : ");
            out.println("</p>");
            out.println("<p>");
            out.println("Title : " + request.getParameter("title"));
            out.println("</p>");
            out.println("<p>");
            out.println("Author : " + request.getParameter("name_author"));
            out.println("</p>");
            out.println("<p>");
            out.println("Year :  " + request.getParameter("year"));
            out.println("</p>");
        } finally {            
            out.close();
        }
    }    
%>
```

##### 2. Un exemple de fonction avec requete SQL dans un Entity Bean permettant de récupérer les livres triés par année croissante
```java
public List<Book> getAllBooksTriYear() {
		Query query = entityManager.createQuery("SELECT m from Book as m order by m.year asc");
		return query.getResultList();
	}
```


##### 3. Un exemple de gestion de l'entity manager, relié à la "book-pu"
```java
@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;
```

##### 4. Le code xml correspondant à "book-pu"
```xml
<persistence-unit name="book-pu" transaction-type="JTA">
        <jta-data-source>book</jta-data-source>
        <class>car.tp4.entity.Book</class>
        <properties>
            <property name="openjpa.jdbc.SynchronizeMappings" value="buildSchema(ForeignKeys=true)"/>
        </properties>
</persistence-unit>
 ```


##### 5. Un exemple simple de servlet (faisant appel à une page jsp pour le code html)

```java
@WebServlet("/addBook")
public class AddBook extends HttpServlet {

	@EJB
	private BookBean bookBean;

	public AddBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/addBook.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String author = (String) request.getParameter("name_author");
		String title = (String) request.getParameter("title");
		Integer year = Integer.parseInt(request.getParameter("year"));
		bookBean.addBook(new Book(title, author, year));
		doGet(request, response);
	}

}
```
