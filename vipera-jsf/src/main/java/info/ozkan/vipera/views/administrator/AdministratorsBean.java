package info.ozkan.vipera.views.administrator;

import info.ozkan.vipera.business.administrator.AdministratorFacade;
import info.ozkan.vipera.business.administrator.AdministratorManagerResult;
import info.ozkan.vipera.entities.Administrator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

/**
 * Yöneticiler ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("administrators")
@Scope("session")
public class AdministratorsBean implements Serializable {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AdministratorsBean.class);
    /**
     * Serial
     */
    private static final long serialVersionUID = 7128479501306753780L;
    /**
     * Yeni eklenecek yönetici
     */
    private final Administrator newAdministrator = new Administrator();
    /**
     * Veri modeli
     */
    private AdministratorModel model;
    /**
     * Seçilen yönetici
     */
    private Administrator selectedAdmin;

    /**
     * Parola
     */
    private String password;
    /**
     * parola tekrarı
     */
    private String password2;
    /**
     * Üye aktifliği
     */
    private boolean enabled;
    /**
     * Yeni yöneticinin üyelik aktifliği
     */
    private boolean newAdminAuth = true;
    /**
     * business object
     */
    @Inject
    private AdministratorFacade administratorFacade;

    /**
     * @return the newAdministrator
     */
    public Administrator getNewAdministrator() {
        return newAdministrator;
    }

    /**
     * @return the model
     */
    public AdministratorModel getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(final AdministratorModel model) {
        this.model = model;
    }

    /**
     * @return the selectedAdmin
     */
    public Administrator getSelectedAdmin() {
        return selectedAdmin;
    }

    /**
     * @param selectedAdmin
     *            the selectedAdmin to set
     */
    public void setSelectedAdmin(final Administrator selectedAdmin) {
        this.selectedAdmin = selectedAdmin;
    }

    public void loadData() {
        if (model == null) {
            initializeModel();
        }
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2
     *            the password2 to set
     */
    public void setPassword2(final String password2) {
        this.password2 = password2;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    private void initializeModel() {
        final AdministratorManagerResult result = administratorFacade.getAll();
        final List<Administrator> dataList = result.getAdministrators();
        model = new AdministratorModel(createMapFromList(dataList), dataList);
    }

    private Map<Long, Administrator> createMapFromList(
            final List<Administrator> dataList) {
        final Map<Long, Administrator> map = new HashMap<Long, Administrator>();
        for (final Administrator admin : dataList) {
            map.put(admin.getId(), admin);
        }
        return map;
    }

    /**
     * @return the newAdminAuth
     */
    public boolean isNewAdminAuth() {
        return newAdminAuth;
    }

    /**
     * @param newAdminAuth
     *            the newAdminAuth to set
     */
    public void setNewAdminAuth(final boolean newAdminAuth) {
        this.newAdminAuth = newAdminAuth;
    }
}
