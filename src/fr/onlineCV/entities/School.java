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
@Table(name = "school")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s"),
    @NamedQuery(name = "School.findById", query = "SELECT s FROM School s WHERE s.id = :id"),
    @NamedQuery(name = "School.findByLabel", query = "SELECT s FROM School s WHERE s.label = :label"),
    @NamedQuery(name = "School.findByLocation", query = "SELECT s FROM School s WHERE s.location = :location"),
    @NamedQuery(name = "School.findByType", query = "SELECT s FROM School s WHERE s.type = :type"),
    @NamedQuery(name = "School.findByLabelLike", query = "SELECT s FROM School s WHERE s.label LIKE :label")})
public class School implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "label")
    private String label;
    @Column(name = "location")
    private String location;
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    private List<Obtain> obtainList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    private List<Study> studyList;

    public School() {
    }

    public School(Integer id) {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.School[ id=" + id + " ]";
    }
    
}
