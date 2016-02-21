package fr.onlineCV.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.onlineCV.entities.User;

@Stateless
public class UsersDAO {

	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM User u WHERE u.email=:email";
	private static final String JPQL_SELECT_ALL = "SELECT u FROM User u";
	private static final String PARAM_EMAIL = "email";

	// Injecting manager that handle connection with DB
	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	// Saving new user
	public void create(User user) throws DAOException {

		try {
			em.persist(user);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	// Find user by email
	public User find(String email) throws DAOException {
		User user = null;
		Query requete = em.createQuery(JPQL_SELECT_PAR_EMAIL);
		requete.setParameter(PARAM_EMAIL, email);

		try {
			user = (User) requete.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return user;
	}

	public List<User> findByFirstName(String first_name) {
		List<User> users = new ArrayList<>();
		TypedQuery<User> query = em.createNamedQuery("User.findByFirstName", User.class);

		query.setParameter("firstName", first_name);

		try {
			users = (List<User>) query.getResultList();
			System.out.println("Size list Users = " + users.size());
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return users;
	}

	public List<User> selectAll() throws DAOException {
		TypedQuery<User> requete = em.createQuery(JPQL_SELECT_ALL, User.class);
		List<User> user = new ArrayList<>();
		try {
			user = requete.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return user;
	}
}