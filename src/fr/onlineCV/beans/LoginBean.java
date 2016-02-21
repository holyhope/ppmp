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

	// Method called when button of register form clicked
	public String login() {
		User userEmail = userDao.find(user.getEmail());

		if (userEmail.getHashPassword().equals(PasswordConvert.hashPassword(user.getHashPassword()))) {
			FacesMessage message = new FacesMessage("Succès de la connexion !");
			FacesContext.getCurrentInstance().addMessage(null, message);
			SessionBean.createSession().setAttribute(USER, userEmail);
			user = userEmail;
			isConnected = true;
			return "Profile?faces-redirect=true";
		} else {
			FacesMessage message = new FacesMessage("Wrong password !");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return "login";
		}
	}
	
	//logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login?faces-redirect=true";
    }
    
	public User getUser() {
		return user;
	}
	
	public boolean getIsConnected(){
		return isConnected;
	}
}