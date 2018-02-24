/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.facades;

import es.ajrs.jee.ef.model.entities.DetallesUsuario;
import es.ajrs.jee.ef.model.entities.Usuario;
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
public class DetallesUsuarioFacade extends AbstractFacade<DetallesUsuario> {
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
    public DetallesUsuarioFacade() {
        super(DetallesUsuario.class);
    }

    /**
     *
     * @param usuario
     * @return
     */
    public List<DetallesUsuario> damelosDetallesUsuarios(Usuario usuario) {
        TypedQuery<DetallesUsuario> query;
        query = getEntityManager().createNamedQuery("DetallesUsuario.findByApplicationUsersId", DetallesUsuario.class);
        query.setParameter("applicationUsersId", usuario);
        List<DetallesUsuario> detallesUsuarios = query.getResultList();
        return detallesUsuarios;
    }  

    /**
     *
     * @param detallesUsuario
     * @return
     */
    public boolean exists(DetallesUsuario detallesUsuario) {
        TypedQuery query = getEntityManager().createNamedQuery("DetallesUsuario.findByDniCif", DetallesUsuario.class);
        query.setParameter("dniCif", detallesUsuario.getDniCif());
        List<DetallesUsuario> detallesUsuarios = query.getResultList();
        if(detallesUsuarios.isEmpty()){
            return  false;
        }
        return true;
    }
}
