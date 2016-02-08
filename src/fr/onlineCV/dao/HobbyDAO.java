package fr.onlineCV.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.onlineCV.entities.Hobby;
import fr.onlineCV.entities.User;

@Stateless
public class HobbyDAO {

	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	public void add(Hobby hobby, User user) throws DAOException {
		try {
			user.getHobbies().add(hobby);
			em.merge(user);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
