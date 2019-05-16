package car.tp4.entity;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Stateless BookBean
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@Stateless
@Local
public class BookBean {

	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	/**
	 * Set the entity manager
	 * 
	 * @param entity
	 *            the entity manager
	 */
	public void setEntityManager(EntityManager entity) {
		this.entityManager = entity;
	}

	/**
	 * Add a book
	 * 
	 * @param book
	 *            the book to add
	 */
	public void addBook(Book book) {
		entityManager.persist(book);
	}

	/**
	 * Set the availability of the book to false
	 * 
	 * @param book
	 *            the book
	 * @return the answer of the request
	 */
	public int setAvailabilityFalseBook(Book book) {
		Query query = entityManager.createQuery("Update Book b SET b.available=false where b.id = " + book.getId());
		return query.executeUpdate();
	}

	/**
	 * Get all books
	 * 
	 * @return the books
	 */
	public List<Book> getAllBooks() {
		Query query = entityManager.createQuery("SELECT m from Book as m");
		return query.getResultList();
	}

	/**
	 * Get all titles
	 * 
	 * @return the titles
	 */
	public List<String> getAllTitles() {
		Query query = entityManager.createQuery("SELECT m.title from Book as m");
		return query.getResultList();
	}

	/**
	 * Get all books sorted by year asc
	 * 
	 * @return the books
	 */
	public List<Book> getAllBooksTriYear() {
		Query query = entityManager.createQuery("SELECT m from Book as m order by m.year asc");
		return query.getResultList();
	}

	/**
	 * Get all books of the author given in parameter or of all authors if the
	 * parameters is null And the title or a part of the title corresponds to
	 * the parameter given
	 * 
	 * @param author
	 *            the author
	 * @param title
	 *            the title
	 * @return the books
	 */
	public List<Book> getAllBooksAuthorAndTitle(String author, String title) {
		Query query = entityManager.createQuery("SELECT m from Book as m where upper(m.author)=\""
				+ author.toUpperCase() + "\" and upper(m.title) like \"%" + title.toUpperCase() + "%\"");
		return query.getResultList();
	}

	/**
	 * Get all books where the title or a part of the title corresponds to the
	 * parameter given
	 * 
	 * @param title
	 *            the title
	 * @return the books
	 */
	public List<Book> getAllBooksTitle(String title) {
		Query query = entityManager
				.createQuery("SELECT m from Book as m where upper(m.title) like \"%" + title.toUpperCase() + "%\"");
		return query.getResultList();
	}

	/**
	 * Get the book of id given in parameter
	 * 
	 * @param id
	 *            the id of the book
	 * @return the book
	 */
	public Book getBook(String id) {
		Book b;
		Query query = entityManager.createQuery("SELECT b from Book b where b.id = " + id);
		b = (Book) query.getSingleResult();
		return b;
	}

}