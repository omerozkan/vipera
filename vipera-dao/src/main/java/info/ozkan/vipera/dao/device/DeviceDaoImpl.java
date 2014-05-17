package info.ozkan.vipera.dao.device;

import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.business.device.DeviceManagerSearchFilter;
import info.ozkan.vipera.business.device.DeviceManagerStatus;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link DeviceDao} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("deviceDao")
public class DeviceDaoImpl implements DeviceDao {
    /**
     * Cihaz arama JQL sorgusu
     */
    private static final String JQL_FIND_DEVICE =
            "from Device d where d.apiKey = :apiKey AND d.apiPassword = :apiPassword";
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DeviceDaoImpl.class);
    /**
     * EntityManager
     */
    private EntityManager em;

    public DeviceManagerResult add(final Device device) {
        final Patient patient;
        patient = getPatient(device);
        device.setPatient(patient);
        em.persist(device);
        final DeviceManagerResult result = createSuccessResult();
        result.setDevice(device);
        return result;
    }

    private Patient getPatient(final Device device) {
        final Patient patient = device.getPatient();
        return em.find(Patient.class, patient.getId());
    }

    public DeviceManagerResult find(final DeviceManagerSearchFilter filter) {
        final Query query = filterToCriteriaQuery(filter);
        final List<Device> devices = query.getResultList();
        final DeviceManagerResult result = createSuccessResult();
        result.setDevices(devices);
        return result;
    }

    /**
     * Filtreden CriteriaQuery nesnesi üretir
     * 
     * @param filter
     * @return
     */
    private Query filterToCriteriaQuery(final DeviceManagerSearchFilter filter) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Device> criteriaQuery =
                cb.createQuery(Device.class);
        final Root<Device> root = criteriaQuery.from(Device.class);
        final List<Predicate> predicates = new ArrayList<Predicate>();
        final String apiKey = filter.getApiKey();
        final Patient patient = filter.getPatient();

        if (patient != null) {
            predicates.add(cb.equal(root.get("patient"), patient));
        }

        if (apiKey != null && !apiKey.isEmpty()) {
            final String pattern = '%' + apiKey + '%';
            predicates.add(cb.like(root.<String> get("apiKey"), pattern));
        }

        final Predicate[] array = predicates.toArray(new Predicate[0]);
        criteriaQuery.select(root).where(array);
        final Query query = em.createQuery(criteriaQuery);

        return query;
    }

    /**
     * Başarılı mesajı içeren {@link DeviceManagerResult} nesnesi üretir
     * 
     * @return
     */
    private DeviceManagerResult createSuccessResult() {
        final DeviceManagerResult result = new DeviceManagerResult();
        result.setStatus(DeviceManagerStatus.SUCCESS);
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

    public DeviceManagerResult delete(final Long deviceId) {
        final Device device = em.find(Device.class, deviceId);
        LOGGER.info("The device {} has been deleted", device.getApiKey());
        em.remove(device);
        return createSuccessResult();
    }

    public Device getById(final Long id) {
        return em.find(Device.class, id);
    }

    public DeviceManagerResult update(final Device device) {
        em.merge(device);
        final DeviceManagerResult result = createSuccessResult();
        result.setDevice(device);
        return result;
    }

    public DeviceManagerResult findDevice(final String apiKey,
            final String apiPassword) {
        DeviceManagerResult result;
        final Query query = em.createQuery(JQL_FIND_DEVICE);
        query.setParameter("apiKey", apiKey);
        query.setParameter("apiPassword", apiPassword);
        final List<Device> resultList = query.getResultList();
        if (resultList.size() == 0) {
            result = new DeviceManagerResult();
            result.setStatus(DeviceManagerStatus.NOT_FOUND);
        } else {
            result = createSuccessResult();
            result.setDevice(resultList.get(0));
        }
        return result;
    }
}
