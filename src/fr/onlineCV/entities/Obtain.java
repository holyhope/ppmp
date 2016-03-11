/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.onlineCV.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maxime
 */
@Entity
@Table(name = "obtain")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Obtain.findAll", query = "SELECT o FROM Obtain o"),
    @NamedQuery(name = "Obtain.findByIdUsers", query = "SELECT o FROM Obtain o WHERE o.obtainPK.idUsers = :idUsers"),
    @NamedQuery(name = "Obtain.findByIdSchool", query = "SELECT o FROM Obtain o WHERE o.obtainPK.idSchool = :idSchool"),
    @NamedQuery(name = "Obtain.findByIdDiploma", query = "SELECT o FROM Obtain o WHERE o.obtainPK.idDiploma = :idDiploma"),
    @NamedQuery(name = "Obtain.findByYear", query = "SELECT o FROM Obtain o WHERE o.year = :year")})
public class Obtain implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObtainPK obtainPK;
    @Basic(optional = false)
    @Column(name = "year")
    @Temporal(TemporalType.DATE)
    private Date year;
    @JoinColumn(name = "id_users", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User users;
    @JoinColumn(name = "id_school", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private School school;
    @JoinColumn(name = "id_diploma", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Diploma diploma;

    public Obtain() {
    }

    public Obtain(ObtainPK obtainPK) {
        this.obtainPK = obtainPK;
    }

    public Obtain(ObtainPK obtainPK, Date year) {
        this.obtainPK = obtainPK;
        this.year = year;
    }

    public Obtain(int idUsers, int idSchool, int idDiploma) {
        this.obtainPK = new ObtainPK(idUsers, idSchool, idDiploma);
    }

    public ObtainPK getObtainPK() {
        return obtainPK;
    }

    public void setObtainPK(ObtainPK obtainPK) {
        this.obtainPK = obtainPK;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Diploma getDiploma() {
        return diploma;
    }

    public void setDiploma(Diploma diploma) {
        this.diploma = diploma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (obtainPK != null ? obtainPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obtain)) {
            return false;
        }
        Obtain other = (Obtain) object;
        if ((this.obtainPK == null && other.obtainPK != null) || (this.obtainPK != null && !this.obtainPK.equals(other.obtainPK))) {
            return false;
        }
        return true;
    }
}
