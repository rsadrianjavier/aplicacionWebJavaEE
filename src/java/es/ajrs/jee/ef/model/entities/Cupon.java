/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PerlaNegra
 */
@Entity
@Table(name = "coupons", catalog = "ajrs_jee_ef", schema = "")
@NamedQueries({
    @NamedQuery(name = "Cupon.findAll", query = "SELECT c FROM Cupon c"),
    
    @NamedQuery(name = "Cupon.findByOffersId", query = "SELECT c FROM Cupon c WHERE c.offersId = :offersId"),
    @NamedQuery(name = "Cupon.findByApplicationUsersId", query = "SELECT c FROM Cupon c WHERE c.applicationUsersId = :applicationUsersId"),
    
    @NamedQuery(name = "Cupon.findById", query = "SELECT c FROM Cupon c WHERE c.id = :id"),
    @NamedQuery(name = "Cupon.findByGeneratedCode", query = "SELECT c FROM Cupon c WHERE c.generatedCode = :generatedCode"),
    @NamedQuery(name = "Cupon.findByPurchaseDatetime", query = "SELECT c FROM Cupon c WHERE c.purchaseDatetime = :purchaseDatetime"),
    @NamedQuery(name = "Cupon.findByUsed", query = "SELECT c FROM Cupon c WHERE c.used = :used")})
public class Cupon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    
    @Column(name = "generated_code")
    private String generatedCode;
    @Basic(optional = false)
    @NotNull
    
    @Column(name = "purchase_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDatetime;
    @Basic(optional = false)
    @NotNull
    
    @Column(name = "used")
    private boolean used;
    
    @JoinColumn(name = "offers_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Oferta offersId;
    
    @JoinColumn(name = "application_users_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario applicationUsersId;

    /**
     *
     */
    public Cupon() {
        this(0);
    }

    /**
     *
     * @param id
     */
    public Cupon(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param generatedCode
     * @param purchaseDatetime
     * @param used
     */
    public Cupon(Integer id, String generatedCode, Date purchaseDatetime, boolean used) {
        this.id = id;
        this.generatedCode = generatedCode;
        this.purchaseDatetime = purchaseDatetime;
        this.used = used;
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
    public String getGeneratedCode() {
        return generatedCode;
    }

    /**
     *
     * @param generatedCode
     */
    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    /**
     *
     * @return
     */
    public Date getPurchaseDatetime() {
        return purchaseDatetime;
    }

    /**
     *
     * @param purchaseDatetime
     */
    public void setPurchaseDatetime(Date purchaseDatetime) {
        this.purchaseDatetime = purchaseDatetime;
    }

    /**
     *
     * @return
     */
    public boolean getUsed() {
        return used;
    }

    /**
     *
     * @param used
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     *
     * @return
     */
    public Oferta getOffersId() {
        return offersId;
    }

    /**
     *
     * @param offersId
     */
    public void setOffersId(Oferta offersId) {
        this.offersId = offersId;
    }

    /**
     *
     * @return
     */
    public Usuario getApplicationUsersId() {
        return applicationUsersId;
    }

    /**
     *
     * @param applicationUsersId
     */
    public void setApplicationUsersId(Usuario applicationUsersId) {
        this.applicationUsersId = applicationUsersId;
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
        if (!(object instanceof Cupon)) {
            return false;
        }
        Cupon other = (Cupon) object;
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
        return "es.ajrs.jee.ef.model.entities.Cupon[ id=" + id + " ]";
    }
    
}
