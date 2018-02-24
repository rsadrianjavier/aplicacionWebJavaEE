/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PerlaNegra
 */
@Entity
@Table(name = "application_roles", catalog = "ajrs_jee_ef", schema = "")
@NamedQueries({
    @NamedQuery(name = "ApplicationRol.findAll", query = "SELECT a FROM ApplicationRol a"),
    @NamedQuery(name = "ApplicationRol.findById", query = "SELECT a FROM ApplicationRol a WHERE a.id = :id"),
    @NamedQuery(name = "ApplicationRol.findByRolename", query = "SELECT a FROM ApplicationRol a WHERE a.rolename = :rolename")})
public class ApplicationRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "rolename")
    private String rolename;

    /**
     *
     */
    public ApplicationRol() {
    }

    /**
     *
     * @param id
     */
    public ApplicationRol(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param rolename
     */
    public ApplicationRol(Integer id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getRolename() {
        return rolename;
    }

    /**
     *
     * @param rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationRol)) {
            return false;
        }
        ApplicationRol other = (ApplicationRol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "es.ajrs.jee.ef.model.entities.ApplicationRol[ id=" + id + " ]";
    }
    
}
