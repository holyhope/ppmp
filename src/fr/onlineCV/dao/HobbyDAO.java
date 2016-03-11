package fr.onlineCV.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.onlineCV.entities.Hobby;

@Stateless
public class HobbyDAO {

	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	public Hobby findByLabel(String hobbyLabel) {
		Hobby hobby = null;
		TypedQuery<Hobby> query = em.createNamedQuery("Hobby.findByLabel", Hobby.class);
		query.setParameter("label", hobbyLabel);

		try {
			hobby = (Hobby) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return hobby;
	}

	public List<Hobby> findByLabelLike(String label) {
		TypedQuery<Hobby> query = em.createNamedQuery("Hobby.findByLabelLike", Hobby.class);
		query.setParameter("label", label + "%");
		List<Hobby> hobbies = new ArrayList<>();
		try {
			hobbies = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return hobbies;
	}
}
