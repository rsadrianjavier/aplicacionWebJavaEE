/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.model.entities;

import java.util.ArrayList;

/**
 *
 * @author PerlaNegra
 */
public class Pedido {
    
    private ArrayList<Cupon> cuponesCreados;
    
    public Pedido() {
        
        cuponesCreados = new ArrayList<>();
    }

    public ArrayList<Cupon> getCuponesCreados() {
        return cuponesCreados;
    }

    public void setCuponesCreados(ArrayList<Cupon> cuponesCreados) {
        this.cuponesCreados = cuponesCreados;
    }

    public Object iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
