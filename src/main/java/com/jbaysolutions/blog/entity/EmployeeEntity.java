/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbaysolutions.blog.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rui
 */
@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idemployee")
    private Integer idclient;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;
    
    @ManyToOne
    @JoinColumn(name = "idcompany", referencedColumnName = "idcompany")
    private CompanyEntity company;

    public EmployeeEntity() {
    }

    public EmployeeEntity(Integer idclient) {
        this.idclient = idclient;
    }

    public EmployeeEntity(Integer idclient, String name) {
        this.idclient = idclient;
        this.name = name;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @XmlTransient
    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclient != null ? idclient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeEntity)) {
            return false;
        }
        EmployeeEntity other = (EmployeeEntity) object;
        if ((this.idclient == null && other.idclient != null) || (this.idclient != null && !this.idclient.equals(other.idclient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "syshex.tutorials.jpa2.tut3.entity.ClientEntity[ idclient=" + idclient + " ]";
    }
    
}
