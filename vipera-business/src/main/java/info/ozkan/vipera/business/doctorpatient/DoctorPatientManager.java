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

}
