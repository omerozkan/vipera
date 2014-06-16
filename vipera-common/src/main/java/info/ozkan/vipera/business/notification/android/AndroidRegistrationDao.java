package info.ozkan.vipera.business.notification.android;

import info.ozkan.vipera.entities.Doctor;

/**
 * Android cihaz kayıtları üzerinde veritabanı işlemleri yapan dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface AndroidRegistrationDao {
    /**
     * Veritabanına hekime ait bir cihazı ekler
     * 
     * @param doctor
     * @param registrationId
     * @return
     */
    AndroidRegistrationResult add(Doctor doctor, String registrationId);

    /**
     * Veritabanından hekime ait bir cihazı kaldırır
     * 
     * @param doctor
     * @param registrationId
     * @return
     */
    AndroidRegistrationResult remove(Doctor doctor, String registrationId);

}
