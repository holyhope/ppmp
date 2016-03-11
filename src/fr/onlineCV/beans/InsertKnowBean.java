package fr.onlineCV.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.onlineCV.dao.SkillDAO;
import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.Skill;
import fr.onlineCV.entities.User;

@ManagedBean
@RequestScoped
public class InsertKnowBean {

	private User user;
	private Skill skill;
	private List<String> skillListLabel;

	@EJB
	UsersDAO usersDao;
	@EJB
	SkillDAO skillDao;

	public InsertKnowBean() {
		user = new User();
		skill = new Skill();
		skillListLabel = new ArrayList<>();
	}

	public void addSkill() throws IOException {
		final List<Skill> skillList;

		// Getting user session to find the information of the current user
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		
		//Getting skill List
		skillList = user.getSkillList();
		
		//Check if all skills are new
		for (Skill skill : skillList) {
			if (skillListLabel.contains(skill.getLabel())) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Erreur lors de l'ajout des compétences", "Vous avez déjà certaines compétences");
				FacesContext.getCurrentInstance().addMessage("growl", message);
				return;
			}
		}
		//Getting the skill by label name and put it in the skill List
		skillListLabel.stream().forEach(x -> skillList.add(skillDao.findByLabel(x)));
		user.setSkillList(skillList);
		//Merging ...
		usersDao.update(user);

		//Updating all data of the current user
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx, "#{displayUsersBean}",
				DisplayUsersBean.class);
		displayUsersBean.onPageLoad();
		
		String descriptionMessage = null;
		
		if (skillListLabel.size() > 1) {
			descriptionMessage = "Vos compétences ont bien été ajoutées";
		} else {
			descriptionMessage = "Votre compétence a bien été ajoutée";
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès ajout des compétences",
				descriptionMessage);
		FacesContext.getCurrentInstance().addMessage("growl", message);
	}

	public void deleteSkill() throws IOException {
		//Getting user session to find the information of the current user
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		
		//Getting skill List
		final List<Skill> skillList = user.getSkillList();
		// Remove skill that is equals to the label to remove
		skillList.removeIf(x -> x.getLabel().equals(skill.getLabel()));
		user.setSkillList(skillList);
		//Merging ...
		usersDao.update(user);
		
		//Updating all data of the current user
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx, "#{displayUsersBean}",
				DisplayUsersBean.class);
		displayUsersBean.onPageLoad();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<String> getSkillListLabel() {
		return skillListLabel;
	}

	public void setSkillListLabel(List<String> skillListLabel) {
		this.skillListLabel = skillListLabel;
	}
}
