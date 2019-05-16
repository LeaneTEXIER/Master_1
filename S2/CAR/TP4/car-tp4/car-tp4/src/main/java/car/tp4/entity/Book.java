package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity Book
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String author;
	private String title;
	private int year;
	private boolean available;

	/**
	 * Create a book
	 */
	public Book() {
	}

	/**
	 * Create a book
	 * 
	 * @param title
	 *            the title of the book
	 * @param author
	 *            the author of the book
	 * @param year
	 *            the year of the book
	 */
	public Book(String title, String author, int year) {
		this.author = author;
		this.title = title;
		this.year = year;
		this.available = true;
	}

	/**
	 * Return the id of the book
	 * 
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Return true if the book is available, false otherwise
	 * 
	 * @return availability of the book
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Set the availability of the book
	 * 
	 * @param available
	 *            availability of the book
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * Return the author of the book
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Set the author of the book
	 * 
	 * @param author
	 *            the author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Get the title of the book
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the book
	 * 
	 * @param title
	 *            the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the year of the book
	 * 
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year of the book
	 * 
	 * @param year
	 *            the year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Book book = (Book) o;

		if (id != book.id)
			return false;
		if (!author.equals(book.author))
			return false;
		return title.equals(book.title);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + author.hashCode();
		result = 31 * result + title.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Author : " + author + ", Title : " + title + ", Year : " + year;
	}
}
