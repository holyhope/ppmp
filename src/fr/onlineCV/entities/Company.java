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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maxime
 */
@Entity
@Table(name = "company")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
		@NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
		@NamedQuery(name = "Company.findByLabel", query = "SELECT c FROM Company c WHERE c.label = :label"),
		@NamedQuery(name = "Company.findByYearStart", query = "SELECT c FROM Company c WHERE c.yearStart = :yearStart"),
		@NamedQuery(name = "Company.findByYearEnd", query = "SELECT c FROM Company c WHERE c.yearEnd = :yearEnd"),
		@NamedQuery(name = "Company.findByDomain", query = "SELECT c FROM Company c WHERE c.domain = :domain"),
		@NamedQuery(name = "Company.findBySize", query = "SELECT c FROM Company c WHERE c.size = :size"),
		@NamedQuery(name = "Company.findByLocation", query = "SELECT c FROM Company c WHERE c.location = :location"),
		@NamedQuery(name = "Company.findByLabelLike", query = "SELECT c FROM Company c WHERE c.label LIKE :label") })
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "label")
	private String label;
	@Lob
	@Column(name = "description")
	private String description;
	@Basic(optional = false)
	@Column(name = "year_start")
	@Temporal(TemporalType.DATE)
	private Date yearStart;
	@Column(name = "year_end")
	@Temporal(TemporalType.DATE)
	private Date yearEnd;
	@Column(name = "domain")
	private String domain;
	@Column(name = "size")
	private Integer size;
	@Column(name = "location")
	private String location;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private List<Experiences> experiencesList;

	public Company() {
	}

	public Company(Integer id) {
		this.id = id;
	}

	public Company(Integer id, Date yearStart) {
		this.id = id;
		this.yearStart = yearStart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getYearStart() {
		return yearStart;
	}

	public void setYearStart(Date yearStart) {
		this.yearStart = yearStart;
	}

	public Date getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Date yearEnd) {
		this.yearEnd = yearEnd;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
		if (!(object instanceof Company)) {
			return false;
		}
		Company other = (Company) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}
}
