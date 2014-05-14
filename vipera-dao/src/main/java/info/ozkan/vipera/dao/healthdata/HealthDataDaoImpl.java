package info.ozkan.vipera.dao.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataBrowseFilter;
import info.ozkan.vipera.business.healthdata.HealthDataManagerStatus;
import info.ozkan.vipera.business.healthdata.HealthDataResult;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.Patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * {@link HealthDataDao} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataDaoImpl implements HealthDataDao {
    /**
     * Id ye göre sağlık verisi sorgusu
     */
    private static final String JQL_GET_BY_ID =
            "from HealthData d join fetch d.patient WHERE d.id = :id";
    /**
     * Persisence context
     */
    private EntityManager em;

    public HealthDataResult add(final HealthData healthData) {
        em.persist(healthData);
        return createSuccessResult();
    }

    /**
     * Başarılı değeri içeren bir sonuç nesnesi üretir
     * 
     * @return
     */
    private HealthDataResult createSuccessResult() {
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

    public HealthDataResult find(final HealthDataBrowseFilter filter) {
        final HealthDataResult result;
        final Query query = createQueryFromFilter(filter);
        final List<HealthData> list = query.getResultList();
        result = createSuccessResult();
        result.setHealthDatas(list);
        return result;
    }

    /**
     * filtre nesnesinden jpa sorgusu oluşturur
     * 
     * @param filter
     * @return
     */
    private Query createQueryFromFilter(final HealthDataBrowseFilter filter) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<HealthData> cq = cb.createQuery(HealthData.class);
        final Root<HealthData> root = cq.from(HealthData.class);
        final List<Predicate> predicates = new ArrayList<Predicate>();

        final Patient patient = filter.getPatient();
        final Date startDate = filter.getStartDate();
        final Date endDate = filter.getEndDate();

        if (patient != null) {
            predicates.add(cb.equal(root.get("patient"), patient));
        }
        if (startDate != null && endDate != null) {
            predicates.add(cb.between(root.<Date> get("date"), startDate,
                    endDate));
        }
        final Predicate[] array = predicates.toArray(new Predicate[0]);
        cq.select(root).where(array);
        final Query query = em.createQuery(cq);
        return query;
    }

    public HealthDataResult getById(final Long id) {
        HealthDataResult result;
        final Query query = em.createQuery(JQL_GET_BY_ID);
        query.setParameter("id", id);
        final List<HealthData> resultList = query.getResultList();
        if (resultList.size() != 0) {
            final HealthData data = resultList.get(0);
            result = createSuccessResult();
            result.setHealthData(data);
        } else {
            result = new HealthDataResult();
            result.setStatus(HealthDataManagerStatus.NOT_FOUND);
        }
        return result;
    }
}
