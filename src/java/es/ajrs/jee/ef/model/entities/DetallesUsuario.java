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
@Table(name = "application_user_details", catalog = "ajrs_jee_ef", schema = "")
@NamedQueries({
    @NamedQuery(name = "DetallesUsuario.findAll", query = "SELECT d FROM DetallesUsuario d"),
    @NamedQuery(name = "DetallesUsuario.findById", query = "SELECT d FROM DetallesUsuario d WHERE d.id = :id"),
    @NamedQuery(name = "DetallesUsuario.findByName", query = "SELECT d FROM DetallesUsuario d WHERE d.name = :name"),
    @NamedQuery(name = "DetallesUsuario.findByDniCif", query = "SELECT d FROM DetallesUsuario d WHERE d.dniCif = :dniCif"),
    @NamedQuery(name = "DetallesUsuario.findByAddress", query = "SELECT d FROM DetallesUsuario d WHERE d.address = :address"),
    @NamedQuery(name = "DetallesUsuario.findByCity", query = "SELECT d FROM DetallesUsuario d WHERE d.city = :city"),
    @NamedQuery(name = "DetallesUsuario.findByProvince", query = "SELECT d FROM DetallesUsuario d WHERE d.province = :province"),
    @NamedQuery(name = "DetallesUsuario.findByCountry", query = "SELECT d FROM DetallesUsuario d WHERE d.country = :country"),
    @NamedQuery(name = "DetallesUsuario.findByLatitude", query = "SELECT d FROM DetallesUsuario d WHERE d.latitude = :latitude"),
    @NamedQuery(name = "DetallesUsuario.findByLongitude", query = "SELECT d FROM DetallesUsuario d WHERE d.longitude = :longitude"),
    @NamedQuery(name = "DetallesUsuario.findByDateOfRegistration", query = "SELECT d FROM DetallesUsuario d WHERE d.dateOfRegistration = :dateOfRegistration"),
    @NamedQuery(name = "DetallesUsuario.findByLeavingDate", query = "SELECT d FROM DetallesUsuario d WHERE d.leavingDate = :leavingDate"),
    @NamedQuery(name = "DetallesUsuario.findByApplicationUsersId", query = "SELECT d FROM DetallesUsuario d WHERE d.applicationUsersId = :applicationUsersId")})
public class DetallesUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "dni_cif")
    private String dniCif;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "province")
    private String province;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "country")
    private String country;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Column(name = "latitude")
    private Double latitude;
    
    @Column(name = "longitude")
    private Double longitude;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_registration")
    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;
    
    @Column(name = "leaving_date")
    @Temporal(TemporalType.DATE)
    private Date leavingDate;
    
    @JoinColumn(name = "application_users_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario applicationUsersId;

    /**
     *
     */
    public DetallesUsuario() {
        this(0);
    }

    /**
     *
     * @param id
     */
    public DetallesUsuario(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param name
     * @param dniCif
     * @param address
     * @param city
     * @param province
     * @param country
     * @param dateOfRegistration
     */
    public DetallesUsuario(Integer id, String name, String dniCif, String address, String city, String province, String country, Date dateOfRegistration) {
        this.id = id;
        this.name = name;
        this.dniCif = dniCif;
        this.address = address;
        this.city = city;
        this.province = province;
        this.country = country;
        this.dateOfRegistration = dateOfRegistration;
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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDniCif() {
        return dniCif;
    }

    /**
     *
     * @param dniCif
     */
    public void setDniCif(String dniCif) {
        this.dniCif = dniCif;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getProvince() {
        return province;
    }

    /**
     *
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     */
    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    /**
     *
     * @param dateOfRegistration
     */
    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    /**
     *
     * @return
     */
    public Date getLeavingDate() {
        return leavingDate;
    }

    /**
     *
     * @param leavingDate
     */
    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
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
        if (!(object instanceof DetallesUsuario)) {
            return false;
        }
        DetallesUsuario other = (DetallesUsuario) object;
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
        return "es.ajrs.jee.ef.model.entities.DetallesUsuario[ id=" + id + " ]";
    }
    
}
