package fr.onlineCV.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.onlineCV.dao.CompanyDAO;
import fr.onlineCV.dao.DiplomaDAO;
import fr.onlineCV.dao.HobbyDAO;
import fr.onlineCV.dao.SchoolDAO;
import fr.onlineCV.dao.SkillDAO;
import fr.onlineCV.entities.Company;

@ManagedBean
@RequestScoped
public class AutoCompleteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Company> companies;

	@EJB
	private CompanyDAO companyDao;
	@EJB
	private SkillDAO skillDao;
	@EJB
	private HobbyDAO hobbyDao;
	@EJB
	private SchoolDAO schoolDao;
	@EJB
	private DiplomaDAO diplomaDao;

	public AutoCompleteBean() {
		companies = new ArrayList<>();
	}

	public List<String> completeTextCompany(String query) {
		List<String> result = new ArrayList<>();
		companyDao.findByLabelLike(query).stream().forEach(x -> result.add(x.getLabel()));
        return result;
    }
	
	public List<String> completeTextSkill(String query){
		List<String> result = new ArrayList<>();
		skillDao.findByLabelLike(query).stream().forEach(x->result.add(x.getLabel()));
		return result;
	}
	
	public List<String> completeTextHobby(String query){
		List<String> result = new ArrayList<>();
		hobbyDao.findByLabelLike(query).stream().forEach(x->result.add(x.getLabel()));
		return result;
	}
	
	public List<String> completeTextSchool(String query){
		List<String> result = new ArrayList<>();
		schoolDao.findByLabelLike(query).stream().forEach(x->result.add(x.getLabel()));
		return result;
	}
	public List<String> completeTextDiploma(String query){
		List<String> result = new ArrayList<>();
		diplomaDao.findByLabelLike(query).stream().forEach(x->result.add(x.getLabel()));
		return result;
	}

	public List<Company> getCompanies() {
		return companies;
	}
	
	
}