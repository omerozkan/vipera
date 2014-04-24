package info.ozkan.vipera.dao.doctor;

import info.ozkan.vipera.business.doctor.DoctorManagerError;
import info.ozkan.vipera.entities.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Hekim veritabanı üzerinde işlem yapan Dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorDao")
public class DoctorDaoImpl implements DoctorDao {
    protected static final String JQL_GET_BY_TCKN = "from Doctor d where d.tckn = :tckn";
    /**
     * Persistence nesne
     */
    private EntityManager em;

    /*
     * (non-Javadoc)
     * 
     * @see info.ozkan.vipera.dao.doctor.DoctorDao#add(info.ozkan.vipera
     * .entities.Doctor)
     */
    public DoctorDaoResult add(final Doctor doctor) {
        final DoctorDaoResult result = new DoctorDaoResult();
        em.persist(doctor);
        result.setSuccess(true);
        return result;
    }

    /**
     * Persistence nesne
     * 
     * @param em
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager em) {
        this.em = em;
    }

    /**
     * Veritabanından TCKN'ye ait hekimi sorgular
     * 
     * @param tckn
     *            TC Kimlik No
     * @return Doctor nesnesi
     */
    public DoctorDaoResult getByTckn(final Long tckn) {
        final Query query = em.createQuery(JQL_GET_BY_TCKN);
        query.setParameter(Doctor.TCKN, tckn);
        final DoctorDaoResult result = new DoctorDaoResult();
        final List resultList = query.getResultList();
        if (resultList.size() == 0) {
            result.setSuccess(false);
            result.setError(DoctorManagerError.DOCTOR_NOT_EXIST);
        } else {
            result.setSuccess(true);
            result.setDoctor((Doctor) resultList.get(0));
        }
        return result;
    }

    /**
     * Filtrelere göre veritabanında arama işlemi yapar
     */
    public List<Doctor> find(final DoctorBrowseFilter filter) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
        final Root<Doctor> root = cq.from(Doctor.class);
        final Map<String, Object> filters = filter.getFilters();
        final List<Predicate> predicates = new ArrayList<Predicate>();
        for (final String attr : filters.keySet()) {
            final Object obj = filters.get(attr);
            if (obj != null && !obj.toString().isEmpty()) {
                final String pattern = '%' + obj.toString() + '%';
                predicates.add(cb.like(
                        root.<String> get(attr).as(String.class), pattern));
            }
        }
        cq.select(root).where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }

}