package info.ozkan.vipera.views.administrator;

import info.ozkan.vipera.business.administrator.AdministratorFacade;
import info.ozkan.vipera.business.administrator.AdministratorManagerResult;
import info.ozkan.vipera.common.EmailValidator;
import info.ozkan.vipera.entities.Administrator;
import info.ozkan.vipera.entities.Authorize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private static final String ADDING_FAIL_DETAIL =
            "Yönetici eklenemedi lütfen kullanıcı adı ve eposta alanlarının benzersiz olduğundan emin olun!";
    private static final String ADDING_FAIL = "Yönetici ekleme başarısız!";
    private static final String ADDING_SUCCESS_DETAIL_PATTERN =
            "Yeni yönetici %s (%s) sisteme eklenmiştir!";
    private static final String ADDING_SUCCESS = "Ekleme başarılı!";
    private static final String UPDATE_SUCCESS = "Güncelleme başarılı!";
    private static final String UPDATE_FAIL_DETAIL =
            "Kullanıcı adı ve eposta alanları benzersiz olmalıdır. Lütfen kontrol ediniz!";
    private static final String UPDATE_FAIL = "Güncelleme başarısız!";
    private static final String EMAIL_INVALID_DETAIL =
            "Lütfen girdiğiniz eposta adresini kontrol edin!";
    private static final String EMAIL_INVALID = "Eposta adresi geçersiz!";
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
    private Administrator newAdministrator = new Administrator();
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
    private FacesContext context;

    /**
     * Yöneticileri sistemden yükler
     */
    public void loadData() {
        if (model == null) {
            initializeModel();
        }
    }

    public void update() {
        setPasswordForUpdate();
        setAuthorizationForUpdate();
        final boolean updateAdmin = checkEmailForUpdate();
        if (updateAdmin) {
            updateAdmin();
        }
        initializeModel();
    }

    public void add() {
        context = FacesContext.getCurrentInstance();
        setAuthorizationForAdding();
        final boolean addAdmin = checkNewAdminEmail();
        if (addAdmin) {
            addNewAdmin();
        }
        initializeModel();

    }

    private void addNewAdmin() {
        final AdministratorManagerResult result =
                administratorFacade.add(newAdministrator);
        if (result.isSuccess()) {
            final String detail =
                    String.format(ADDING_SUCCESS_DETAIL_PATTERN,
                            newAdministrator.getUsername(),
                            newAdministrator.getName());
            createSuccessMessage(ADDING_SUCCESS, detail);
            LOGGER.info("The new administrator {} has added to system!",
                    newAdministrator);
            newAdministrator = new Administrator();
            newAdminAuth = true;
        } else {
            createErrorMessage(ADDING_FAIL, ADDING_FAIL_DETAIL);
        }
    }

    private boolean checkNewAdminEmail() {
        boolean addAdmin = true;
        if (!emailValid(newAdministrator.getEmail())) {
            addAdmin = false;
            createErrorMessage(EMAIL_INVALID, EMAIL_INVALID_DETAIL);
        }
        return addAdmin;
    }

    private void setAuthorizationForAdding() {
        if (newAdminAuth) {
            newAdministrator.setEnabled(Authorize.ENABLE);
        } else {
            newAdministrator.setEnabled(Authorize.DISABLE);
        }
    }

    private void updateAdmin() {
        final AdministratorManagerResult result =
                administratorFacade.update(selectedAdmin);
        if (result.isSuccess()) {
            createSuccessMessage(UPDATE_SUCCESS, UPDATE_SUCCESS);
            LOGGER.info("The administrator {} has been updated!", selectedAdmin);
        } else {
            createErrorMessage(UPDATE_FAIL, UPDATE_FAIL_DETAIL);
        }
    }

    private boolean checkEmailForUpdate() {
        boolean updateAdmin = true;
        context = FacesContext.getCurrentInstance();
        if (!emailValid(selectedAdmin.getEmail())) {
            updateAdmin = false;
            createErrorMessage(EMAIL_INVALID, EMAIL_INVALID_DETAIL);
        }
        return updateAdmin;
    }

    private void setAuthorizationForUpdate() {
        if (enabled) {
            selectedAdmin.setEnabled(Authorize.ENABLE);
        } else {
            selectedAdmin.setEnabled(Authorize.DISABLE);
        }
    }

    private void setPasswordForUpdate() {
        if (!password.isEmpty()) {
            selectedAdmin.setPassword(password);
        }
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
        final Authorize enabled = selectedAdmin.getEnabled();
        this.enabled = enabled.equals(Authorize.ENABLE) ? true : false;
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

    /**
     * @return the newAdministrator
     */
    public Administrator getNewAdministrator() {
        return newAdministrator;
    }

    /**
     * Modeli ilklendirir
     */
    private void initializeModel() {
        final AdministratorManagerResult result = administratorFacade.getAll();
        final List<Administrator> dataList = result.getAdministrators();
        model = new AdministratorModel(createMapFromList(dataList), dataList);
    }

    /**
     * Model için Listeden Map üretir
     * 
     * @param dataList
     * @return
     */
    private Map<Long, Administrator> createMapFromList(
            final List<Administrator> dataList) {
        final Map<Long, Administrator> map = new HashMap<Long, Administrator>();
        for (final Administrator admin : dataList) {
            map.put(admin.getId(), admin);
        }
        return map;
    }

    private void
            createSuccessMessage(final String summary, final String detail) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                summary, detail));
    }

    private void createErrorMessage(final String summary, final String detail) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                summary, detail));
    }

    private boolean emailValid(final String email) {
        return EmailValidator.isValid(email);
    }

}
