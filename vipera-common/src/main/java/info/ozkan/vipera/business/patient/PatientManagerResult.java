package info.ozkan.vipera.business.patient;

import info.ozkan.vipera.entities.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Hastalar üzerinde yapılan CRUD işlemleri sonucunda istemci için üretilen
 * sonuç sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class PatientManagerResult {
    /**
     * Status
     */
    private PatientManagerStatus status;
    /**
     * Hasta listesi
     */
    private List<Patient> patients = new ArrayList<Patient>();

    /**
     * @return the status
     */
    public PatientManagerStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final PatientManagerStatus status) {
        this.status = status;
    }

    /**
     * Arama işlemi sonucu hastaların listesini dönderir
     * 
     * @return
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Hasta listesi
     * 
     * @param patientList
     */
    public void setPatientList(final List<Patient> patientList) {
        patients = patientList;
    }

    /**
     * İşlem sonucu yüklenen hasta nesnesi dönderir. Genellikle tek sonuç dönen
     * işlemler için kullanılır
     * 
     * @return
     */
    public Patient getPatient() {
        if (patients.size() > 0) {
            return patients.get(0);
        }
        return null;
    }

    /**
     * Sonuca hasta ekler Genellikle tek sonuç dönen durumlar için kullanılır
     * 
     * @param patient
     */
    public void setPatient(final Patient patient) {
        patients.clear();
        patients.add(patient);
    }

    /**
     * işlem başarılı ise true dönderir
     * 
     * @return
     */
    public boolean isSuccess() {
        return status.equals(PatientManagerStatus.SUCCESS);
    }

}
