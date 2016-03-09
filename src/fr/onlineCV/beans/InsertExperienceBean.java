package fr.onlineCV.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.onlineCV.dao.CompanyDAO;
import fr.onlineCV.dao.ExperiencesDAO;
import fr.onlineCV.dao.UsersDAO;
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
	
	@EJB
	private UsersDAO usersDao;

	public InsertExperienceBean() {
		company = new Company();
		user = new User();
		experiences = new Experiences();
	}
	
	public void addExperience() throws IOException{
		company = companyDao.findByLabel(company.getLabel());
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		experiences.setExperiencesPK(new ExperiencesPK(user.getId(), company.getId()));
		experiences.setCompany(company);
		experiences.setUsers(user);
		experiencesDao.create(experiences);
		List<Experiences> experiencesList = user.getExperiencesList();
		experiencesList.add(experiences);
		user.setExperiencesList(experiencesList);
		usersDao.update(user);
		
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx , "#{displayUsersBean}", DisplayUsersBean.class);
		displayUsersBean.onPageLoad();
		
	}
	
	public void deleteExperience() throws IOException{
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		List<Experiences> experiencesList = user.getExperiencesList();
		experiencesList.removeIf(x -> x.getLabel().equals(experiences.getLabel()));
		user.setExperiencesList(experiencesList);
		usersDao.update(user);
		
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx , "#{displayUsersBean}", DisplayUsersBean.class);
		displayUsersBean.onPageLoad();
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
