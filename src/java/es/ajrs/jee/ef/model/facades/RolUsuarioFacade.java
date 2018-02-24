/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.facades;

import es.ajrs.jee.ef.model.entities.RolUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author PerlaNegra
 */
@Stateless
public class RolUsuarioFacade extends AbstractFacade<RolUsuario> {
    @PersistenceContext(unitName = "AJRS_JEE_EFPU")
    private EntityManager em;
    private List<RolUsuario> listaUsuarios;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public RolUsuarioFacade() {
        super(RolUsuario.class);
    }

    /**
     *
     * @param username
     * @return
     */
    public RolUsuario getRoldeUsuario(String username) {
        TypedQuery<RolUsuario> query = getEntityManager().createNamedQuery("RolUsuario.findByUsername", RolUsuario.class);
        query.setParameter("username", username);
        List<RolUsuario> roles = query.getResultList();
        if(roles.size() == 1){
            return roles.get(0);
        }
        return null;
    }
    
    /**
     *
     * @param rolename
     * @return
     */
    public List<RolUsuario> getListaUsuarios(String rolename){
        TypedQuery<RolUsuario> query = getEntityManager().createNamedQuery("RolUsuario.findByRolename", RolUsuario.class);
        query.setParameter("rolename", rolename);
        listaUsuarios = query.getResultList();
        return listaUsuarios;
        
    }
    
}
