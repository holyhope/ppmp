package fr.onlineCV.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.onlineCV.dao.HobbyDAO;
import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.Hobby;
import fr.onlineCV.entities.User;

@ManagedBean
@RequestScoped
public class InsertPracticeBean {

	private User user;
	private Hobby hobby;
	private List<String> hobbyListLabel;
	
	@EJB
	UsersDAO usersDao;
	@EJB
	HobbyDAO hobbyDao;
	
	public InsertPracticeBean() {
		user = new User();
		hobby = new Hobby();
		hobbyListLabel = new ArrayList<>();
	}
	
	public void addHobby() throws IOException{
		final List<Hobby> hobbyList;
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		hobbyList = user.getHobbyList();
		for (Hobby hobby : hobbyList) {
			if (hobbyListLabel.contains(hobby.getLabel())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'ajout des hobbies",
						"Vous avez déjà certains hobbies");
				FacesContext.getCurrentInstance().addMessage("growl", message);
				return;
			}
		}
		hobbyListLabel.stream().forEach(x -> hobbyList.add(hobbyDao.findByLabel(x)));
		user.setHobbyList(hobbyList);
		usersDao.update(user);
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx , "#{displayUsersBean}", DisplayUsersBean.class);
		displayUsersBean.onPageLoad();
		String descriptionMessage = null;
		if(hobbyListLabel.size() > 1){
			descriptionMessage = "Vos hobbies ont bien été ajoutés";
		}
		else{
			descriptionMessage = "Votre hobby a bien été ajouté";
		}
		FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_INFO, "Succès : Ajout Hobby", descriptionMessage );
        FacesContext.getCurrentInstance().addMessage("growl", message );
	}
	public void deleteHobby() throws IOException{
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		final List<Hobby> hobbyList = user.getHobbyList();
		hobbyList.removeIf(x-> x.getLabel().equals(hobby.getLabel()));
		user.setHobbyList(hobbyList);
		usersDao.update(user);
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx , "#{displayUsersBean}", DisplayUsersBean.class);
		displayUsersBean.onPageLoad();
		//ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    //ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hobby getHobby() {
		return hobby;
	}

	public void setHobby(Hobby hobby) {
		this.hobby = hobby;
	}

	public List<String> getHobbyListLabel() {
		return hobbyListLabel;
	}

	public void setHobbyListLabel(List<String> hobbyListLabel) {
		this.hobbyListLabel = hobbyListLabel;
	}
}
