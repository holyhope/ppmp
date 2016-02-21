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
@Table(name = "study", catalog = "online_cv", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Study.findAll", query = "SELECT s FROM Study s"),
    @NamedQuery(name = "Study.findByIdUsers", query = "SELECT s FROM Study s WHERE s.studyPK.idUsers = :idUsers"),
    @NamedQuery(name = "Study.findByIdSchool", query = "SELECT s FROM Study s WHERE s.studyPK.idSchool = :idSchool"),
    @NamedQuery(name = "Study.findByYearStart", query = "SELECT s FROM Study s WHERE s.yearStart = :yearStart"),
    @NamedQuery(name = "Study.findByYearEnd", query = "SELECT s FROM Study s WHERE s.yearEnd = :yearEnd")})
public class Study implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StudyPK studyPK;
    @Column(name = "year_start")
    @Temporal(TemporalType.DATE)
    private Date yearStart;
    @Column(name = "year_end")
    @Temporal(TemporalType.DATE)
    private Date yearEnd;
    @JoinColumn(name = "id_users", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User users;
    @JoinColumn(name = "id_school", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private School school;

    public Study() {
    }

    public Study(StudyPK studyPK) {
        this.studyPK = studyPK;
    }

    public Study(int idUsers, int idSchool) {
        this.studyPK = new StudyPK(idUsers, idSchool);
    }

    public StudyPK getStudyPK() {
        return studyPK;
    }

    public void setStudyPK(StudyPK studyPK) {
        this.studyPK = studyPK;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studyPK != null ? studyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Study)) {
            return false;
        }
        Study other = (Study) object;
        if ((this.studyPK == null && other.studyPK != null) || (this.studyPK != null && !this.studyPK.equals(other.studyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Study[ studyPK=" + studyPK + " ]";
    }
    
}
