/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.facades;

import es.ajrs.jee.ef.model.entities.Oferta;
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
public class OfertaFacade extends AbstractFacade<Oferta> {
    @PersistenceContext(unitName = "AJRS_JEE_EFPU")
    private EntityManager em;
    
    private List<Oferta> ofertasActivas;
    private List<Oferta> ofertasPorNegocio;

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
    public OfertaFacade() {
        super(Oferta.class);
    }
    
    /**
     *
     * @return
     */
    public List<Oferta> findActivos(){
        TypedQuery<Oferta> query = getEntityManager().createNamedQuery("Oferta.findActivos", Oferta.class);
        ofertasActivas = query.getResultList();
        return ofertasActivas;
    }
    
    /**
     *
     * @param usuario
     * @return
     */
    public List<Oferta> ofertasPorNegocio(Usuario usuario){
        TypedQuery<Oferta> query = getEntityManager().createNamedQuery("Oferta.findByApplicationUsersId", Oferta.class);
        query.setParameter("applicationUsersId", usuario);
        ofertasPorNegocio = query.getResultList();
        
        return ofertasPorNegocio;
    }

    
   
}
