package info.ozkan.vipera.business.administrator;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.administrator.AdministratorDao;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

/**
 * {@link AdministratorManager} arayüzü implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorManagerImpl implements AdministratorManager {
    /**
     * Dao
     */
    @Inject
    private AdministratorDao administratorDao;

    @Transactional
    @RolesAllowed(Role.ROLE_ADMIN)
    public AdministratorManagerResult getAll() {
        return administratorDao.getAll();
    }

}
