/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.onlineCV.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maxime
 */
@Entity
@Table(name = "diploma")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Diploma.findAll", query = "SELECT d FROM Diploma d"),
		@NamedQuery(name = "Diploma.findById", query = "SELECT d FROM Diploma d WHERE d.id = :id"),
		@NamedQuery(name = "Diploma.findByLabel", query = "SELECT d FROM Diploma d WHERE d.label = :label"),
		@NamedQuery(name = "Diploma.findBySpecialization", query = "SELECT d FROM Diploma d WHERE d.specialization = :specialization"),
		@NamedQuery(name = "Diploma.findByLabelLike", query = "SELECT d FROM Diploma d WHERE d.label LIKE :label") })
public class Diploma implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "label")
	private String label;
	@Column(name = "specialization")
	private String specialization;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "diploma")
	private List<Obtain> obtainList;

	public Diploma() {
	}

	public Diploma(Integer id) {
		this.id = id;
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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	@XmlTransient
	public List<Obtain> getObtainList() {
		return obtainList;
	}

	public void setObtainList(List<Obtain> obtainList) {
		this.obtainList = obtainList;
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
		if (!(object instanceof Diploma)) {
			return false;
		}
		Diploma other = (Diploma) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}
}
