package car.tp4.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;
import car.tp4.entity.Panier;
import car.tp4.entity.PanierBean;

/**
 * WebServlet to pass an order
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@WebServlet("/basket")
public class PanierServlet extends HttpServlet {

	@EJB
	private BookBean bookBean;

	@EJB
	private PanierBean panierBean;

	/**
	 * Create a panier servlet
	 */
	public PanierServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("books", bookBean.getAllBooks());
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/panier.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] ids = request.getParameterValues("book");
		List<Book> books = new ArrayList<Book>();
		for (String id : ids) {
			Book b;
			b = bookBean.getBook(id);
			bookBean.setAvailabilityFalseBook(b);
			books.add(b);
		}
		Panier panier = new Panier(books);
		panierBean.addPanier(panier);
		doGet(request, response);

	}

}
