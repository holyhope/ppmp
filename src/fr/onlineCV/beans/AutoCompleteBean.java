package fr.onlineCV.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.onlineCV.dao.CompanyDAO;
import fr.onlineCV.dao.SkillDAO;
import fr.onlineCV.entities.Company;

@ManagedBean
@SessionScoped
public class AutoCompleteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Company> companies;
	private String txt5;

	@EJB
	private CompanyDAO companyDao;
	@EJB
	private SkillDAO skillDao;

	public AutoCompleteBean() {
		companies = new ArrayList<>();
	}

	public List<String> completeTextCompany(String query) {
		List<String> result = new ArrayList<>();
		companies = companyDao.selectAll();
        companies.stream().forEach(x -> result.add(x.getLabel()));
        return result;
    }
	
	public List<String> completeTextSkill(String query){
		List<String> result = new ArrayList<>();
		skillDao.findByLabelLike(query).stream().forEach(x->result.add(x.getLabel()));
		return result;
	}

	public List<Company> getCompanies() {
		return companies;
	}
	
	public String getTxt5(){
		return txt5;
	}
	
	public void setTxt5(String txt5){
		this.txt5 = txt5;
	}
	
}