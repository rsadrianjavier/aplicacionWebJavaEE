/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.facades;

import es.ajrs.jee.ef.model.entities.Usuario;
import java.sql.ResultSet;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "AJRS_JEE_EFPU")
    private EntityManager em;

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
    public UsuarioFacade() {
        super(Usuario.class);
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public Usuario doLogin(String username, String password) {
        TypedQuery<Usuario> query = getEntityManager().createNamedQuery("Usuario.login", Usuario.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Usuario> usuarios = query.getResultList();
        if(usuarios.size() == 1){
            return usuarios.get(0);
        }
        return null;
    }

    /**
     *
     * @param username
     * @return
     */
    public List<Usuario> damelosUsuarios(String username){
        TypedQuery<Usuario> query = getEntityManager().createNamedQuery("Usuario.findByUsername", Usuario.class);
        query.setParameter("username", username);
        List<Usuario> usuariosParaDar = query.getResultList();
        return usuariosParaDar;
    }    

    /**
     *
     * @param usuario
     * @return
     */
    public boolean exists(Usuario usuario) {
        TypedQuery query = getEntityManager().createNamedQuery("Usuario.findByUsername", Usuario.class);
        query.setParameter("username", usuario.getUsername());
        List<Usuario> usuarios = query.getResultList();
        if(usuarios.isEmpty()){
            TypedQuery queryMail = getEntityManager().createNamedQuery("Usuario.findByEmail", Usuario.class);
            queryMail.setParameter("email", usuario.getEmail());
            List<Usuario> mailUsuarios = queryMail.getResultList();
            if(mailUsuarios.isEmpty()){
                return  false;
            }
        }
        return true;

    }
    
    
}
