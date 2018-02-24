package es.ajrs.jee.ef.controllers.beans;

import es.ajrs.jee.ef.controllers.util.JsfUtil;
import es.ajrs.jee.ef.model.entities.Carrito;
import es.ajrs.jee.ef.model.entities.Cupon;
import es.ajrs.jee.ef.model.entities.Oferta;
import es.ajrs.jee.ef.model.entities.Usuario;
import es.ajrs.jee.ef.model.facades.CuponFacade;

import es.ajrs.jee.ef.model.facades.OfertaFacade;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PerlaNegra
 */
@ManagedBean(name = "carritoController")
@SessionScoped
public class CarritoController implements Serializable  {
    @EJB
    private CuponFacade cuponFacade;

    @EJB
    private OfertaFacade ofertaFacade;

    private LinkedList<Cupon> cuponesCreados;
        
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
    
    private Oferta seleccionada;
    
    private Carrito carrito = new Carrito();
    //private ArrayList ofertasSeleccionadas = new ArrayList();
            
    /**
     *
     */
    public CarritoController() {
    }

    /**
     *
     * @return
     */
    public Carrito getCarrito() {
        return carrito;
    }

    /**
     *
     * @param carrito
     */
    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
  
    /**
     *
     * @return
     */
    public Oferta getSeleccionada() {
        return seleccionada;
    }

    /**
     *
     * @param seleccionada
     */
    public void setSeleccionada(Oferta seleccionada) {
        this.seleccionada = seleccionada;
    }
    
    /**
     *
     */
    public void anadirCarrito(){
        
        carrito.getOfertasCarrito().add(seleccionada);
        seleccionada = null;
    }

    /**
     *
     */
    public void vaciarCarrito(){
        carrito.getOfertasCarrito().clear();
    }
    
    /**
     *
     * @param ofertasCarrito
     * @param usuario
     */
    public void finalizar(ArrayList<Oferta> ofertasCarrito, Usuario usuario){
        
        for (Oferta ofertaCarrito : ofertasCarrito) {
            Cupon cupon = new Cupon();
            cupon.setOffersId(ofertaCarrito);
            cupon.setApplicationUsersId(usuario);
            String codigoAutogenerado = getCadenaAlfanumAleatoria (8);

            cupon.setGeneratedCode(codigoAutogenerado);            
            cupon.setPurchaseDatetime(new Date(System.currentTimeMillis()));
            getCuponFacade().create(cupon);
            JsfUtil.addSuccessMessage("Compra realizada con éxito");
            
        }
        this.vaciarCarrito();      
    }
    
    private String getCadenaAlfanumAleatoria (int longitud){
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while ( i < longitud){
            char c = (char)r.nextInt(255);
            if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
                cadenaAleatoria += c;
                i ++;
            }
        }
        return cadenaAleatoria;
    }
}
