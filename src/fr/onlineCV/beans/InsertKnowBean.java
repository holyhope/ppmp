package fr.onlineCV.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.onlineCV.dao.SkillDAO;
import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.Skill;
import fr.onlineCV.entities.User;

@ManagedBean
@SessionScoped
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
	
	public void addSkill(){
		List<Skill> skillList = new ArrayList<>();
		skillListLabel.stream().forEach(x -> skillList.add(skillDao.findByLabel(x)));
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		//skillList.stream().forEach(x -> System.out.println("label = " + x.getLabel()));
		user.setSkillList(skillList);
		usersDao.update(user);
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