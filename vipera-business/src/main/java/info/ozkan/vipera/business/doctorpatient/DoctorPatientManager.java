package info.ozkan.vipera.business.doctorpatient;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

/**
 * Hekim hasta işlemlerini gerçekleştiren business sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorPatientManager {
    /**
     * Hekim hasta atama işlemini yapar
     * 
     * @param doctor
     * @param patient
     * @return
     */
    DoctorPatientManagerResult assign(Doctor doctor, Patient patient);

    /**
     * Hekim e ait hastaları yükler
     * 
     * @param doctor
     */
    void loadPatients(Doctor doctor);

    /**
     * Hekim listesinde bulunan bir hastanın atanmasını kaldırır
     * 
     * @param doctor
     *            hekim
     * @param patient
     *            hasta
     * @return
     */
    DoctorPatientManagerResult removeAssignment(Doctor doctor, Patient patient);

    /**
     * Hastaya atanan hekimleri yükler
     * 
     * @param patient
     */
    void loadDoctors(Patient patient);

}
