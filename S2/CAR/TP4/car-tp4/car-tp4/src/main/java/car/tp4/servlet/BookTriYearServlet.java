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
 * WebServlet to list all the books depending of filter (year)
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@WebServlet("/yearsort")
public class BookTriYearServlet extends HttpServlet {

	@EJB
	private BookBean bookBean;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("books", bookBean.getAllBooksTriYear());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/books.jsp");
		dispatcher.forward(request, response);
	}
}
