package info.ozkan.vipera.dao.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataResult;
import info.ozkan.vipera.business.healthdata.HealthDataManagerStatus;
import info.ozkan.vipera.entities.HealthData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@link HealthDataDao} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataDaoImpl implements HealthDataDao {

    /**
     * Persisence context
     */
    private EntityManager em;

    public HealthDataResult add(final HealthData healthData) {
        em.persist(healthData);
        final HealthDataResult result = new HealthDataResult();
        result.setStatus(HealthDataManagerStatus.SUCCESS);
        return result;
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
