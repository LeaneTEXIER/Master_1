package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.InitAndAuthor;

/**
 * WebServlet to list all the authors
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@WebServlet("/authors")
public class GetAuthors extends HttpServlet {

	@EJB
	private InitAndAuthor authorSession;

	/**
	 * Get the authors
	 */
	public GetAuthors() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("authors", authorSession.getAllAuthors());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/authors.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
