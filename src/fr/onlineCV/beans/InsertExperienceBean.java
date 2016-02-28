package fr.onlineCV.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.onlineCV.dao.CompanyDAO;
import fr.onlineCV.dao.ExperiencesDAO;
import fr.onlineCV.entities.Company;
import fr.onlineCV.entities.Experiences;
import fr.onlineCV.entities.ExperiencesPK;
import fr.onlineCV.entities.User;

@ManagedBean
@SessionScoped
public class InsertExperienceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Company company;
	private User user;
	
	private Experiences experiences;

	@EJB
	private CompanyDAO companyDao;
	
	@EJB
	private ExperiencesDAO experiencesDao;

	public InsertExperienceBean() {
		company = new Company();
		user = new User();
		experiences = new Experiences();
	}
	
	public void addExperience(){
		company = companyDao.findByLabel(company.getLabel());
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		experiences.setExperiencesPK(new ExperiencesPK(user.getId(), company.getId()));
		experiences.setCompany(company);
		experiences.setUsers(user);
		experiencesDao.create(experiences);
		FacesMessage message = new FacesMessage("experiences created");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Experiences getExperiences() {
		return experiences;
	}

	public void setExperiences(Experiences experiences) {
		this.experiences = experiences;
	}
	
	

}
