package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.BookBean;

/**
 * WebServlet to list all the books depending of filters (author and title)
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@WebServlet("/bookauthorsandtitle")
public class BookTriAuthorAndTitleServlet extends HttpServlet {

	@EJB
	private BookBean bookBean;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		if (title == null)
			title = "";
		if (author == null) {
			request.setAttribute("books", bookBean.getAllBooksTitle(title));
		} else {
			request.setAttribute("books", bookBean.getAllBooksAuthorAndTitle(author, title));
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/books.jsp");
		dispatcher.forward(request, response);
	}
}
