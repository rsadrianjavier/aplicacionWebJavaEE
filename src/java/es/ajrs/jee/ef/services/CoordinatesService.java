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
package es.ajrs.jee.ef.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Clase que va a realizar las consultas al API de Google Maps.
 *
 * @author Juan José Hernández Alonso
 */
public class CoordinatesService {

    private static final String GEOCODER_REQUEST_PREFIX_FOR_XML = "http://maps.google.com/maps/api/geocode/xml";

    /**
     * Método que devuelve un array de doubles con las coordenadas.
     *
     * @param address String dirección completa de búsqueda.
     * @return double[] coordinates [lat/long].
     */
    public double[] getLatitudeLongitude(String address) {
        //Mostramos la dirección completa formateada por consola.
        System.err.println("Address: " + address);
        //Inicialización de variables.
        double[] coordinates = new double[2];
        HttpURLConnection conn = null;
        NodeList resultNodeList;
        Document geocoderResultDocument;
        try {
            //Montamos la URL de consulta al API de google maps.
            URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=false");
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());
            //Obtenemos el resultado de la petición.
            geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
            //Utilizamos XPath para poder analizar ese resultado de una forma 
            //sencilla.
            XPath xpath = XPathFactory.newInstance().newXPath();
            //Buscamos los contenidos concretos que necesitamos.
            resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/geometry/location/*", geocoderResultDocument, XPathConstants.NODESET);
            float lat = Float.NaN;
            float lng = Float.NaN;
            //Recorremos los resultados resultantes.
            for (int i = 0; i < resultNodeList.getLength(); ++i) {
                Node node = resultNodeList.item(i);
                //Mostramos los nodos y los valores por consola.
                System.out.println("Node: " + node.getNodeName() + " Value: " + node.getTextContent());
                //Recuperamos la latitud.
                if ("lat".equals(node.getNodeName())) {
                    lat = Float.parseFloat(node.getTextContent());
                }
                //Recuperamos la longitud
                if ("lng".equals(node.getNodeName())) {
                    lng = Float.parseFloat(node.getTextContent());
                }
            }
            /**
             * Se presume que solo obtendremos un resultado y será el último y
             * único el que almacenemos. Podríamos crear un array con todos los 
             * resultados en caso de que hubiese más de una coincidencia.
             */
            coordinates[0] = lat;
            coordinates[1] = lng;
            //Mostramos todo de nuevo por consola.
            System.out.println("Address: " + address + " lat/lng=" + lat + "," + lng);
        } catch (IOException | SAXException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(CoordinatesService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return coordinates;
    }

    /**
     * Método que recupera una dirección dadas unas coordenadas. El proceso
     * es muy similar al anterior.
     *
     * @param latitude Double latitud.
     * @param longitude Double longitud.
     * @return String dirección completa y sin tratar.
     */
    public String getAddress(Double latitude, Double longitude) {
        String address = null;
        HttpURLConnection conn = null;
        NodeList resultNodeList;
        Document geocoderResultDocument;
        try {
            URL url = new URL(GEOCODER_REQUEST_PREFIX_FOR_XML + "?latlng=" + latitude + "," + longitude + "&sensor=false");
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputSource geocoderResultInputSource = new InputSource(conn.getInputStream());
            geocoderResultDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(geocoderResultInputSource);
            XPath xpath = XPathFactory.newInstance().newXPath();
            resultNodeList = (NodeList) xpath.evaluate("/GeocodeResponse/result[1]/*", geocoderResultDocument, XPathConstants.NODESET);
            for (int i = 0; i < resultNodeList.getLength(); ++i) {
                Node node = resultNodeList.item(i);
                if ("formatted_address".equals(node.getNodeName())) {
                    address = (node.getTextContent());
                    System.err.println("Dirección: " + address + "[" + latitude + "," + longitude + "]");
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException | XPathExpressionException ex) {
            Logger.getLogger(CoordinatesService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return address;
    }
}
