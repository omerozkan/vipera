package info.ozkan.vipera.dao.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataFieldResult;
import info.ozkan.vipera.business.healthdata.HealthDataFieldStatus;
import info.ozkan.vipera.entities.HealthDataField;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Veritabanı üzerinde sağlık alanları ile ilgili CRUD işlemleri yapan Dao
 * sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataFieldDao")
public class HealthDataFieldDaoImpl implements HealthDataFieldDao {
    /**
     * EntityManager
     */
    private EntityManager em;

    public HealthDataFieldResult add(final HealthDataField field) {
        em.persist(field);
        final HealthDataFieldResult result = createSuccessResult();
        result.setHealthDataField(field);
        return result;
    }

    private HealthDataFieldResult createSuccessResult() {
        final HealthDataFieldResult result = new HealthDataFieldResult();
        result.setStatus(HealthDataFieldStatus.SUCCESS);
        return result;
    }

    public HealthDataFieldResult getAllFields() {
        final Query query = em.createQuery("from HealthDataField f",
                HealthDataField.class);
        final List<HealthDataField> list = query.getResultList();
        final HealthDataFieldResult result = createSuccessResult();
        result.setHealthDataFields(list);
        return result;
    }

    public HealthDataFieldResult update(final HealthDataField field) {
        em.merge(field);
        final HealthDataFieldResult result = createSuccessResult();
        result.setHealthDataField(field);
        return result;
    }

    public HealthDataFieldResult remove(final HealthDataField field) {
        em.remove(em.merge(field));
        return createSuccessResult();
    }

    /**
     * @param entityManager
     *            the entityManager to set
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        em = entityManager;
    }

}
