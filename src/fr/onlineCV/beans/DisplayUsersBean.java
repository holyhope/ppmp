package fr.onlineCV.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.User;

@ManagedBean
@SessionScoped
public class DisplayUsersBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<User> users;
	
	@EJB
	private UsersDAO userDao;
	
	public void onPageLoad(){
		users = userDao.selectAll();
	}

	public DisplayUsersBean() {
		users = new ArrayList<>();
	}

	public List<User> getUsers() {
		return users;
	}
}