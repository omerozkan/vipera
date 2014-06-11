package info.ozkan.vipera.dao.administrator;

import info.ozkan.vipera.business.administrator.AdministratorManagerResult;

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

}
