package fr.onlineCV.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.onlineCV.entities.Obtain;

@Stateless
public class ObtainDAO {

	// Injecting manager that handle connection with DB
	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	public void create(Obtain obtain) throws DAOException {

		try {
			em.persist(obtain);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void delete(Obtain obtain) {
		try {
			Obtain ref = em.find(Obtain.class, obtain.getObtainPK());

			em.remove(ref);

		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
