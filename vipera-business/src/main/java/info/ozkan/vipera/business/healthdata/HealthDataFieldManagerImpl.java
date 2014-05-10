package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.healthdata.HealthDataFieldDao;
import info.ozkan.vipera.entities.HealthDataField;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

/**
 * Sağlık alanları üzerinde CRUD işlemleri yapan Manager sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataFieldManager")
@Transactional
@RolesAllowed(Role.ROLE_ADMIN)
public class HealthDataFieldManagerImpl implements HealthDataFieldManager {
    /**
     * dao katmanı nesnesi
     */
    @Inject
    private HealthDataFieldDao healthDataFieldDao;

    public HealthDataFieldResult add(final HealthDataField field) {
        return healthDataFieldDao.add(field);
    }

    public HealthDataFieldResult getFields() {
        return healthDataFieldDao.getAllFields();
    }

    public HealthDataFieldResult update(final HealthDataField field) {
        return healthDataFieldDao.update(field);
    }

    public HealthDataFieldResult remove(final HealthDataField field) {
        return healthDataFieldDao.remove(field);
    }

}
