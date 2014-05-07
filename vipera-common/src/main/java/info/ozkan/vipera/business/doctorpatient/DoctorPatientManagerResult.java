package info.ozkan.vipera.business.doctorpatient;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

/**
 * Hekim hasta ilişkileri üzerinde yapılan işlemler sonucu üretilen result
 * sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorPatientManagerResult {
    /**
     * Status
     */
    private DoctorPatientManagerStatus status;

    /**
     * Doctor
     */
    private Doctor doctor;
    /**
     * Patient
     */
    private Patient patient;

    /**
     * @return the status
     */
    public DoctorPatientManagerStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final DoctorPatientManagerStatus status) {
        this.status = status;
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor
     *            the doctor to set
     */
    public void setDoctor(final Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
