package fr.onlineCV.beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.User;
import fr.onlineCV.tools.PasswordConvert;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String USER = "user";
	private User user;

	@EJB
	private UsersDAO userDao;

	public LoginBean() {
		user = new User();
	}

	// Method called when button of register form clicked
	public void login() {
		User userEmail = userDao.find(user.getEmail());

		if (userEmail.getHash_password().equals(PasswordConvert.hashPassword(user.getHash_password()))) {
			FacesMessage message = new FacesMessage("Succès de la connexion !");
			FacesContext.getCurrentInstance().addMessage(null, message);
			SessionBean.createSession().setAttribute(USER, userEmail);
		} else {
			FacesMessage message = new FacesMessage("userEmail = " + userEmail.getHash_password() + " user = "
					+ PasswordConvert.hashPassword(user.getHash_password()));
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public User getUser() {
		return user;
	}
}