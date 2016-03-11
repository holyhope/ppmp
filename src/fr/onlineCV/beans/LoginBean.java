package fr.onlineCV.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.User;
import fr.onlineCV.tools.PasswordConvert;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String USER = "user";
	private User user;
	private boolean isConnected = false;

	@EJB
	private UsersDAO userDao;

	public LoginBean() {
		user = new User();
	}

	// Method called when button login is clicked
	public String login() {
		User userEmail = userDao.find(user.getEmail());

		// Check if both hash password are equals
		if (userEmail.getHashPassword().equals(PasswordConvert.hashPassword(user.getHashPassword()))) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connexion",
					"Vous êtes maintentant connecté(e)");
			FacesContext fctx = FacesContext.getCurrentInstance();
			fctx.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage("growl", message);
			// Creating session for user connected
			SessionBean.createSession().setAttribute(USER, userEmail);
			user = userEmail;
			isConnected = true;
			return "myProfile?faces-redirect=true";
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Wrong user or Wrong password !");
			FacesContext fctx = FacesContext.getCurrentInstance();
			fctx.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage("growl", message);
			return "login";
		}
	}

	// Logout event, invalidate session
	public String logout() {
		HttpSession session = SessionBean.getSession();
		session.invalidate();
		return "login?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public boolean getIsConnected() {
		return isConnected;
	}
}