package car.tp4.entity;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * StateLess InitAndAuthor
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@Stateless
@Local
public class InitAndAuthor {

	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	/**
	 * Add 2 books
	 */
	public void init() {
		entityManager.persist(new Book("La petite fadette", "George Sand", 1901));
		entityManager.persist(new Book("Le hasard n'existe pas", "Karl Otto Schmidt", 1921));
	}

	/**
	 * Get all authors
	 * 
	 * @return List of authors
	 */
	public List<Object[]> getAllAuthors() {
		javax.persistence.Query query = entityManager.createQuery("SELECT distinct b.author from Book b");
		List<Object[]> res;
		try {
			res = query.getResultList();
		} catch (NoResultException e) {
			res = null;
		}

		return res;
	}

}