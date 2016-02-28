package fr.onlineCV.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.User;

@ManagedBean
@SessionScoped
public class SearchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private List<User> users;

	@EJB
	private UsersDAO userDao;

	public SearchBean() {
		user = new User();
		users = new ArrayList<>();
	}

	public String searchByFirstName() {

		if (SessionBean.isConnected()) {
			
			users = userDao.findByFirstName(user.getFirstName());

			return "search";
		} else {
			return "login";
		}
	}
	
	public String showUser(){
		user = userDao.findById(user.getId());
		return "Profile";
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser() {
		return user;
	}
}