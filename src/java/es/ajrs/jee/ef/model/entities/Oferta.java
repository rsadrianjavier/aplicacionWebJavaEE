/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "offers", catalog = "ajrs_jee_ef", schema = "")
@NamedQueries({
    @NamedQuery(name = "Oferta.findActivos", query = "SELECT o FROM Oferta o WHERE o.active = true"),
    @NamedQuery(name = "Oferta.findAll", query = "SELECT o FROM Oferta o"),
    @NamedQuery(name = "Oferta.findById", query = "SELECT o FROM Oferta o WHERE o.id = :id"),
    @NamedQuery(name = "Oferta.findByTitle", query = "SELECT o FROM Oferta o WHERE o.title = :title"),
    @NamedQuery(name = "Oferta.findByDescription", query = "SELECT o FROM Oferta o WHERE o.description = :description"),
    @NamedQuery(name = "Oferta.findByStartPrice", query = "SELECT o FROM Oferta o WHERE o.startPrice = :startPrice"),
    @NamedQuery(name = "Oferta.findByOfferPrice", query = "SELECT o FROM Oferta o WHERE o.offerPrice = :offerPrice"),
    @NamedQuery(name = "Oferta.findByImage", query = "SELECT o FROM Oferta o WHERE o.image = :image"),
    @NamedQuery(name = "Oferta.findByStartDate", query = "SELECT o FROM Oferta o WHERE o.startDate = :startDate"),
    @NamedQuery(name = "Oferta.findByEndDate", query = "SELECT o FROM Oferta o WHERE o.endDate = :endDate"),
    @NamedQuery(name = "Oferta.findByActive", query = "SELECT o FROM Oferta o WHERE o.active = :active"),
    @NamedQuery(name = "Oferta.findByApplicationUsersId", query = "SELECT o FROM Oferta o WHERE o.applicationUsersId = :applicationUsersId")})
public class Oferta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_price")
    private float startPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "offer_price")
    private float offerPrice;
    @Size(max = 100)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offersId")
    private List<Cupon> cuponList;
    @JoinColumn(name = "application_users_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario applicationUsersId;

    /**
     *
     */
    public Oferta() {
        this(0);
    }

    /**
     *
     * @param id
     */
    public Oferta(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param title
     * @param startPrice
     * @param offerPrice
     * @param startDate
     * @param endDate
     * @param active
     */
    public Oferta(Integer id, String title, float startPrice, float offerPrice, Date startDate, Date endDate, boolean active) {
        this.id = id;
        this.title = title;
        this.startPrice = startPrice;
        this.offerPrice = offerPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
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
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public float getStartPrice() {
        return startPrice;
    }

    /**
     *
     * @param startPrice
     */
    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    /**
     *
     * @return
     */
    public float getOfferPrice() {
        return offerPrice;
    }

    /**
     *
     * @param offerPrice
     */
    public void setOfferPrice(float offerPrice) {
        this.offerPrice = offerPrice;
    }

    /**
     *
     * @return
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     */
    public boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     *
     * @return
     */
    public List<Cupon> getCuponList() {
        return cuponList;
    }

    /**
     *
     * @param cuponList
     */
    public void setCuponList(List<Cupon> cuponList) {
        this.cuponList = cuponList;
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
        if (!(object instanceof Oferta)) {
            return false;
        }
        Oferta other = (Oferta) object;
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
        return "es.ajrs.jee.ef.model.entities.Oferta[ id=" + id + " ]";
    }
    
}
