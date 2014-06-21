package info.ozkan.vipera.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Android cihazlar için hekim, registirationID eşleşmesi yapan model
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "DOCTOR_ANDROID_DEVICES")
public class DoctorAndroidDevice {
    /**
     * GCM tarafından gönderilen id
     */
    @Id
    @Column(name = "registration_id", nullable = false, unique = true)
    private String registrationId;
    /**
     * Hekim
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

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
     * @return the registrationId
     */
    public String getRegistrationId() {
        return registrationId;
    }

    /**
     * @param registrationId
     *            the registrationId to set
     */
    public void setRegistrationId(final String registrationId) {
        this.registrationId = registrationId;
    }
}
