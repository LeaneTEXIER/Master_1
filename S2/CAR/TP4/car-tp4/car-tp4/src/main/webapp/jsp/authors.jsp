<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authors</title>
    </head>
    <body>
        <h2>The authors in the database are :</h2>
        <ul>
        <%
            Collection<String> authors = (Collection<String>) request.getAttribute("authors");

            
			for (String a : authors) {
				out.println("<li> "+ a + "</li>");
			}
        %>
    </ul>
    </body>
</html>


		