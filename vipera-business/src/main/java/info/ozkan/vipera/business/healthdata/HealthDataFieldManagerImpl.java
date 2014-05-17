package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.healthdata.HealthDataFieldDao;
import info.ozkan.vipera.entities.HealthDataField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class HealthDataFieldManagerImpl implements HealthDataFieldManager {
    /**
     * dao katmanı nesnesi
     */
    @Inject
    private HealthDataFieldDao healthDataFieldDao;
    /**
     * alanlar
     */
    private Map<String, HealthDataField> fields;

    @RolesAllowed(Role.ROLE_ADMIN)
    public HealthDataFieldResult add(final HealthDataField field) {
        return healthDataFieldDao.add(field);
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    public HealthDataFieldResult getFields() {
        return healthDataFieldDao.getAllFields();
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    public HealthDataFieldResult update(final HealthDataField field) {
        return healthDataFieldDao.update(field);
    }

    @RolesAllowed(Role.ROLE_ADMIN)
    public HealthDataFieldResult remove(final HealthDataField field) {
        return healthDataFieldDao.remove(field);
    }

    public HealthDataField getField(final String key) {
        if (fields == null || !fields.containsKey(key)) {
            createFields();
        }
        return fields.get(key);
    }

    /**
     * Veritabanından alanları alarak map e kaydeder
     */
    private void createFields() {
        fields = new HashMap<String, HealthDataField>();
        final HealthDataFieldResult result = getFields();
        final List<HealthDataField> list = result.getHealthDataFields();
        for (final HealthDataField field : list) {
            fields.put(field.getName(), field);
        }
    }

}
