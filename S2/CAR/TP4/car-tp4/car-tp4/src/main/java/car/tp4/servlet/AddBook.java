package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;

/**
 * WebServlet to addBook
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@WebServlet("/addBook")
public class AddBook extends HttpServlet {

	@EJB
	private BookBean bookBean;

	/**
	 * Add a book
	 */
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
