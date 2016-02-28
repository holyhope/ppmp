/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.onlineCV.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maxime
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Users.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
		@NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName"),
		@NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName"),
		@NamedQuery(name = "User.findByBirthdate", query = "SELECT u FROM User u WHERE u.birthdate = :birthdate"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery(name = "User.findByHashPassword", query = "SELECT u FROM User u WHERE u.hashPassword = :hashPassword"),
		@NamedQuery(name = "User.findByPhotoPath", query = "SELECT u FROM User u WHERE u.photoPath = :photoPath"),
		@NamedQuery(name = "User.findByFirstAndLastName", query = "SELECT u FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName"),})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "last_name")
	@NotNull(message = "{lastName.notNull}")
	private String lastName;
	
	@Column(name = "first_name")
	@NotNull(message = "{firstName.notNull}")
	private String firstName;
	
	@Column(name = "birthdate")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "{birthday.notNull}")
	private Date birthdate;
	
	@Basic(optional = false)
	@Column(name = "email")
	@Pattern(regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "{email.pattern}")
	@NotNull(message = "{email.notNull}")
	private String email;
	
	@Basic(optional = false)
	@Column(name = "hash_password")
	@NotNull(message = "{password.notNull}")
	@Size(min = 5, message = "{password.minSize}")
	//@Convert(converter = PasswordConvert.class)
		// @Converter( name = "passwordConverter", converterClass =
		// PasswordConvert.class )
	private String hashPassword;
	
	@Column(name = "photo_path")
	private String photoPath;
	
	@JoinTable(name = "practice", joinColumns = {
			@JoinColumn(name = "id_users", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_hobby", referencedColumnName = "id") })
	@ManyToMany
	private List<Hobby> hobbyList;
	
	@JoinTable(name = "know", joinColumns = {
			@JoinColumn(name = "id_users", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_skill", referencedColumnName = "id") })
	@ManyToMany
	private List<Skill> skillList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private List<Obtain> obtainList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private List<Study> studyList;
	
	@OneToMany(mappedBy = "idUsers")
	private List<Phone> phoneList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private List<Experiences> experiencesList;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String email, String hashPassword) {
		this.id = id;
		this.email = email;
		this.hashPassword = hashPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@XmlTransient
	public List<Hobby> getHobbyList() {
		return hobbyList;
	}

	public void setHobbyList(List<Hobby> hobbyList) {
		this.hobbyList = hobbyList;
	}

	@XmlTransient
	public List<Skill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<Skill> skillList) {
		this.skillList = skillList;
	}

	@XmlTransient
	public List<Obtain> getObtainList() {
		return obtainList;
	}

	public void setObtainList(List<Obtain> obtainList) {
		this.obtainList = obtainList;
	}

	@XmlTransient
	public List<Study> getStudyList() {
		return studyList;
	}

	public void setStudyList(List<Study> studyList) {
		this.studyList = studyList;
	}

	@XmlTransient
	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}

	@XmlTransient
	public List<Experiences> getExperiencesList() {
		return experiencesList;
	}

	public void setExperiencesList(List<Experiences> experiencesList) {
		this.experiencesList = experiencesList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.mycompany.mavenproject1.Users[ id=" + id + " ]";
	}

}
