package info.ozkan.vipera.views.login;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Yönetici login ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("adminLogin")
@Scope("session")
public class AdministratorLoginBean implements Serializable {
    /**
     * Serial
     */
    private static final long serialVersionUID = -7727352294738992023L;
    /**
     * Logging
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AdministratorLoginBean.class);
    /**
     * Login sayfası
     */
    protected static final String LOGIN_PAGE = "login";
    /**
     * Panel ana sayfa
     */
    protected static final String INDEX_PAGE = "index?faces-redirect=true";
    /**
     * Mesaj başlığı
     */
    private static final String MESSAGE_TITLE = "Uyarı";
    /**
     * Alanların boş olduğu durumda gösterilecek olan hata mesajı
     */
    public static final String EMPTY_FIELD_MESSAGE =
            "Lütfen kullanıcı adınızı ve parolanızı giriniz!";
    /**
     * Girilen bilgilerin geçersiz olduğu durumda gösterilecek olan hata mesajı
     */
    public static final String INVALID_LOGIN_MESSAGE =
            "Geçersiz kullanıcı adı ve parola girdiniz."
                    + "Lütfen bilgilerinizi kontrol ederek tekrar deneyin!";
    /**
     * {@link AdministratorLoginBean#INVALID_LOGIN_MESSAGE} mesajını içeren
     * FacesMessage nesnesi
     */
    protected static final FacesMessage INVALID_LOGIN = new FacesMessage(
            MESSAGE_TITLE, INVALID_LOGIN_MESSAGE);
    /**
     * {@link AdministratorLoginBean#EMPTY_FIELD_MESSAGE} mesajını içeren
     * FacesMessage nesnesi
     */
    protected static final FacesMessage EMPTY_FIELD = new FacesMessage(
            MESSAGE_TITLE, EMPTY_FIELD_MESSAGE);

    /**
     * Kullanıcı adı
     */
    private String username;
    /**
     * Parola
     */
    private String password;
    /**
     * Business katmanı nesnesi
     */
    @Inject
    private AuthenticationManager adminAuthManager;
    /**
     * Kullanıcı giriş işlemi başarılı mı?
     */
    private boolean isSuccess;

    /**
     * Login işlemi sonucunda login sayfasına veya yönetici anasayfasına
     * yönlendirilmesini sağlar
     * 
     * @return yeni sayfa
     */
    public String login() {

        return isSuccess ? INDEX_PAGE : LOGIN_PAGE;
    }

    /**
     * Girilen bilgileri kontrol eder gerektiğinde hata mesajı gösterir
     * 
     * @param ae
     */
    public void login(final ActionEvent ae) {
        final FacesContext context = FacesContext.getCurrentInstance();
        isSuccess = false;
        if (getUsername().isEmpty() || getPassword().isEmpty()) {
            context.addMessage(null, EMPTY_FIELD);
            return;
        }
        try {
            final Authentication request =
                    new UsernamePasswordAuthenticationToken(getUsername(),
                            getPassword());
            final Authentication result =
                    adminAuthManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            isSuccess = true;
            LOGGER.info("{} has login to admin panel", getUsername());
        } catch (final AuthenticationException e) {
            LOGGER.info("The user has failed to login admin panel!");
            context.addMessage(null, INVALID_LOGIN);
        }
    }

    /**
     * inject AuthenticationManager
     * 
     * @param adminAuthManager
     */
    public void
            setAdminAuthManager(final AuthenticationManager adminAuthManager) {
        this.adminAuthManager = adminAuthManager;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
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
