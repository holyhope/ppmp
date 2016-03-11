package fr.onlineCV.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.onlineCV.dao.HobbyDAO;
import fr.onlineCV.dao.SkillDAO;
import fr.onlineCV.dao.UsersDAO;
import fr.onlineCV.entities.Hobby;
import fr.onlineCV.entities.Skill;
import fr.onlineCV.entities.User;

@ManagedBean
@SessionScoped
public class SearchBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private User user;
	private String searchRequest;
	private List<User> users;
	private HashMap<Skill, List<User>> searchSkillResult;
	private HashMap<Hobby, List<User>> searchHobbyResult;
	private boolean isMyProfile = false;

	@EJB
	private UsersDAO userDao;
	@EJB
	private SkillDAO skillDao;
	@EJB
	private HobbyDAO hobbyDao;

	public SearchBean() {
		user = new User();
		users = new ArrayList<>();
		searchSkillResult = new HashMap<>();
		searchHobbyResult = new HashMap<>();
	}

	public List<User> searchByFirstName() {

		return userDao.findByFirstNameLike(searchRequest);

	}

	public String search() {
		searchBySkill();
		searchByHobby();
		users = searchByFirstName();
		users.addAll(searchByLastName());
		return "search";
	}

	public void searchBySkill() {
		searchSkillResult.clear();
		List<Skill> skillList = new ArrayList<>(); 
		skillList = skillDao.findByLabelLike(searchRequest);
		skillList.forEach(x -> searchSkillResult.put(x, userDao.findBySkill(x.getLabel())));
		searchSkillResult.values().removeIf(x -> x == null || x.size() == 0);
		
	}
	
	public void searchByHobby() {
		searchHobbyResult.clear();
		List<Hobby> hobbyList = new ArrayList<>(); 
		hobbyList = hobbyDao.findByLabelLike(searchRequest);
		hobbyList.forEach(x -> searchHobbyResult.put(x, userDao.findByHobby(x.getLabel())));
		searchHobbyResult.values().removeIf(x -> x == null || x.size() == 0);
	}

	public List<User> searchByLastName() {
		return userDao.findByLastNameLike(searchRequest);

	}
	
	public List<User> searchBySkillLabel(){
		return userDao.findBySkill(searchRequest);
	}

	public String showUser() {
		user = userDao.findById(user.getId());
		User userSession = (User) SessionBean.getSession().getAttribute(LoginBean.USER);
		if (userSession == null) {
			isMyProfile = false;
		} else {
			userSession = userDao.find(userSession.getEmail());
			isMyProfile = userSession.getId() == user.getId();
		}
		return "Profile?faces-redirect=true";
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser() {
		return user;
	}

	public void setMyProfile(boolean isMyProfile) {
		this.isMyProfile = isMyProfile;
	}

	public boolean getIsMyProfile() {
		return isMyProfile;
	}

	public String getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(String searchRequest) {
		this.searchRequest = searchRequest;
	}

	public HashMap<Skill, List<User>> getSearchSkillResult() {
		return searchSkillResult;
	}

	public void setSearchSkillResult(HashMap<Skill, List<User>> searchSkillResult) {
		this.searchSkillResult = searchSkillResult;
	}

	public HashMap<Hobby, List<User>> getSearchHobbyResult() {
		return searchHobbyResult;
	}

	public void setSearchHobbyResult(HashMap<Hobby, List<User>> searchHobbyResult) {
		this.searchHobbyResult = searchHobbyResult;
	}
}