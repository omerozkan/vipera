package info.ozkan.vipera.views.administrator;

import info.ozkan.vipera.entities.Administrator;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Yönetici oturum bilgisi
 * 
 * @author Ömer Özkan
 * 
 */
@Named("adminSession")
@Scope("session")
public class AdminSessionBean {
    /**
     * Yönetici
     */
    private static Administrator administrator;

    /**
     * setup
     */
    @PostConstruct
    public void setUp() {
        getSession();
    }

    /**
     * Oturum açmış olan yöneticiyi set eder
     */
    private static void getSession() {
        final Authentication obj =
                SecurityContextHolder.getContext().getAuthentication();
        final Object admin = obj.getPrincipal();
        if (admin instanceof Administrator) {
            administrator = (Administrator) admin;
        }
    }

    /**
     * Kullanıcı adı
     * 
     * @return
     */
    public String getUsername() {
        return getAdministrator().getUsername();
    }

    /**
     * @return the administrator
     */
    public static Administrator getAdministrator() {
        if (administrator == null) {
            getSession();
        }
        return administrator;
    }

}
