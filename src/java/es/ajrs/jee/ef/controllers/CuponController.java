package es.ajrs.jee.ef.controllers;

import es.ajrs.jee.ef.model.entities.Cupon;
import es.ajrs.jee.ef.controllers.util.JsfUtil;
import es.ajrs.jee.ef.controllers.util.JsfUtil.PersistAction;
import es.ajrs.jee.ef.model.facades.CuponFacade;

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
@ManagedBean(name = "cuponController")
@SessionScoped
public class CuponController implements Serializable {

    @EJB
    private es.ajrs.jee.ef.model.facades.CuponFacade ejbFacade;
    private List<Cupon> items = null;
    private Cupon selected;

    /**
     *
     */
    public CuponController() {
    }

    /**
     *
     * @return
     */
    public Cupon getSelected() {
        return selected;
    }

    /**
     *
     * @param selected
     */
    public void setSelected(Cupon selected) {
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

    private CuponFacade getFacade() {
        return ejbFacade;
    }

    /**
     *
     * @return
     */
    public Cupon prepareCreate() {
        selected = new Cupon();
        initializeEmbeddableKey();
        return selected;
    }

    /**
     *
     */
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("CuponCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     *
     */
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("CuponUpdated"));
    }

    /**
     *
     */
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/es/ajrs/jee/ef/es_Bundle").getString("CuponDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /**
     *
     * @return
     */
    public List<Cupon> getItems() {
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
    public List<Cupon> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    /**
     *
     * @return
     */
    public List<Cupon> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     *
     */
    @FacesConverter(forClass = Cupon.class)
    public static class CuponControllerConverter implements Converter {

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
            CuponController controller = (CuponController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuponController");
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
            if (object instanceof Cupon) {
                Cupon o = (Cupon) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cupon.class.getName()});
                return null;
            }
        }

    }

}
