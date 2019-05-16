<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>New book</title>
    </head>
<body>

<form method = "post">
    <div>
        <label for="title">Title :</label>
        <input type="text" required="required" id="title" value="${param.title}" name="title">
    </div>
    <div>
        <label for="name_author">Name Author :</label>
        <input type="text" required="required" id="name_author" value="${param.name_author}" name="name_author" >
    </div>
    <div>
        <label for="year">Year :</label>
        <input type="number" required="required" id="year" value="${param.year}" name="year">
    
    </div>

    <button type="submit" value="Submit">Submit</button>
</form>

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
</body>
</html>

