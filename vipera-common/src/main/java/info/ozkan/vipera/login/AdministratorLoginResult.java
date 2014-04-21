package info.ozkan.vipera.login;

import info.ozkan.vipera.business.login.AdministratorLoginStatus;
import info.ozkan.vipera.entities.Administrator;

/**
 * Yönetici login işlemi yaptıktan sonra oluşan sonuç sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorLoginResult {
    /**
     * Sonuç
     */
    private AdministratorLoginStatus status;
    /**
     * Yönetici nesnesi
     */
    private Administrator administrator;

    /**
     * @return the status
     */
    public AdministratorLoginStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final AdministratorLoginStatus status) {
        this.status = status;
    }

    /**
     * @return the administrator
     */
    public Administrator getAdministrator() {
        return administrator;
    }

    /**
     * @param administrator
     *            the administrator to set
     */
    public void setAdministrator(final Administrator administrator) {
        this.administrator = administrator;
    }

}
