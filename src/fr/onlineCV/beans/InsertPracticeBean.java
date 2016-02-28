package fr.onlineCV.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
	
	public void addHobby(){
		final List<Hobby> hobbyList;
		user = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		user = usersDao.find(user.getEmail());
		hobbyList = user.getHobbyList();
		hobbyListLabel.stream().forEach(x -> hobbyList.add(hobbyDao.findByLabel(x)));
		user.setHobbyList(hobbyList);
		usersDao.update(user);
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
