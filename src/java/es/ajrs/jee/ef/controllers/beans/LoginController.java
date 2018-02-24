/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.controllers.beans;

import es.ajrs.jee.ef.controllers.util.JsfUtil;
import es.ajrs.jee.ef.model.entities.DetallesUsuario;
import es.ajrs.jee.ef.model.entities.RolUsuario;
import es.ajrs.jee.ef.model.entities.Usuario;
import es.ajrs.jee.ef.model.facades.DetallesUsuarioFacade;
import es.ajrs.jee.ef.model.facades.RolUsuarioFacade;
import es.ajrs.jee.ef.model.facades.UsuarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author PerlaNegra
 */

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable{
    @EJB
    private DetallesUsuarioFacade detallesUsuarioFacade;
    @EJB
    private RolUsuarioFacade rolUsuarioFacade;
    @EJB
    private UsuarioFacade usuarioFacade;

    public LoginController() {
    }

    /**
     *
     * @return
     */
    public DetallesUsuarioFacade getDetallesUsuarioFacade() {
        return detallesUsuarioFacade;
    }

    /**
     *
     * @param detallesUsuarioFacade
     */
    public void setDetallesUsuarioFacade(DetallesUsuarioFacade detallesUsuarioFacade) {
        this.detallesUsuarioFacade = detallesUsuarioFacade;
    }

    /**
     *
     * @return
     */
    public RolUsuarioFacade getRolUsuarioFacade() {
        return rolUsuarioFacade;
    }

    /**
     *
     * @param rolUsuarioFacade
     */
    public void setRolUsuarioFacade(RolUsuarioFacade rolUsuarioFacade) {
        this.rolUsuarioFacade = rolUsuarioFacade;
    }
    
    /**
     *
     * @return
     */
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    /**
     *
     * @param usuarioFacade
     */
    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
    
    
    private Usuario user;
    private String username;
    private String password;
    private RolUsuario rolusuario;
    private String rol = "";
    //private int id;
    
    private DetallesUsuario detallesUsuarioLogueado;

    List<DetallesUsuario> adaptaList;

    public void setAdaptaList(List<DetallesUsuario> adaptaList) {
        this.adaptaList = adaptaList;
    }
    /**
     *
     * @return
     */
    public DetallesUsuario getDetallesUsuarioLogueado() {
        return detallesUsuarioLogueado;
    }
    
    public List<DetallesUsuario> getAdaptaList(){
        adaptaList = new ArrayList();
        adaptaList.add(detallesUsuarioLogueado);
        return adaptaList;
        
    }
    /**
     *
     * @param detallesUsuarioLogueado
     */
    public void setDetallesUsuarioLogueado(DetallesUsuario detallesUsuarioLogueado) {
        this.detallesUsuarioLogueado = detallesUsuarioLogueado;
    }
    
    /**
     *
     * @return
     */
    public RolUsuario getRolusuario() {
        return rolusuario;
    }

    /**
     *
     * @param rolusuario
     */
    public void setRolusuario(RolUsuario rolusuario) {
        this.rolusuario = rolusuario;
    }
    
    /**
     *
     * @return
     */
    public String eligeHome(){
        if(rol.equalsIgnoreCase("cliente")){
            return "homeCliente";
        }
        return "homeStandar";
    }
    
    /**
     *
     * @return
     */
    public String doLogin(){
        if(user == null && !username.isEmpty() && !password.isEmpty()){
            user = usuarioFacade.doLogin(username, password);
            if(user == null){
                username = "";
                password = "";
                JsfUtil.addErrorMessage("Error con las credenciales");
                return "failure";
            }else{
                //id = user.getId();
                rolusuario = rolUsuarioFacade.getRoldeUsuario(username);
                rol = rolusuario.getRolename();
                
                if(rol.equalsIgnoreCase("administrador")){
                    return "admin";
                }else if (rol.equalsIgnoreCase("negocio")) {

                    if(getDetallesUsuarioFacade().damelosDetallesUsuarios(user).size() == 1){
                        detallesUsuarioLogueado = getDetallesUsuarioFacade().damelosDetallesUsuarios(user).get(0);
                    }
                    return "nego";
                }else if (rol.equalsIgnoreCase("cliente")) {
                    if(getDetallesUsuarioFacade().damelosDetallesUsuarios(user).size() == 1){
                        detallesUsuarioLogueado = getDetallesUsuarioFacade().damelosDetallesUsuarios(user).get(0);
                    }
                    return "clien";
                }               
            }
        }
        return "failure";
    }

    /**
     *
     * @return
     */
    public String getRol() {
        return rol;
    }

    /**
     *
     * @param rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     *
     * @return
     */
    public Usuario getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(Usuario user) {
        this.user = user;
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
    public String doLogout(){
        if(user != null){
            JsfUtil.addErrorMessage("Cerrando sesión");
            user = null;
            username = null;
            password = null;

            return "logout";
        }
        JsfUtil.addErrorMessage("Ninguna sesión abierta");
        return "nada";
    }
    
    /**
     * Método que comprueba si un usuario tiene un rol determinado.
     *
     * @param role String rol a comprobar.
     * @return boolean true si posee el rol, false si no.
     */
    public boolean isUserInRole(String role) {
        return getRol().contains(role);
    }

    /**
     * Método que comprueba si el usuario tiene permiso de administrador.
     *
     * @return boolean true si contiene ese rol, false si no.
     */
    public boolean isAdministrador() {
        return isUserInRole("Administrador");
    }
    
    /**
     * Método que comprueba si el usuario tiene permiso de cliente.
     *
     * @return boolean true si contiene ese rol, false si no.
     */
    public boolean isCliente() {
        return isUserInRole("Cliente");
    }
    /**
     * Método que comprueba si el usuario tiene permiso de negocio.
     *
     * @return boolean true si contiene ese rol, false si no.
     */
    public boolean isNegocio() {
        return isUserInRole("Negocio");
    }
}
