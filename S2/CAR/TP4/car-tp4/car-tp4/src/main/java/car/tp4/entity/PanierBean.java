package car.tp4.entity;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Stateless PanierBean
 * 
 * @authors texierl, deffontaine, petitpre
 *
 */
@Stateless
@Local
public class PanierBean {

	@PersistenceContext(unitName = "panier-pu")
	private EntityManager entityManager;

	/**
	 * Add a basket
	 * 
	 * @param panier
	 *            the basket
	 */
	public void addPanier(Panier panier) {
		entityManager.persist(panier);
	}

	/**
	 * Get all baskets
	 * 
	 * @return Baskets
	 */
	public List<Panier> getAllPaniers() {
		Query query = entityManager.createQuery("SELECT m from Panier as m");
		return query.getResultList();
	}

}