/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author PerlaNegra
 */
@Entity
@Table(name = "application_users", catalog = "ajrs_jee_ef", schema = "")
@NamedQueries({
    @NamedQuery(name = "Usuario.login", query = "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password"),
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByActive", query = "SELECT u FROM Usuario u WHERE u.active = :active")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "email")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    
    @Column(name = "active")
    private boolean active;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationUsersId")
    private List<Cupon> cuponList;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationUsersId")
    private List<Oferta> ofertaList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "applicationUsersId")
    private List<DetallesUsuario> detallesUsuarioList;

    /**
     *
     */
    public Usuario() {
        this(0);
    }

    /**
     *
     * @param id
     */
    public Usuario(Integer id) {
        this.id = id;
    }

    /**
     *
     * @param id
     * @param username
     * @param password
     * @param email
     * @param active
     */
    public Usuario(Integer id, String username, String password, String email, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
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
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
    public List<Oferta> getOfertaList() {
        return ofertaList;
    }

    /**
     *
     * @param ofertaList
     */
    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }

    /**
     *
     * @return
     */
    public List<DetallesUsuario> getDetallesUsuarioList() {
        return detallesUsuarioList;
    }

    /**
     *
     * @param detallesUsuarioList
     */
    public void setDetallesUsuarioList(List<DetallesUsuario> detallesUsuarioList) {
        this.detallesUsuarioList = detallesUsuarioList;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
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
        return "es.ajrs.jee.ef.model.entities.Usuario[ id=" + id + " ]";
    }
    
}
