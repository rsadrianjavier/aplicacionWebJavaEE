/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.controllers.beans;

import es.ajrs.jee.ef.model.entities.Cupon;
import es.ajrs.jee.ef.model.entities.Oferta;
import es.ajrs.jee.ef.model.entities.Usuario;
import es.ajrs.jee.ef.model.facades.CuponFacade;
import es.ajrs.jee.ef.model.facades.OfertaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author PerlaNegra
 */
@ManagedBean(name = "clienteController")
@RequestScoped
public class ClienteController {
    @EJB
    private CuponFacade cuponFacade;

    /**
     *
     * @return
     */
    public CuponFacade getCuponFacade() {
        return cuponFacade;
    }

    /**
     *
     * @param cuponFacade
     */
    public void setCuponFacade(CuponFacade cuponFacade) {
        this.cuponFacade = cuponFacade;
    }

    /**
     *
     * @param usuario
     * @return
     */
    public List<Cupon> findCuponesDelCliente(Usuario usuario) {
        
        List<Cupon> cuponesBuscados = getCuponFacade().cuponesPorCliente(usuario);
        
        return cuponesBuscados;
    }

    /**
     * Creates a new instance of gestionNegocioController
     */
    public ClienteController() {
    }
    
}
