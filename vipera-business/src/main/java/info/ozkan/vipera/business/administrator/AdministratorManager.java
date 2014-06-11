package info.ozkan.vipera.business.administrator;

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

}
