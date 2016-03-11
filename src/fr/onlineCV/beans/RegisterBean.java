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
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	@EJB
	private UsersDAO userDao;

	public RegisterBean() {
		user = new User();
	}

	// Method called when button of register form clicked
	public String register() {
		// Hash the new password
		user.setHashPassword(PasswordConvert.hashPassword(user.getHashPassword()));
		// Creating the new user
		userDao.create(user);

		// Success Message
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès",
				"Succès de l'inscription !\nConnectez-vous avec vos nouveaux identifiants");
		FacesContext fctx = FacesContext.getCurrentInstance();
		fctx.getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage("growl", message);
		return "login";
	}

	public User getUser() {
		return user;
	}
}