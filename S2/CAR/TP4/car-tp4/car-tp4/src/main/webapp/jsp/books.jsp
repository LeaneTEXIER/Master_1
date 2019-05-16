<%@page import="car.tp4.entity.Book"%>
<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <script src="https://www.w3schools.com/lib/w3.js"></script>
        <script>
			function search() {
			  var inputAuthor = document.getElementById("inputAuthor");
			  var inputTitle = document.getElementById("inputTitle");
			  var filterAuthor = inputAuthor.value.toUpperCase();
			  var filterTitle = inputTitle.value.toUpperCase();
			  var table = document.getElementById("booksTable");
			  var tr = table.getElementsByTagName("tr");
			  for (i = 1; i < tr.length; i++) {
			    var tdAuthor = tr[i].getElementsByTagName("td")[0];
			    var tdTitle = tr[i].getElementsByTagName("td")[1];
			    if (tdAuthor) {
			      var txtValueAuthor = tdAuthor.textContent || tdAuthor.innerText;
			      var txtValueTitle = tdTitle.textContent || tdTitle.innerText;
			      if ((txtValueAuthor.toUpperCase() == filterAuthor || filterAuthor == "") && txtValueTitle.toUpperCase().indexOf(filterTitle) > -1) {
			        tr[i].style.display = "";
			      } else {
			        tr[i].style.display = "none";
			      }
			    }       
			  }
			}
		</script>
	</head>
    <body>
        <h2>Existing books</h2>
        <input type="text" id="inputAuthor" onkeyup="search()" placeholder="Search for authors..." title="Type in a name">
        <input type="text" id="inputTitle" onkeyup="search()" placeholder="Search for titles.." title="Type in a name">
        <table id="booksTable" border="solid">
        <thead>
	        <tr>
	            <th onclick="w3.sortHTML('#booksTable', '.item', 'td:nth-child(1)')" style="cursor:pointer">Author</th>
	            <th onclick="w3.sortHTML('#booksTable', '.item', 'td:nth-child(2)')" style="cursor:pointer">Title</th>
	            <th onclick="w3.sortHTML('#booksTable', '.item', 'td:nth-child(3)')" style="cursor:pointer">Year</th>
	            <th>Available</th>
	        </tr>
    	</thead> 
    	<tbody>
        <%
            Collection<Book> books = (Collection<Book>) request.getAttribute("books");

            for (Book book: books) {
                out.print("<tr class=\"item\"><td>" + book.getAuthor() + "</td><td>" + book.getTitle()+"</td><td>" + book.getYear() + "</td><td>" + book.isAvailable()+"</td></tr>");
            }
        %>
        </tbody>
    </table>
    </body>
</html>
