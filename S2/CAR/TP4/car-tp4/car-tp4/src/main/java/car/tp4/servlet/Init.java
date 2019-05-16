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
 * WebServlet to add two books
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@WebServlet("/init")
public class Init extends HttpServlet {

	@EJB
	private InitAndAuthor initSession;

	/**
	 * Create the object init
	 */
	public Init() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		initSession.init();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/init.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
