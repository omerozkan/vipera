package info.ozkan.vipera.dao.patient;

import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientManagerStatus;
import info.ozkan.vipera.business.patient.PatientSearchFilter;
import info.ozkan.vipera.entities.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

    public PatientManagerResult find(final PatientSearchFilter filter) {
        final CriteriaQuery<Patient> cq = createCriteriaFromFilter(filter);
        final PatientManagerResult result = createManagerResultFromCriteria(cq);
        return result;
    }

    /**
     * CriteriaQuery ile veritabanından arama işlemi yaparak alınan sonucu sonuç
     * nesnesine çevirir
     * 
     * @param cq
     * @return
     */
    private PatientManagerResult createManagerResultFromCriteria(
            final CriteriaQuery<Patient> cq) {
        final PatientManagerResult result = new PatientManagerResult();
        result.setStatus(PatientManagerStatus.SUCCESS);
        final Query query = em.createQuery(cq);
        result.setPatientList(query.getResultList());
        return result;
    }

    /**
     * Filtreleri CriteriaQuery'ye çevirir
     * 
     * @param filters
     * @return
     */
    private CriteriaQuery<Patient> createCriteriaFromFilter(
            final PatientSearchFilter filter) {
        final Map<String, Object> filters = filter.getFilters();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
        final Root<Patient> root = cq.from(Patient.class);
        final List<Predicate> predicates = new ArrayList<Predicate>();
        for (final String attr : filters.keySet()) {
            final Object obj = filters.get(attr);
            final String pattern = '%' + obj.toString() + '%';
            predicates.add(cb.like(root.<String> get(attr).as(String.class),
                    pattern));
        }
        cq.select(root).where(predicates.toArray(new Predicate[0]));
        return cq;
    }

}
