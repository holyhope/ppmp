/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.onlineCV.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Maxime
 */
@Embeddable
public class ObtainPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "id_users")
	private int idUsers;
	@Basic(optional = false)
	@Column(name = "id_school")
	private int idSchool;
	@Basic(optional = false)
	@Column(name = "id_diploma")
	private int idDiploma;

	public ObtainPK() {
	}

	public ObtainPK(int idUsers, int idSchool, int idDiploma) {
		this.idUsers = idUsers;
		this.idSchool = idSchool;
		this.idDiploma = idDiploma;
	}

	public int getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public int getIdSchool() {
		return idSchool;
	}

	public void setIdSchool(int idSchool) {
		this.idSchool = idSchool;
	}

	public int getIdDiploma() {
		return idDiploma;
	}

	public void setIdDiploma(int idDiploma) {
		this.idDiploma = idDiploma;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) idUsers;
		hash += (int) idSchool;
		hash += (int) idDiploma;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof ObtainPK)) {
			return false;
		}
		ObtainPK other = (ObtainPK) object;
		if (this.idUsers != other.idUsers) {
			return false;
		}
		if (this.idSchool != other.idSchool) {
			return false;
		}
		if (this.idDiploma != other.idDiploma) {
			return false;
		}
		return true;
	}
}
