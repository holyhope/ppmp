package fr.onlineCV.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.onlineCV.entities.Company;

@Stateless
public class CompanyDAO {

	// Injecting manager that handle connection with DB
	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	public void create(Company company) throws DAOException {

		try {
			em.persist(company);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<Company> selectAll() throws DAOException {
		TypedQuery<Company> query = em.createNamedQuery("Company.findAll", Company.class);
		List<Company> companies = new ArrayList<>();
		try {
			companies = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return companies;
	}

	public Company findByLabel(String label) {
		TypedQuery<Company> query = em.createNamedQuery("Company.findByLabel", Company.class);
		query.setParameter("label", label);
		Company company;
		try {
			company = query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return company;
	}

	public List<Company> findByLabelLike(String label) {
		TypedQuery<Company> query = em.createNamedQuery("Company.findByLabelLike", Company.class);
		query.setParameter("label", label + "%");
		List<Company> companies = new ArrayList<>();
		try {
			companies = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return companies;
	}

}
