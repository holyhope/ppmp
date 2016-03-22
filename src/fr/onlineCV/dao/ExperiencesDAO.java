package fr.onlineCV.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.onlineCV.entities.Experiences;

@Stateless
public class ExperiencesDAO {

	// Injecting manager that handle connection with DB
	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	public void create(Experiences experiences) throws DAOException {

		try {
			em.persist(experiences);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void delete(Experiences experience) {
		try {
			Experiences ref = em.find(Experiences.class, experience.getExperiencesPK());
			em.remove(ref);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}