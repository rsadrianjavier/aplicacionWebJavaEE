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
package es.ajrs.jee.ef.services.model;

import es.ajrs.jee.ef.controllers.beans.LoginController;

/**
 * Clase que define la entidad dirección.
 *
 * @author Juan José Hernández Alonso
 */
public class Address {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String address;
    private String city;
    private String country;
    private String province;
    private String postalCode;
    private Double longitude;
    private Double latitude;
    private boolean defaultAddress;
    private LoginController lc;

    /**
     * Constructor por defecto.
     */
    public Address() {
        this.id = 0;
        this.defaultAddress = true;
        this.postalCode = "";
        this.address = lc.getDetallesUsuarioLogueado().getAddress();
        this.city = lc.getDetallesUsuarioLogueado().getCity();
        this.country = lc.getDetallesUsuarioLogueado().getCountry();
        this.province = lc.getDetallesUsuarioLogueado().getProvince();
    }

    /**
     * Constructor parametrizado.
     *
     * @param id Integer id de la dirección en "base de datos".
     */
    public Address(Integer id) {
        this.id = id;
    }

    /**
     * Constructor parametrizado con todas las propiedades del objeto. Se obvian
     * tanto la longitud como la latitud ya que debe ser el API de google el que
     * devuelva esos valores.
     *
     * @param id Integer id.
     * @param address String dirección.
     * @param city String ciudad.
     * @param country String pais.
     * @param province String provincia.
     * @param postalCode String código postal.
     * @param defaultAddress boolean parámetro que indica si es la dirección
     * principal o no.
     */
    public Address(Integer id, String address, String city, String country, String province, String postalCode, boolean defaultAddress) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.country = country;
        this.province = province;
        this.postalCode = postalCode;
        this.defaultAddress = defaultAddress;
    }

    /**
     * Getter del id.
     *
     * @return Integer id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter del id.
     *
     * @param id Integer.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter de la dirección.
     *
     * @return String address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter de la dirección.
     *
     * @param address String.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter de la ciudad.
     *
     * @return String city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter de la ciudad.
     *
     * @param city String.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter del país.
     *
     * @return String país.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter del país.
     *
     * @param country String.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter de la provincia.
     *
     * @return String province.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Setter de la provincia.
     *
     * @param province String.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Getter del código postal.
     *
     * @return String código postal.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setter del código postal.
     *
     * @param postalCode String código postal.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Getter de la longitud.
     *
     * @return Double longitude.
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Setter de la longitud.
     *
     * @param longitude Double.
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter de la latitud.
     *
     * @return Double latitude.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Setter de la latitud.
     *
     * @param latitude Double.
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter del booleano que indica si es la dirección por defecto.
     *
     * @return boolean defaultAddress.
     */
    public boolean getDefaultAddress() {
        return defaultAddress;
    }

    /**
     * Setter del booleano que indica si es la dirección por defecto.
     *
     * @param defaultAddress boolean.
     */
    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return getFullAddress();
    }

    /**
     * Método que devuelve las coordenadas formateadas.
     *
     * @return String coordenadas formateadas.
     */
    public String getCoordinatesForMap() {
        return getLatitude() + "," + getLongitude();
    }

    /**
     * Método que devuelve la dirección completa formateada correctamente.
     *
     * @return String dirección completa.
     */
    public String getFullAddress() {
        return (!"".equals(getAddress()) ? getAddress() + "," : "")
                + (!"".equals(getCity()) ? getCity() + "," : "")
                + (!"".equals(getProvince()) ? getProvince() + "," : "")
                + (!"".equals(getCountry()) ? getCountry() + "," : "")
                + (!"".equals(getPostalCode()) ? getPostalCode() : "");
    }
}
