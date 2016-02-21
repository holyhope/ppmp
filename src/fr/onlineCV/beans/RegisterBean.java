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
    public void register() {
    	user.setHashPassword(PasswordConvert.hashPassword(user.getHashPassword()));
        userDao.create( user );
        FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
    }

    public User getUser() {
        return user;
    }
}