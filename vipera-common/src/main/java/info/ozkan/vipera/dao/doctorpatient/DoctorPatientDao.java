package info.ozkan.vipera.dao.doctorpatient;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientManagerResult;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

/**
 * Hekim hasta ilişkilerini veritabanı üzerinde işlem yaptıdan dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorPatientDao {

    /**
     * Bir hekime hasta atama işlemi yapar
     * 
     * @param doctor
     *            Hekim
     * @param patient
     *            Hasta
     * @return
     */
    DoctorPatientManagerResult
            addPatientToDoctor(Doctor doctor, Patient patient);

    /**
     * Hekime ait hastaları veritabanından bularak hekim nesnesine atanma
     * işlemini yapar
     * 
     * @param doctor
     */
    void loadPatientsByDoctor(Doctor doctor);

    /**
     * Hastaya ait hekimleri veritabanından bulara nesnesine atama işlemi yapar
     * 
     * @param patient
     */
    void loadDoctorsByPatient(Patient patient);

}
