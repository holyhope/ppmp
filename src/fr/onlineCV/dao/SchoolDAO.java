package fr.onlineCV.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.onlineCV.entities.School;

@Stateless
public class SchoolDAO {

	// Injecting manager that handle connection with DB
		@PersistenceContext(unitName = "bdd_OnlineCV_PU")
		private EntityManager em;
		
		
		// Saving new user
			public void create(School school) throws DAOException {

				try {
					em.persist(school);
				} catch (Exception e) {
					throw new DAOException(e);
				}
			}
		
		public List<School> selectAll() throws DAOException {
			TypedQuery<School> query = em.createNamedQuery("School.findAll", School.class);
			List<School> schools = new ArrayList<>();
			try {
				schools = query.getResultList();
			} catch (Exception e) {
				throw new DAOException(e);
			}
			return schools;
		}
		
		public School findByLabel(String label){
			TypedQuery<School> query = em.createNamedQuery("School.findByLabel", School.class);
			query.setParameter("label", label);
			School school;
			try {
				school = query.getSingleResult();
			} catch (Exception e) {
				throw new DAOException(e);
			}
			return school;
		}
		
		public List<School> findByLabelLike(String label) {
			TypedQuery<School> query = em.createNamedQuery("School.findByLabelLike", School.class);
			query.setParameter("label", label + "%");
			List<School> schools = new ArrayList<>();
			try {
				schools = query.getResultList();
			} catch (Exception e) {
				throw new DAOException(e);
			}
			return schools;
		}
}
