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
    DoctorPatientManagerResult assign(Doctor doctor, Patient patient);
}
