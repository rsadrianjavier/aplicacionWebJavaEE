package es.ajrs.jee.ef.controllers;

import es.ajrs.jee.ef.model.entities.RolUsuario;
import es.ajrs.jee.ef.controllers.util.JsfUtil;
import es.ajrs.jee.ef.controllers.util.JsfUtil.PersistAction;
import es.ajrs.jee.ef.model.facades.RolUsuarioFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author PerlaNegra
 */
@ManagedBean(name = "rolUsuarioController")
@SessionScoped
public class RolUsuarioController implements Serializable {

    @EJB
    private es.ajrs.jee.ef.model.facades.RolUsuarioFacade ejbFacade;
    private List<RolUsuario> items = null;
    private RolUsuario selected;

    /**
     *
     */
    public RolUsuarioController() {
    }

    /**
     *
     * @return
     */
    public RolUsuario getSelected() {
        return selected;
    }

    /**
     *
     * @param selected
     */
    public void setSelected(RolUsuario selected) {
        this.selected = selected;
    }

    /**
     *
     */
    protected void setEmbeddableKeys() {
    }

    /**
     *
     */
    protected void initializeEmbeddableKey() {
    }

    private RolUsuarioFacade getFacade() {
        return ejbFacade;
    }

    /**
     *
     * @return
     */
    public RolUsuario prepareCreate() {
        selected = new RolUsuario();
        initializeEmbeddableKey();
        return selected;
    }

    /**
     *
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("RolUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     *
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("RolUsuarioUpdated"));
    }

    /**
     *
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("RolUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     *
     * @return
     */
    public List<RolUsuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     *
     * @return
     */
    public List<RolUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    /**
     *
     * @return
     */
    public List<RolUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     *
     */
    @FacesConverter(forClass = RolUsuario.class)
    public static class RolUsuarioControllerConverter implements Converter {

        /**
         *
         * @param facesContext
         * @param component
         * @param value
         * @return
         */
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolUsuarioController controller = (RolUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolUsuarioController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        /**
         *
         * @param facesContext
         * @param component
         * @param object
         * @return
         */
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof RolUsuario) {
                RolUsuario o = (RolUsuario) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RolUsuario.class.getName()});
                return null;
            }
        }

    }

}
