package fr.onlineCV.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.User;

@ManagedBean
@ViewScoped
public class DisplayUsersBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	
	@EJB
	private UsersDAO userDao;
	
	public void onPageLoad(){
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = userDao.find(user.getEmail());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}