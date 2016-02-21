package fr.onlineCV.beans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.onlineCV.dao.HobbyDAO;
import fr.onlineCV.entities.Hobby;
import fr.onlineCV.entities.User;

@ManagedBean
@RequestScoped
public class HobbyBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Hobby hobby;

	public HobbyBean() {
		hobby = new Hobby();
	}

	@EJB
	private HobbyDAO hobbyDao;

	public void add() {
		//hobbyDao.add(hobby, (User) SessionBean.getSession().getAttribute(LoginBean.USER));
	}
}
