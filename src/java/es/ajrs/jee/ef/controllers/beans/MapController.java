/*
 * The MIT License
 *
 * Copyright 2015 SEAS - Estudios Abiertos.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package es.ajrs.jee.ef.controllers.beans;

import es.ajrs.jee.ef.services.model.Address;
import es.ajrs.jee.ef.services.CoordinatesService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * Clase con el bean que controla la vista del mapa.
 *
 * @author Juan José Hernández Alonso
 */
@Named(value = "mapController")
@SessionScoped
public class MapController implements Serializable {

    private Address address;
    private MapModel model;
    private Marker marker;

    /**
     * Creates a new instance of MapBean.
     */
    public MapController() {
        model = new DefaultMapModel();
        address = new Address();
    }

    /**
     * Método que añade un marcador al modelo del mapa.
     */
    public void addMarker() {
        model.addOverlay(new Marker(new LatLng(address.getLatitude(), address.getLongitude()), address.getFullAddress()));
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", address.getCoordinatesForMap()));
    }

    /**
     * Método para mostrar un mensaje al contexto.
     *
     * @param message String.
     */
    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Getter del modelo del mapa.
     *
     * @return MapModel modelo.
     */
    public MapModel getModel() {
        return model;
    }

    /**
     * Setter del modelo del mapa.
     *
     * @param model MapModel.
     */
    public void setModel(MapModel model) {
        this.model = model;
    }

    /**
     * Método que recupera las coordenadas del API de Google Maps para una
     * dirección determinada.
     */
    public void retrieveCoordinates() {
        CoordinatesService service = new CoordinatesService();
        double[] coords = service.getLatitudeLongitude(getAddress().getFullAddress());
        getAddress().setLatitude(coords[0]);
        getAddress().setLongitude(coords[1]);
        System.err.println("Dirección: " + service.getAddress(getAddress().getLatitude(), getAddress().getLongitude()));
        resetModel();
        addMarker();
    }

    /**
     * Getter del marcador del mapa.
     *
     * @return Marker marcador del mapa.
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * Método que reinicia el modelo del mapa.
     */
    private void resetModel() {
        model = new DefaultMapModel();
    }

    /**
     * Getter de la dirección.
     *
     * @return Address dirección por la que consulta.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter de la dirección.
     *
     * @param defaultAddress Address dirección.
     */
    public void setAddress(Address defaultAddress) {
        this.address = defaultAddress;
    }

    /**
     * Método que muestra la dirección en la interfaz gráfica y que podríamos
     * aprovechar para almacenar en base de datos.
     */
    public void save() {
        String msg = getAddress().getFullAddress() + " " + getAddress().getCoordinatesForMap();
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }
}
