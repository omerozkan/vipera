package info.ozkan.vipera.business.administrator;

import info.ozkan.vipera.entities.Administrator;

/**
 * Yöneticiler üzerinde CRUD işlemi yapan Facade sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface AdministratorFacade {

    /**
     * Sistemde kayıtlı bütün yöneticileri dönderir
     * 
     * @return
     */
    AdministratorManagerResult getAll();

    /**
     * Sistemde kayıtlı olan bir yöneticiyi günceller
     * 
     * @param administrator
     * @return
     */
    AdministratorManagerResult update(Administrator administrator);

    /**
     * Sisteme yeni bir yönetici ekler
     * 
     * @param administrator
     * @return
     */
    AdministratorManagerResult add(Administrator administrator);

    AdministratorManagerResult delete(Administrator selectedAdmin);

}
