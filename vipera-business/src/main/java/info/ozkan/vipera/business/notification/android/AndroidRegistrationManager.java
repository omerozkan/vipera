package info.ozkan.vipera.business.notification.android;

import info.ozkan.vipera.entities.Doctor;

/**
 * Android cihaz kayıtları üzerinde işlem yapan işletme sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface AndroidRegistrationManager {
    /**
     * Hekime ait bir cihazı sisteme ekler
     * 
     * @param doctor
     * @param registrationId
     * @return
     */
    AndroidRegistrationResult register(Doctor doctor, String registrationId);

    /**
     * Hekime ait bir cihazı kaldırır
     * 
     * @param doctor
     * @param registrationId
     * @return
     */
    AndroidRegistrationResult remove(Doctor doctor, String registrationId);

}
