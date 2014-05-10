package info.ozkan.vipera.dao.device;

import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.business.device.DeviceManagerStatus;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@link DeviceDao} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("deviceDao")
public class DeviceDaoImpl implements DeviceDao {
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

}
