package info.ozkan.vipera.dao.notification.android;

import info.ozkan.vipera.business.notification.android.AndroidRegistrationDao;
import info.ozkan.vipera.business.notification.android.AndroidRegistrationResult;
import info.ozkan.vipera.business.notification.android.AndroidRegistrationStatus;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorAndroidDevice;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * {@link AndroidRegistrationDao} implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("androidRegistrationDao")
public class AndroidRegistrationDaoImpl implements AndroidRegistrationDao {
    private static final String JQL_SELECT_BY_DOCTOR =
            "from DoctorAndroidDevice a JOIN FETCH a.doctor WHERE a.doctor = :doctor";
    /**
     * persistence context
     */
    private EntityManager em;

    public AndroidRegistrationResult add(final Doctor doctor,
            final String registrationId) {
        final boolean hasExist = checkHasExist(doctor, registrationId);
        if (!hasExist) {
            final DoctorAndroidDevice device = new DoctorAndroidDevice();
            device.setDoctor(doctor);
            device.setRegistrationId(registrationId);
            em.merge(device);
            return createSuccessResult();
        } else {
            final AndroidRegistrationResult result =
                    new AndroidRegistrationResult();
            result.setStatus(AndroidRegistrationStatus.HAS_EXIST);
            return result;
        }
    }

    private boolean checkHasExist(final Doctor doctor,
            final String registrationId) {
        final Query query =
                em.createQuery("from DoctorAndroidDevice a JOIN FETCH a.doctor WHERE a.doctor = :doctor AND a.registrationId = :registrationId");
        query.setParameter("doctor", doctor);
        query.setParameter("registrationId", registrationId);
        final List<DoctorAndroidDevice> devices = query.getResultList();
        return devices.size() != 0;
    }

    private AndroidRegistrationResult createSuccessResult() {
        final AndroidRegistrationResult result =
                new AndroidRegistrationResult();
        result.setStatus(AndroidRegistrationStatus.SUCCESS);
        return result;
    }

    public AndroidRegistrationResult remove(final Doctor doctor,
            final String registrationId) {
        final DoctorAndroidDevice device =
                em.find(DoctorAndroidDevice.class, registrationId);
        em.remove(device);
        return createSuccessResult();
    }

    public AndroidRegistrationResult findDevices(final Doctor doctor) {
        AndroidRegistrationResult result;
        final Query query = em.createQuery(JQL_SELECT_BY_DOCTOR);
        query.setParameter("doctor", doctor);
        final List<DoctorAndroidDevice> devices = query.getResultList();
        if (devices.size() > 0) {
            result = createSuccessResult();
            result.setDevices(devices);
        } else {
            result = new AndroidRegistrationResult();
            result.setStatus(AndroidRegistrationStatus.NOT_FOUND);
            result.setDevices(Collections.EMPTY_LIST);
        }
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
