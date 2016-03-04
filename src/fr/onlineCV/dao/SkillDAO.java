package fr.onlineCV.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.onlineCV.entities.Skill;

@Stateless
public class SkillDAO {

	// Injecting manager that handle connection with DB
	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	public List<Skill> selectAll() throws DAOException {
		TypedQuery<Skill> query = em.createNamedQuery("Skill.findAll", Skill.class);
		List<Skill> skills = new ArrayList<>();
		try {
			skills = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return skills;
	}
	

	public Skill findByLabel(String label) {
		TypedQuery<Skill> query = em.createNamedQuery("Skill.findByLabel", Skill.class);
		query.setParameter("label", label);
		Skill skill = new Skill();
		try {
			skill = query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return skill;

	}

	public List<Skill> findByLabelLike(String label) {
		TypedQuery<Skill> query = em.createNamedQuery("Skill.findByLabelLike", Skill.class);
		query.setParameter("label", label + "%");
		List<Skill> skills = new ArrayList<>();
		try {
			skills = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return skills;
	}
}
