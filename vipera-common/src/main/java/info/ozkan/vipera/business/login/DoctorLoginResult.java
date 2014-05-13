package info.ozkan.vipera.business.login;

import info.ozkan.vipera.entities.Doctor;

/**
 * Hekim login işlemi sonucu üretilen sonuç nesnesi
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorLoginResult {
    /**
     * Statüs
     */
    private DoctorLoginStatus status;
    /**
     * Doctor
     */
    private Doctor doctor;

    /**
     * @return the status
     */
    public DoctorLoginStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final DoctorLoginStatus status) {
        this.status = status;
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
