/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.onlineCV.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maxime
 */
@Entity
@Table(name = "experiences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Experiences.findAll", query = "SELECT e FROM Experiences e"),
    @NamedQuery(name = "Experiences.findByIdUsers", query = "SELECT e FROM Experiences e WHERE e.experiencesPK.idUsers = :idUsers"),
    @NamedQuery(name = "Experiences.findByIdCompany", query = "SELECT e FROM Experiences e WHERE e.experiencesPK.idCompany = :idCompany"),
    @NamedQuery(name = "Experiences.findByLabel", query = "SELECT e FROM Experiences e WHERE e.label = :label"),
    @NamedQuery(name = "Experiences.findByYearStart", query = "SELECT e FROM Experiences e WHERE e.yearStart = :yearStart"),
    @NamedQuery(name = "Experiences.findByYearEnd", query = "SELECT e FROM Experiences e WHERE e.yearEnd = :yearEnd")})
public class Experiences implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExperiencesPK experiencesPK;
    @Column(name = "label")
    private String label;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "year_start")
    @Temporal(TemporalType.DATE)
    private Date yearStart;
    @Column(name = "year_end")
    @Temporal(TemporalType.DATE)
    private Date yearEnd;
    @JoinColumn(name = "id_users", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User users;
    @JoinColumn(name = "id_company", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Company company;

    public Experiences() {
    }

    public Experiences(ExperiencesPK experiencesPK) {
        this.experiencesPK = experiencesPK;
    }

    public Experiences(int idUsers, int idCompany) {
        this.experiencesPK = new ExperiencesPK(idUsers, idCompany);
    }

    public ExperiencesPK getExperiencesPK() {
        return experiencesPK;
    }

    public void setExperiencesPK(ExperiencesPK experiencesPK) {
        this.experiencesPK = experiencesPK;
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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (experiencesPK != null ? experiencesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Experiences)) {
            return false;
        }
        Experiences other = (Experiences) object;
        if ((this.experiencesPK == null && other.experiencesPK != null) || (this.experiencesPK != null && !this.experiencesPK.equals(other.experiencesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Experiences[ experiencesPK=" + experiencesPK + " ]";
    }
    
}
