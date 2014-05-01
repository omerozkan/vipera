package info.ozkan.vipera.dao.patient;

import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientManagerStatus;
import info.ozkan.vipera.entities.Patient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Hastalar üzerinde CRUD işlemi yapan Dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class PatientDaoImpl implements PatientDao {
    /**
     * Persistence context
     */
    private EntityManager em;

    public PatientManagerResult add(final Patient patient) {
        final PatientManagerResult result = new PatientManagerResult();
        em.persist(patient);
        result.setStatus(PatientManagerStatus.SUCCESS);
        return result;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return em;
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
