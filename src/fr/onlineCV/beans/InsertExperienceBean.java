package fr.onlineCV.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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

	public void addExperience() throws IOException {
		// Check if yearStart before yearEnd
		if (experiences.getYearStart().before(experiences.getYearEnd())) {
			company = companyDao.findByLabel(company.getLabel());

			// Getting user session to find the information of the current user
			user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
			user = usersDao.find(user.getEmail());

			// Setting informations for the new experience
			experiences.setExperiencesPK(new ExperiencesPK(user.getId(), company.getId()));
			experiences.setCompany(company);
			experiences.setUsers(user);

			// Add 1 day to yearStart and yearEnd
			Calendar c = Calendar.getInstance();
			c.setTime(this.experiences.getYearStart());
			c.add(Calendar.DATE, 1);
			experiences.setYearStart(c.getTime());
			c.setTime(this.experiences.getYearEnd());
			c.add(Calendar.DATE, 1);
			experiences.setYearEnd(c.getTime());

			experiencesDao.create(experiences);

			// Getting experience List
			List<Experiences> experiencesList = user.getExperiencesList();

			experiencesList.add(experiences);

			// Setting the new experience List to the current user
			user.setExperiencesList(experiencesList);

			// Merging ...
			usersDao.update(user);

			// Updating all data with method onPageLoad()
			FacesContext fctx = FacesContext.getCurrentInstance();
			DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx, "#{displayUsersBean}",
					DisplayUsersBean.class);
			displayUsersBean.onPageLoad();

			// Message if success
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès ajout de l'experience",
					"Votre expérience à bien été ajouté");
			FacesContext.getCurrentInstance().addMessage("growl", message);

			// Message if error
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"La date de début est après la date de fin");
			FacesContext.getCurrentInstance().addMessage("growl", message);
		}

	}

	public void deleteExperience(int idCompany) throws IOException {
		// Find informations of the user
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		company = companyDao.findById(idCompany);

		// Getting experience List
		List<Experiences> experiencesList = user.getExperiencesList();

		// Getting experience to remove
		experiencesList.forEach(experience -> {
			if (experience.getCompany().getId().equals(company.getId())
					&& experience.getUsers().getId().equals(user.getId())) {
				this.experiences = experiencesList.get(experiencesList.indexOf(experience));
			}
		});

		// Remove experience
		if (experiences != null) {
			experiencesList.remove(this.experiences);
			experiencesDao.delete(experiences);
		}
		// Remove experience that is equals to the label to remove
		user.setExperiencesList(experiencesList);
		// Merging ...
		usersDao.update(user);

		// Updating all informations of the current user
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx, "#{displayUsersBean}",
				DisplayUsersBean.class);
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
