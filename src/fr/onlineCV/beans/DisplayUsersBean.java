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
	private boolean showDeleteButtonSkill = false;
	private boolean showDeleteButtonHobby = false;
	private boolean showDeleteButtonExperiences = false;
	private boolean showDeleteButtonDiploma = false;
	
	@EJB
	private UsersDAO userDao;
	
	public void onPageLoad(){
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		if(user != null){
		user = userDao.find(user.getEmail());
		}
	}
	
	public void modifySkill(){
		if(showDeleteButtonSkill==true){
			showDeleteButtonSkill=false ;
		}
		else{
			showDeleteButtonSkill = true;
		}
		
	}
	public void modifyHobby(){
		if(showDeleteButtonHobby==true){
			showDeleteButtonHobby=false ;
		}
		else{
			showDeleteButtonHobby = true;
		}
		
	}
	public void modifyExperiences(){
		if(showDeleteButtonExperiences==true){
			showDeleteButtonExperiences=false ;
		}
		else{
			showDeleteButtonExperiences = true;
		}
		
	}
	public void modifyDiploma(){
		if(showDeleteButtonDiploma==true){
			showDeleteButtonDiploma=false ;
		}
		else{
			showDeleteButtonDiploma = true;
		}
		
	}
	
	
	public void saveChanges(){
		userDao.update(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isShowDeleteButton() {
		return showDeleteButtonSkill;
	}

	public void setShowDeleteButton(boolean showDeleteButton) {
		this.showDeleteButtonSkill = showDeleteButton;
	}

	public boolean isShowDeleteButtonHobby() {
		return showDeleteButtonHobby;
	}

	public void setShowDeleteButtonHobby(boolean showDeleteButtonHobby) {
		this.showDeleteButtonHobby = showDeleteButtonHobby;
	}

	public boolean isShowDeleteButtonExperiences() {
		return showDeleteButtonExperiences;
	}

	public void setShowDeleteButtonExperiences(boolean showDeleteButtonExperiences) {
		this.showDeleteButtonExperiences = showDeleteButtonExperiences;
	}

	public boolean isShowDeleteButtonDiploma() {
		return showDeleteButtonDiploma;
	}

	public void setShowDeleteButtonDiploma(boolean showDeleteButtonDiploma) {
		this.showDeleteButtonDiploma = showDeleteButtonDiploma;
	}

	
}