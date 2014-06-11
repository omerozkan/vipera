package info.ozkan.vipera.business.administrator;

import info.ozkan.vipera.entities.Administrator;

/**
 * Yöneticiler üzerinde işlem yapar
 * 
 * @author Ömer Özkan
 * 
 */
public interface AdministratorManager {

    /**
     * Sistemde kayıtlı olan tüm yöneticileri dönderir
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

    /**
     * Sistemde kayıtlı bir yöneticiyi siler
     * 
     * @param administrator
     * @return
     */
    AdministratorManagerResult delete(Administrator administrator);

}
