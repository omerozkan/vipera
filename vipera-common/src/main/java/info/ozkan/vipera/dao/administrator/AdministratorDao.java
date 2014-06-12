package info.ozkan.vipera.dao.administrator;

import info.ozkan.vipera.business.administrator.AdministratorManagerResult;
import info.ozkan.vipera.entities.Administrator;

/**
 * Veritabanında Yöneticiler üzerinde CRUD işlemleri yapan Dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface AdministratorDao {
    /**
     * Veritabanında kayıtlı olan bütün yöneticileri dönderir
     * 
     * @return
     */
    AdministratorManagerResult getAll();

    /**
     * Veritabanında kayıtlı olan bir yöneticiyi günceller
     * 
     * @param administrator
     * @return
     */
    AdministratorManagerResult update(Administrator administrator);

    /**
     * Veritabanına yeni bir yönetici ekler
     * 
     * @param administrator
     * @return
     */
    AdministratorManagerResult add(Administrator administrator);

    /**
     * Veritabanından bir yöneticiyi siler
     * 
     * @param administrator
     * @return
     */
    AdministratorManagerResult delete(Administrator administrator);

}
