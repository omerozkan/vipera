package info.ozkan.vipera.business.administrator;

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

}
