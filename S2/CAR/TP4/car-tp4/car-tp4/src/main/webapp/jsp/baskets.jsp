<%@page import="car.tp4.entity.Panier"%>
<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Baskets</title>
	</head>
    <body>
        <h2>Baskets</h2>
        <%
            Collection<Panier> paniers = (Collection<Panier>) request.getAttribute("baskets");

        int i = 0;
        for (Panier p: paniers) {
        	out.print("Basket "+ i++ + ":");
        	out.print("<ul>");
        	out.print(p.getBooks());
        	out.print(p.toString());
        	/* for (Book b : p.getBooks()){
        		out.print("<li>"+b.toString()+"</li>");
        	} */
        	out.print("</ul>");
        }
        %>
    </body>
</html>
