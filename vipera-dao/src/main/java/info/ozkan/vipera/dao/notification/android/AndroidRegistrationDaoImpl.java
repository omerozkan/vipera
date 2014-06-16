package info.ozkan.vipera.dao.notification.android;

import info.ozkan.vipera.business.notification.android.AndroidRegistrationDao;
import info.ozkan.vipera.business.notification.android.AndroidRegistrationResult;
import info.ozkan.vipera.business.notification.android.AndroidRegistrationStatus;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorAndroidDevice;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@link AndroidRegistrationDao} implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("androidRegistrationDao")
public class AndroidRegistrationDaoImpl implements AndroidRegistrationDao {
    /**
     * persistence context
     */
    private EntityManager em;

    public AndroidRegistrationResult add(final Doctor doctor,
            final String registrationId) {
        final DoctorAndroidDevice device = new DoctorAndroidDevice();
        device.setDoctor(doctor);
        device.setRegistrationId(registrationId);
        em.merge(device);
        return createSuccessResult();
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

    /**
     * @param entityManager
     *            the entityManager to set
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        em = entityManager;
    }

}
