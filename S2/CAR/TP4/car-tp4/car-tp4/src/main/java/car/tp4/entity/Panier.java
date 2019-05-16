package car.tp4.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity Panier
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@Entity
public class Panier {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private List<Book> books;

	/**
	 * Create a basket
	 */
	public Panier() {
	}

	/**
	 * Create a basket with books
	 * 
	 * @param books
	 *            the books
	 */
	public Panier(List<Book> books) {
		this.books = books;
	}

	/**
	 * Get the books of the basket
	 * 
	 * @return the books
	 */
	public List<Book> getBooks() {
		return this.books;
	}

	/**
	 * Set books of the basket
	 * 
	 * @param books
	 *            the books
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	/**
	 * Get the id of the basket
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set the id of the books
	 * 
	 * @param id
	 *            the id
	 */
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Panier panier = (Panier) o;

		if (id != panier.getId())
			return false;
		return books.equals(panier.getBooks());
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + books.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Books in the basket : " + books;
	}

}
