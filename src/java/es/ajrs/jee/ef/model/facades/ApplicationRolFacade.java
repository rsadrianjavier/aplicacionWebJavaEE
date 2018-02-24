/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.facades;

import es.ajrs.jee.ef.model.entities.ApplicationRol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PerlaNegra
 */
@Stateless
public class ApplicationRolFacade extends AbstractFacade<ApplicationRol> {
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
    public ApplicationRolFacade() {
        super(ApplicationRol.class);
    }
    
}
