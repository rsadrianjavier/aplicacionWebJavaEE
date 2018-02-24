/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.facades;

import es.ajrs.jee.ef.model.entities.Cupon;
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
public class CuponFacade extends AbstractFacade<Cupon> {
    @PersistenceContext(unitName = "AJRS_JEE_EFPU")
    private EntityManager em;
    
    private List<Cupon> cuponesPorNegocio;
    private List<Cupon> cuponesPorCliente;
    
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
    public CuponFacade() {
        super(Cupon.class);
    }
    
    /**
     *
     * @param oferta
     * @return
     */
    public List<Cupon> cuponesPorNegocio(Oferta oferta){
        TypedQuery<Cupon> query = getEntityManager().createNamedQuery("Cupon.findByOffersId", null);
        query.setParameter("offersId", oferta);
        cuponesPorNegocio = query.getResultList();
        
        return cuponesPorNegocio;
    }

    /**
     *
     * @param usuario
     * @return
     */
    public List<Cupon> cuponesPorCliente(Usuario usuario) {
        TypedQuery<Cupon> query = getEntityManager().createNamedQuery("Cupon.findByApplicationUsersId", Cupon.class);
        query.setParameter("applicationUsersId", usuario);
        cuponesPorCliente = query.getResultList();
        
        return cuponesPorCliente;
    }

}