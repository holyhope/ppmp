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
public class StudyPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_users")
    private int idUsers;
    @Basic(optional = false)
    @Column(name = "id_school")
    private int idSchool;

    public StudyPK() {
    }

    public StudyPK(int idUsers, int idSchool) {
        this.idUsers = idUsers;
        this.idSchool = idSchool;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsers;
        hash += (int) idSchool;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudyPK)) {
            return false;
        }
        StudyPK other = (StudyPK) object;
        if (this.idUsers != other.idUsers) {
            return false;
        }
        if (this.idSchool != other.idSchool) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.StudyPK[ idUsers=" + idUsers + ", idSchool=" + idSchool + " ]";
    }
    
}
