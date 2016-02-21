package fr.onlineCV.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mysql.jdbc.NotImplemented;

import fr.onlineCV.entities.Hobby;
import fr.onlineCV.entities.User;

@Stateless
public class HobbyDAO {

	@PersistenceContext(unitName = "bdd_OnlineCV_PU")
	private EntityManager em;

	public void add(Hobby hobby, User user) throws NotImplemented {
		
			throw new NotImplemented();
			//em.merge(user);
		
	}
}
