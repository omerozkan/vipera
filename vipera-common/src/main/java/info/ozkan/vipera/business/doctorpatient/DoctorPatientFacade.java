package info.ozkan.vipera.business.doctorpatient;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

/**
 * Hekim - Hasta atama işlemini yapan business sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorPatientFacade {
    /**
     * Bir hekime yeni bir hasta atar
     * 
     * @param doctor
     *            hekim
     * @param patient
     *            hasta
     * @return
     */
    DoctorPatientManagerResult assign(Doctor doctor, Patient patient);

    /**
     * Hekime ait hastaları yüklenme işlemini yapar
     * 
     * Hastalara ulaşmak için bu metodu çağırdıktan sonra @{link
     * {@link Doctor#getPatients()} metodunu kullanın
     * 
     * @param doctor
     */
    void loadPatients(Doctor doctor);

    /**
     * Hekim listesinde bulunan hastanın ataması kaldırılır
     * 
     * @param doctor
     *            Hekim
     * @param patient
     *            Hasta
     * @return
     */
    DoctorPatientManagerResult removeAssignment(Doctor doctor, Patient patient);

    /**
     * Hastaya ait hekimleri yüker
     * 
     * @param patient
     */
    void loadDoctors(Patient patient);
}
