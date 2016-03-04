package fr.onlineCV.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
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
	
	private int count = 0;

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
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		Obtain obtain = new Obtain();
		obtain.setObtainPK(new ObtainPK(user.getId(), school.getId(), diploma.getId()));
		obtain.setSchool(school);
		obtain.setDiploma(diploma);
		obtain.setUsers(user);
		obtain.setYear(this.obtain.getYear());
		obtainDao.create(obtain);
		
		List<Obtain> obtainList = user.getObtainList();
		System.out.println("BEFORE");
		obtainList.forEach(x -> System.out.println("school Id : " + x.getObtainPK().getIdSchool() + " diploma id : " + x.getObtainPK().getIdDiploma()));
		obtainList.add(obtain);
		System.out.println("AJOUT DE "+ "school Id : " + obtain.getObtainPK().getIdSchool() + " diploma id : " + obtain.getObtainPK().getIdDiploma());
		System.out.println("AFTER");
		obtainList.forEach(x -> System.out.println("school Id : " + x.getObtainPK().getIdSchool() + " diploma id : " + x.getObtainPK().getIdDiploma()));
		user.setObtainList(obtainList);
		usersDao.update(user);

		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx , "#{displayUsersBean}", DisplayUsersBean.class);
		displayUsersBean.onPageLoad();
	}

	public void deleteDiploma(String schoolLabel, String diplomaLabel) throws IOException {
		System.out.println("schoolLabel : " + schoolLabel + " diplomaLabel : " + diplomaLabel);

		school = schoolDao.findByLabel(schoolLabel);
		diploma = diplomaDao.findByLabel(diplomaLabel);
		
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		//System.out.println("Obtain Diploma Id: " + obtain.getDiploma().getId());
		//System.out.println(" Diploma Id : " + diploma.getId());
		
		List<Obtain> obtainList = user.getObtainList();
		System.out.println(" Diploma Id : " + diploma.getId());
		System.out.println(" School Id : " + school.getId());
		System.out.println(" Users Id : " + user.getId());
		System.out.println("TAILLE =" +obtainList.size());
		
		obtainList.forEach(x -> System.out.println("diploma id : " + x.getDiploma().getId() + " School id : " + x.getSchool().getId() + " user id : " + x.getUsers().getId()));
		obtainList.forEach(obtain -> {
			if(obtain.getDiploma().getId().equals(diploma.getId())
					&& obtain.getSchool().getId().equals(school.getId()) && obtain.getUsers().getId().equals(user.getId())){
				this.obtain = obtainList.get(obtainList.indexOf(obtain));
				
			}
		});
		if(obtain != null){
		obtainList.remove(this.obtain);
		System.out.println("obtain pk userid : " + obtain.getObtainPK().getIdUsers() + " school id : " + obtain.getObtainPK().getIdSchool() + " diploma id : " + obtain.getObtainPK().getIdDiploma());
		obtainDao.delete(obtain);
		}
		/*obtainList.removeIf(obtain -> obtain.getDiploma().getId().equals(diploma.getId())
				&& obtain.getSchool().getId().equals(school.getId()) && obtain.getUsers().getId().equals(user.getId()));
		System.out.println("TAILLE 2 = " +obtainList.size());*/
		user.setObtainList(obtainList);
		usersDao.update(user);

		FacesContext fctx = FacesContext.getCurrentInstance();
		DisplayUsersBean displayUsersBean = fctx.getApplication().evaluateExpressionGet(fctx , "#{displayUsersBean}", DisplayUsersBean.class);
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
