package es.ajrs.jee.ef.controllers;

import es.ajrs.jee.ef.model.entities.DetallesUsuario;
import es.ajrs.jee.ef.controllers.util.JsfUtil;
import es.ajrs.jee.ef.controllers.util.JsfUtil.PersistAction;
import es.ajrs.jee.ef.model.facades.DetallesUsuarioFacade;

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
@ManagedBean(name = "detallesUsuarioController")
@SessionScoped
public class DetallesUsuarioController implements Serializable {

    @EJB
    private es.ajrs.jee.ef.model.facades.DetallesUsuarioFacade ejbFacade;
    private List<DetallesUsuario> items = null;
    private DetallesUsuario selected;

    /**
     *
     */
    public DetallesUsuarioController() {
    }

    /**
     *
     * @return
     */
    public DetallesUsuario getSelected() {
        return selected;
    }

    /**
     *
     * @param selected
     */
    public void setSelected(DetallesUsuario selected) {
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

    private DetallesUsuarioFacade getFacade() {
        return ejbFacade;
    }

    /**
     *
     * @return
     */
    public DetallesUsuario prepareCreate() {
        selected = new DetallesUsuario();
        initializeEmbeddableKey();
        return selected;
    }

    /**
     *
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("DetallesUsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     *
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("DetallesUsuarioUpdated"));
    }

    /**
     *
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("DetallesUsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     *
     * @return
     */
    public List<DetallesUsuario> getItems() {
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
    public List<DetallesUsuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    /**
     *
     * @return
     */
    public List<DetallesUsuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     *
     */
    @FacesConverter(forClass = DetallesUsuario.class)
    public static class DetallesUsuarioControllerConverter implements Converter {

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
            DetallesUsuarioController controller = (DetallesUsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallesUsuarioController");
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
            if (object instanceof DetallesUsuario) {
                DetallesUsuario o = (DetallesUsuario) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetallesUsuario.class.getName()});
                return null;
            }
        }

    }

}
