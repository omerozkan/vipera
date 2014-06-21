package info.ozkan.vipera.business.notification.android;

import info.ozkan.vipera.entities.Doctor;

/**
 * Hekimin android cihazları üzerinde işlem yapan facade
 * 
 * @author Ömer Özkan
 * 
 */
public interface AndroidRegistrationFacade {
    /**
     * Hekime ait bir cihaz kaydı gerçekleştirir
     * 
     * @param doctor
     * @param registrationId
     * @return
     */
    AndroidRegistrationResult register(Doctor doctor, String registrationId);

    /**
     * Hekime ait bir cihazı sistemden kaldırır
     * 
     * @param doctor
     * @param registrationId
     * @return
     */
    AndroidRegistrationResult remove(Doctor doctor, String registrationId);

}
