package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.PanierBean;

/**
 * WebServlet to list all the baskets
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@WebServlet("/baskets")
public class BasketsServlet extends HttpServlet {

	@EJB
	private PanierBean panierBean;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("baskets", panierBean.getAllPaniers());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/baskets.jsp");
		dispatcher.forward(request, response);
	}
}
