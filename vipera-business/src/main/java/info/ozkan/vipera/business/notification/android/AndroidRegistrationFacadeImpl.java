package info.ozkan.vipera.business.notification.android;

import info.ozkan.vipera.entities.Doctor;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link AndroidRegistrationFacade} implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("androidRegistrationFacade")
public class AndroidRegistrationFacadeImpl implements AndroidRegistrationFacade {
    /**
     * Manager
     */
    @Inject
    private AndroidRegistrationManager androidRegistrationManager;

    public AndroidRegistrationResult register(final Doctor doctor,
            final String registrationId) {
        return androidRegistrationManager.register(doctor, registrationId);
    }

    public AndroidRegistrationResult remove(final Doctor doctor,
            final String registrationId) {
        return androidRegistrationManager.remove(doctor, registrationId);
    }

    /**
     * @param androidRegistrationManager
     *            the androidRegistrationManager to set
     */
    public void setAndroidRegistrationManager(
            final AndroidRegistrationManager androidRegistrationManager) {
        this.androidRegistrationManager = androidRegistrationManager;
    }

}
