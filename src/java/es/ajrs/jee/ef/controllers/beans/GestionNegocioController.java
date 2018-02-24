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
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author PerlaNegra
 */
@ManagedBean
@RequestScoped
public class GestionNegocioController {
    @EJB
    private CuponFacade cuponFacade;
    @EJB
    private OfertaFacade ofertaFacade;
    
    List<Oferta> ofertasBuscadas;
    List<Cupon> cuponesBuscados;

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
     * @return
     */
    public OfertaFacade getOfertaFacade() {
        return ofertaFacade;
    }

    /**
     *
     * @param ofertaFacade
     */
    public void setOfertaFacade(OfertaFacade ofertaFacade) {
        this.ofertaFacade = ofertaFacade;
    }
    
    /**
     *
     * @param usuario
     * @return
     */
    public List<Oferta> findOfertasDelNegocio(Usuario usuario) {
        
        ofertasBuscadas = getOfertaFacade().ofertasPorNegocio(usuario);       
        return ofertasBuscadas;
    }
    
    /**
     *
     * @param usuario
     * @return
     */
    public List<Cupon> findCuponesDelNegocio(Usuario usuario) {
        cuponesBuscados = new LinkedList<>();
        ofertasBuscadas = findOfertasDelNegocio(usuario);
        
        for (Oferta ofertaBuscada : ofertasBuscadas) {

            List<Cupon> cuponesBuscadosPorOferta = getCuponFacade().cuponesPorNegocio(ofertaBuscada);
            for(Cupon cupon : cuponesBuscadosPorOferta){
                cuponesBuscados.add(cupon);
            }
        }        
        return cuponesBuscados;
    }
    
    /**
     * Creates a new instance of gestionNegocioController
     */
    public GestionNegocioController() {
    }    
}
