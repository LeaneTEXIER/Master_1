<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Basket</title>
    </head>
    <body>
        <form method = "post">
        <%
            Collection<Book> books = (Collection<Book>) request.getAttribute("books");

            for (Book b: books) {
	            if (b.isAvailable()) {
					out.println("<input type=\"checkbox\" name=\"book\" value=\"" + b.getId() + "\">" + b.toString());
					out.println("<br>");
				}
			}
        %>
        
        <button type="submit" value="Submit">Create Basket</button>
    </form>
    </body>
</html>