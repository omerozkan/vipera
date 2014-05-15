package info.ozkan.vipera.dao.patient;

import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientManagerStatus;
import info.ozkan.vipera.business.patient.PatientSearchFilter;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Hastalar üzerinde CRUD işlemi yapan Dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientDao")
public class PatientDaoImpl implements PatientDao {
    /**
     * ID ve hekime göre hasta sorgulayan JQL sorgusu
     */
    private static final String JQL_GET_PATIENT_BY_ID_AND_DOCTOR =
            "from Patient p join fetch p.doctors d WHERE p.id = :id AND d = :doctor";
    /**
     * ID ye göre hasta sorgulayan JQL sorgusu
     */
    private static final String JQL_GET_BY_ID =
            "from Patient p WHERE p.id = :id";
    /**
     * Persistence context
     */
    private EntityManager em;

    public PatientManagerResult add(final Patient patient) {
        em.persist(patient);
        return createSuccesResult(patient);
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
        final List<Predicate> predicates =
                createPredicationsFromFilter(filters, cb, root);
        cq.select(root).where(predicates.toArray(new Predicate[0]));
        return cq;
    }

    /**
     * Filtreleri CriteriaQuery nesnesine dönüştürür
     * 
     * @param filter
     * @param doctor
     * @return
     */
    private CriteriaQuery<Patient> createCriteriaFromFilter(
            final PatientSearchFilter filter, final Doctor doctor) {
        final Map<String, Object> filters = filter.getFilters();
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
        final Root<Patient> root = cq.from(Patient.class);
        final Path<Doctor> path = root.join("doctors");
        final List<Predicate> predicates =
                createPredicationsFromFilter(filters, cb, root);
        predicates.add(cb.equal(path, doctor));
        cq.select(root).where(predicates.toArray(new Predicate[0]));
        return cq;
    }

    /**
     * arama filtresinden predicate listesi dönderir
     * 
     * @param filters
     * @param cb
     * @param root
     * @return
     */
    private List<Predicate> createPredicationsFromFilter(
            final Map<String, Object> filters, final CriteriaBuilder cb,
            final Root<Patient> root) {
        final List<Predicate> predicates = new ArrayList<Predicate>();
        for (final String attr : filters.keySet()) {
            final Object obj = filters.get(attr);
            final String pattern = '%' + obj.toString() + '%';
            predicates.add(cb.like(root.<String> get(attr).as(String.class),
                    pattern));
        }
        return predicates;
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
     * Veritabanından dönen listeye göre bir {@link PatientManagerResult}
     * nesnesi üretir
     * 
     * @param resultList
     * @return
     */
    private PatientManagerResult createResult(final List<Patient> resultList) {
        final PatientManagerResult result = new PatientManagerResult();
        if (patientExist(resultList)) {
            result.setStatus(PatientManagerStatus.NOT_FOUND);
        } else {
            final Patient patient = resultList.get(0);
            result.setPatient(patient);
            result.setStatus(PatientManagerStatus.SUCCESS);
        }
        return result;
    }

    /**
     * Başarılı bir sonuç içeren {@link PatientManagerResult} nesnesi üretir
     * 
     * @param patient
     *            Hasta
     * @return
     */
    private PatientManagerResult createSuccesResult(final Patient patient) {
        final PatientManagerResult result = new PatientManagerResult();
        result.setStatus(PatientManagerStatus.SUCCESS);
        result.setPatient(patient);
        return result;
    }

    public PatientManagerResult delete(final Patient patient) {
        final PatientManagerResult getResult = getById(patient.getId());
        if (userNotFound(getResult)) {
            return getResult;
        }
        final Patient tempPatient = getResult.getPatient();
        em.remove(tempPatient);
        final PatientManagerResult result = new PatientManagerResult();
        result.setStatus(PatientManagerStatus.SUCCESS);
        return result;
    }

    /**
     * Kullanıcı veritabanında kayıtlı değilse true dönderir
     * 
     * @param getResult
     * @return
     */
    private boolean userNotFound(final PatientManagerResult getResult) {
        final PatientManagerStatus status = getResult.getStatus();
        return status.equals(PatientManagerStatus.NOT_FOUND);
    }

    public PatientManagerResult find(final PatientSearchFilter filter) {
        final CriteriaQuery<Patient> cq = createCriteriaFromFilter(filter);
        final PatientManagerResult result = createManagerResultFromCriteria(cq);
        return result;
    }

    public PatientManagerResult find(final PatientSearchFilter filter,
            final Doctor doctor) {
        final CriteriaQuery<Patient> cq =
                createCriteriaFromFilter(filter, doctor);
        final PatientManagerResult result = createManagerResultFromCriteria(cq);
        return result;
    }

    public PatientManagerResult getById(final Long id) {
        final Query query = em.createQuery(JQL_GET_BY_ID);
        query.setParameter("id", id);
        final List<Patient> resultList = query.getResultList();
        return createResult(resultList);
    }

    /**
     * Kayıtlı herhani bir hasta var olup olmadığını kontrol eder
     * 
     * @param resultList
     * @return
     */
    private boolean patientExist(final List<Patient> resultList) {
        return resultList.size() == 0;
    }

    /**
     * @param entityManager
     *            the entityManager to set
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        em = entityManager;
    }

    public PatientManagerResult update(final Patient patient) {
        em.merge(patient);
        return createSuccesResult(patient);
    }

    public PatientManagerResult getById(final Long id, final Doctor doctor) {
        final PatientManagerResult result;
        final Query query = em.createQuery(JQL_GET_PATIENT_BY_ID_AND_DOCTOR);
        query.setParameter("id", id);
        query.setParameter("doctor", doctor);
        final List<Patient> list = query.getResultList();
        if (list.size() != 0) {
            final Patient patient = list.get(0);
            result = createSuccesResult(patient);
        } else {
            result = new PatientManagerResult();
            result.setStatus(PatientManagerStatus.NOT_FOUND);
        }
        return result;
    }

}
