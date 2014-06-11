package info.ozkan.vipera.business.administrator;

import info.ozkan.vipera.entities.Administrator;

import java.util.List;

/**
 * Yöneticiler üzerinde işlem yapıldığında sonuç olarak kullanılan sınıf
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorManagerResult {
    /**
     * Yöneticiler
     */
    private List<Administrator> administrators;
    /**
     * Statü
     */
    private AdministratorManagerStatus status;

    /**
     * @return the administrators
     */
    public List<Administrator> getAdministrators() {
        return administrators;
    }

    /**
     * @param administrators
     *            the administrators to set
     */
    public void setAdministrators(final List<Administrator> administrators) {
        this.administrators = administrators;
    }

    /**
     * @return the status
     */
    public AdministratorManagerStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final AdministratorManagerStatus status) {
        this.status = status;
    }

}
