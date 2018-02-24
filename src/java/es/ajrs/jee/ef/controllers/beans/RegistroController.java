/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.controllers.beans;

import es.ajrs.jee.ef.controllers.util.JsfUtil;
import es.ajrs.jee.ef.model.entities.DetallesUsuario;
import es.ajrs.jee.ef.model.entities.Usuario;
import es.ajrs.jee.ef.model.facades.DetallesUsuarioFacade;
import es.ajrs.jee.ef.model.facades.UsuarioFacade;
import java.sql.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author PerlaNegra
 */
@ManagedBean(name = "registroController")
@RequestScoped
public class RegistroController {
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private DetallesUsuarioFacade detallesUsuarioFacade;
    
    private Usuario usuario;
    private DetallesUsuario detallesUsuario;

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
    public Usuario getUsuario() {
        if(usuario == null){
            usuario = new Usuario();
        }
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     *
     * @return
     */
    public DetallesUsuario getDetallesUsuario() {
        if(detallesUsuario == null){
            detallesUsuario = new DetallesUsuario();
        }
        return detallesUsuario;
    }

    /**
     *
     * @param detallesUsuario
     */
    public void setDetallesUsuario(DetallesUsuario detallesUsuario) {
        this.detallesUsuario = detallesUsuario;
    }
    
    /**
     *
     */
    public void save(){
        if(usuario.getId()==0 && !getUsuarioFacade().exists(usuario) && !getDetallesUsuarioFacade().exists(detallesUsuario)){
            getUsuarioFacade().create(usuario);
            detallesUsuario.setApplicationUsersId(usuario);
            detallesUsuario.setDateOfRegistration(new Date(System.currentTimeMillis()));
            getDetallesUsuarioFacade().create(detallesUsuario );
            JsfUtil.addSuccessMessage("Usuario registrado con éxito");
            usuario = null;
            detallesUsuario = null;
            
        } else{
            JsfUtil.addErrorMessage("Error con las credenciales");
        }   
    }
}
