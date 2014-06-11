package info.ozkan.vipera.views.administrator;

import info.ozkan.vipera.business.administrator.AdministratorFacade;
import info.ozkan.vipera.business.administrator.AdministratorManagerResult;
import info.ozkan.vipera.common.EmailValidator;
import info.ozkan.vipera.entities.Administrator;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Yönetici hesap güncelleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("account")
@Scope("session")
public class AccountBean implements Serializable {
    /**
     * Alanların diğer kullanıcılar ile aynı olması durumunda gösterilen mesaj
     */
    private static final String NON_UNIQUE_VALUES =
            "Girdiğiniz kullanıcı adı veya eposta adresi bir başka yöneticiye aittir!";
    /**
     * Güncelleme başarısız mesajı
     */
    private static final String UPDATING_FAIL = "Güncelleme hatası!";
    /**
     * Güncelleme başarılı mesajı
     */
    private static final String UPDATED_MSG = "Hesabınız güncellenmiştir!";
    /**
     * Güncellendi mesajı
     */
    private static final String UPDATED = "Güncellendi!";
    /**
     * Geçersiz eposta mesajı
     */
    private static final String INVALID_EMAIL_MSG =
            "Girdiğiniz eposta adresi geçersizdir. Lütfen kontrol ediniz!";
    /**
     * Geçersiz eposta
     */
    private static final String INVALID_EMAIL = "Eposta geçersiz!";
    /**
     * Serial
     */
    private static final long serialVersionUID = 1100917840483548741L;
    /**
     * Yönetici
     */
    private Administrator profile;
    /**
     * Parola
     */
    private String password;

    /**
     * Business object
     */
    @Inject
    private AdministratorFacade administratorFacade;

    /**
     * Oturum açmış yöneticiyi tanımlar
     */
    @PostConstruct
    public void setUp() {
        setProfile(AdminSessionBean.getAdministrator());
    }

    /**
     * Güncelleme işlemi yapar
     */
    public void update() {
        final FacesContext context = FacesContext.getCurrentInstance();
        setAdminPassword();
        if (!isEmailValid(getProfile().getEmail())) {
            createErrorMessage(context, INVALID_EMAIL, INVALID_EMAIL_MSG);
        } else {
            updateAccount(context);
        }
    }

    /**
     * Yönetici parola girdi ise parola ata
     */
    private void setAdminPassword() {
        if (password != null && !password.isEmpty()) {
            profile.setPassword(password);
        }
    }

    /**
     * Hesabı günceller
     * 
     * @param context
     */
    private void updateAccount(final FacesContext context) {
        final AdministratorManagerResult result =
                administratorFacade.update(getProfile());
        if (result.isSuccess()) {
            createInfoMessage(context, UPDATED, UPDATED_MSG);
        } else {
            createErrorMessage(context, UPDATING_FAIL, NON_UNIQUE_VALUES);
        }
    }

    /**
     * bilgi mesajı üretir
     * 
     * @param context
     * @param summary
     * @param detail
     */
    private void createInfoMessage(final FacesContext context,
            final String summary, final String detail) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                summary, detail));
    }

    /**
     * hata mesajı üretir
     * 
     * @param context
     * @param summary
     * @param detail
     */
    private void createErrorMessage(final FacesContext context,
            final String summary, final String detail) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                summary, detail));
    }

    /**
     * eposta adresinin geçerliliğini kontrol eder
     * 
     * @param email
     * @return
     */
    private boolean isEmailValid(final String email) {
        return EmailValidator.isValid(email);
    }

    /**
     * @return the profile
     */
    public Administrator getProfile() {
        return profile;
    }

    /**
     * @param profile
     *            the profile to set
     */
    public void setProfile(final Administrator profile) {
        this.profile = profile;
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

}
