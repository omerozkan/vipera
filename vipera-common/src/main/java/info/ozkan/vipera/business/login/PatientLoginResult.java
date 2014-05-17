package info.ozkan.vipera.business.login;

import info.ozkan.vipera.entities.Patient;

/**
 * Hasta login işlemi sonucu
 * 
 * @author Ömer Özkan
 * 
 */
public class PatientLoginResult {
    /**
     * Status
     */
    private PatientLoginStatus status;
    /**
     * Hasta
     */
    private Patient patient;

    /**
     * @return the status
     */
    public PatientLoginStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final PatientLoginStatus status) {
        this.status = status;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient
     *            the patient to set
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

}
