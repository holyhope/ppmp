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
public class ExperiencesPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "id_users")
    private int idUsers;
    @Basic(optional = false)
    @Column(name = "id_company")
    private int idCompany;

    public ExperiencesPK() {
    }

    public ExperiencesPK(int idUsers, int idCompany) {
        this.idUsers = idUsers;
        this.idCompany = idCompany;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsers;
        hash += (int) idCompany;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperiencesPK)) {
            return false;
        }
        ExperiencesPK other = (ExperiencesPK) object;
        if (this.idUsers != other.idUsers) {
            return false;
        }
        if (this.idCompany != other.idCompany) {
            return false;
        }
        return true;
    } 
}
