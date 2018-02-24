/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.controllers.beans;

import es.ajrs.jee.ef.model.entities.RolUsuario;
import es.ajrs.jee.ef.model.entities.Usuario;
import es.ajrs.jee.ef.model.facades.RolUsuarioFacade;
import es.ajrs.jee.ef.model.facades.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author PerlaNegra
 */
@ManagedBean(name = "gestionAdministradorController")
@RequestScoped
public class GestionAdministradorController {
    @EJB
    private RolUsuarioFacade rolUsuarioFacade;
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    
    private List<Usuario> UsuariosBuscados;
    
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
    public List<Usuario> getUsuariosBuscados() {
        return UsuariosBuscados;
    }

    /**
     *
     * @param UsuariosBuscados
     */
    public void setUsuariosBuscados(List<Usuario> UsuariosBuscados) {
        this.UsuariosBuscados = UsuariosBuscados;
    }
    
    /**
     *
     * @return
     */
    public List<Usuario> getUsuariosCliente() {
        
            UsuariosBuscados = findUsuariosPorRol("Cliente");
            
        return UsuariosBuscados;
    }

    /**
     *
     * @return
     */
    public List<Usuario> getUsuariosNegocio() {
        
            UsuariosBuscados = findUsuariosPorRol("Negocio");
        
        return UsuariosBuscados;
    }
    
    private List<Usuario> findUsuariosPorRol(String rolename) {
        
        List<RolUsuario> rolUsuariosBuscados = getRolUsuarioFacade().getListaUsuarios(rolename);
        ArrayList<Usuario> temporales = new ArrayList<>();
        for (RolUsuario rolUsuario : rolUsuariosBuscados) {
            if(getUsuarioFacade().damelosUsuarios(rolUsuario.getUsername()).size() == 1){
                Usuario nuevoUsuario = getUsuarioFacade().damelosUsuarios(rolUsuario.getUsername()).get(0);
                temporales.add(nuevoUsuario); 
            }  
        }
        return temporales;
    }
    /**
     * Creates a new instance of gestionAdministradorController
     */
    public GestionAdministradorController() {
    }
}
