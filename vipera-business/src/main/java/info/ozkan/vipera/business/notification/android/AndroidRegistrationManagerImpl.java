package info.ozkan.vipera.business.notification.android;

import info.ozkan.vipera.entities.Doctor;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

/**
 * {@link AndroidRegistrationManager} implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("androidRegistrationManager")
public class AndroidRegistrationManagerImpl implements
        AndroidRegistrationManager {
    /**
     * dao
     */
    @Inject
    private AndroidRegistrationDao androidRegistrationDao;

    @Transactional
    public AndroidRegistrationResult register(final Doctor doctor,
            final String registrationId) {
        return androidRegistrationDao.add(doctor, registrationId);
    }

    @Transactional
    public AndroidRegistrationResult remove(final Doctor doctor,
            final String registrationId) {
        return androidRegistrationDao.remove(doctor, registrationId);
    }

    /**
     * @param androidRegistrationDao
     *            the androidRegistrationDao to set
     */
    public void setAndroidRegistrationDao(
            final AndroidRegistrationDao androidRegistrationDao) {
        this.androidRegistrationDao = androidRegistrationDao;
    }

}
