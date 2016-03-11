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

import fr.onlineCV.dao.DiplomaDAO;
import fr.onlineCV.dao.ObtainDAO;
import fr.onlineCV.dao.SchoolDAO;
import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.Diploma;
import fr.onlineCV.entities.Obtain;
import fr.onlineCV.entities.ObtainPK;
import fr.onlineCV.entities.School;
import fr.onlineCV.entities.User;

@ManagedBean
@SessionScoped
public class InsertObtainBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private School school;
	private User user;
	private Obtain obtain;
	private Diploma diploma;

	@EJB
	private SchoolDAO schoolDao;
	@EJB
	private ObtainDAO obtainDao;
	@EJB
	private UsersDAO usersDao;
	@EJB
	private DiplomaDAO diplomaDao;

	public InsertObtainBean() {
		school = new School();
		user = new User();
		obtain = new Obtain();
		diploma = new Diploma();
	}

	public void addDiploma() throws IOException {

		school = schoolDao.findByLabel(school.getLabel());
		diploma = diplomaDao.findByLabel(diploma.getLabel());

		// Getting user session to find the information of the current user
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());

		// Creating new Diploma obtain
		Obtain obtain = new Obtain();
		obtain.setObtainPK(new ObtainPK(user.getId(), school.getId(), diploma.getId()));
		obtain.setSchool(school);
		obtain.setDiploma(diploma);
		obtain.setUsers(user);

		// Add 1 day to date
		Calendar c = Calendar.getInstance();
		c.setTime(this.obtain.getYear());
		c.add(Calendar.DATE, 1);
		obtain.setYear(c.getTime());
		obtainDao.create(obtain);

		List<Obtain> obtainList = user.getObtainList();
		obtainList.add(obtain);
		user.setObtainList(obtainList);
		// Merging...
		usersDao.update(user);

		// Updating all data of the current user
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx, "#{displayUsersBean}",
				DisplayUsersBean.class);
		displayUsersBean.onPageLoad();

		// Success Message
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès ajout du diplome",
				"Votre diplome a bien été ajouté");
		FacesContext.getCurrentInstance().addMessage("growl", message);
	}

	public void deleteDiploma(String schoolLabel, String diplomaLabel) throws IOException {

		school = schoolDao.findByLabel(schoolLabel);
		diploma = diplomaDao.findByLabel(diplomaLabel);

		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());

		List<Obtain> obtainList = user.getObtainList();

		// Getting diploma obtain to remove
		obtainList.forEach(obtain -> {
			if (obtain.getDiploma().getId().equals(diploma.getId()) && obtain.getSchool().getId().equals(school.getId())
					&& obtain.getUsers().getId().equals(user.getId())) {
				this.obtain = obtainList.get(obtainList.indexOf(obtain));
			}
		});

		// Remove diploma
		if (obtain != null) {
			obtainList.remove(this.obtain);
			obtainDao.delete(obtain);
		}
		user.setObtainList(obtainList);
		// Merging...
		usersDao.update(user);

		// Update info
		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx, "#{displayUsersBean}",
				DisplayUsersBean.class);
		displayUsersBean.onPageLoad();
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Obtain getObtain() {
		return obtain;
	}

	public void setObtain(Obtain obtain) {
		this.obtain = obtain;
	}

	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

}
