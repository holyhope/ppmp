package fr.onlineCV.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.onlineCV.entities.Diploma;

@Stateless
public class DiplomaDAO {

	// Injecting manager that handle connection with DB
	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	public void create(Diploma diploma) throws DAOException {

		try {
			em.persist(diploma);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<Diploma> selectAll() throws DAOException {
		TypedQuery<Diploma> query = em.createNamedQuery("Diploma.findAll", Diploma.class);
		List<Diploma> diplomas = new ArrayList<>();
		try {
			diplomas = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return diplomas;
	}

	public Diploma findByLabel(String label) {
		TypedQuery<Diploma> query = em.createNamedQuery("Diploma.findByLabel", Diploma.class);
		query.setParameter("label", label);
		Diploma diploma;
		try {
			diploma = query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return diploma;
	}

	public List<Diploma> findByLabelLike(String label) {
		TypedQuery<Diploma> query = em.createNamedQuery("Diploma.findByLabelLike", Diploma.class);
		query.setParameter("label", label + "%");
		List<Diploma> diplomas = new ArrayList<>();
		try {
			diplomas = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return diplomas;
	}
}