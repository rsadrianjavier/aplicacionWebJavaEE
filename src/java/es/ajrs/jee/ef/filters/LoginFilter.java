/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ajrs.jee.ef.filters;

import es.ajrs.jee.ef.controllers.beans.LoginController;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PerlaNegra
 */
@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    
    @Inject
    private LoginController lg;
    private FilterConfig filterConfig;
    

    /**
     * Método de inicialización del filtro
     * 
     * @param filterConfig FilterCOnfig objeto con la configuracion del filtro.
     * @throws javax.servlet.ServletException Excepción lanzada en caso de fallo en el
     * servlet.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException  {        
        this.filterConfig = filterConfig;

    }
    /**
     * Método que implementa el filtrado operando sobre las peticiones y 
     * respuestas.
     * 
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String uri = ((HttpServletRequest) request).getRequestURI();
        String contextPath = ((HttpServletRequest) request).getContextPath();
        
        if (!lg.isAdministrador() && uri.contains("/administrador/")) {
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/error/401.xhtml");   
        }
        if (!lg.isNegocio() && uri.contains("/negocio/")) {
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/error/401.xhtml");
        }
        if (!lg.isCliente() && uri.contains("/cliente/")) {
            ((HttpServletResponse) response).sendRedirect(contextPath + "/faces/error/401.xhtml");
        } 
        chain.doFilter(request, response);        
    }
    
        /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Return the filter configuration object for this filter.
     * @return 
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
}
