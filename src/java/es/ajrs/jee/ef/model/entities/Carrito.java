package es.ajrs.jee.ef.model.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * 
 * @author PerlaNegra
 */

public class Carrito implements Serializable {
    
    private ArrayList<Oferta> ofertasCarrito;

    /**
     *
     */
    public Carrito() {
        
        ofertasCarrito = new ArrayList<>();
        
    }

    /**
     *
     * @return
     */
    public ArrayList<Oferta> getOfertasCarrito() {
        return ofertasCarrito;
    }

    /**
     *
     * @param ofertasCarrito
     */
    public void setOfertasSeleccionadas(ArrayList<Oferta> ofertasCarrito) {
        this.ofertasCarrito = ofertasCarrito;
    }

}
