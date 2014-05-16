package info.ozkan.vipera.views.login;

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
 * Hasta giriş ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientLogin")
@Scope("request")
public class PatientLoginBean {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PatientLoginBean.class);

    /**
     * Giriş başarısız mesajı
     */
    private static final String LOGIN_FAILED_MSG =
            "Girdiğiniz TCKN veya parola yanlış. Lütfen girdiğiniz bilgileri kontrol ediniz";
    /**
     * giriş başarısız mesaj başlığı
     */
    private static final String LOGIN_FAILED = "Giriş başarısız!";
    /**
     * giriş sayfası
     */
    private static final String INDEX_PAGE =
            "/hasta/index.html?faces-redirect=true";
    /**
     * login sayfası
     */
    private static final String LOGIN_PAGE = "/index.html";
    /**
     * TCKN
     */
    private Long tckn;

    /**
     * Parola
     */
    private String password;

    /**
     * Giriş başarılı mı
     */
    private boolean isSuccess;

    /**
     * yetkilendirme nesnesi
     */
    @Inject
    private AuthenticationManager patientAuthManager;

    /**
     * giriş başarılı ise anasayfaya yönlendirir
     * 
     * @return
     */
    public String login() {
        return isSuccess ? INDEX_PAGE : LOGIN_PAGE;
    }

    /**
     * Login işlemini gerçekleştirir
     * 
     * @param ae
     */
    public void login(final ActionEvent ae) {
        final FacesContext context = FacesContext.getCurrentInstance();
        isSuccess = false;
        try {
            final Authentication request =
                    new UsernamePasswordAuthenticationToken(tckn, password);
            final Authentication result =
                    patientAuthManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            isSuccess = true;
            LOGGER.info("{} has login to patient panel", tckn);
        } catch (final AuthenticationException e) {
            context.addMessage(null, new FacesMessage(LOGIN_FAILED,
                    LOGIN_FAILED_MSG));
            LOGGER.info("The user has failed to login patient panel!");
        }
    }

    /**
     * @return the tckn
     */
    public Long getTckn() {
        return tckn;
    }

    /**
     * @param tckn
     *            the tckn to set
     */
    public void setTckn(final Long tckn) {
        this.tckn = tckn;
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
