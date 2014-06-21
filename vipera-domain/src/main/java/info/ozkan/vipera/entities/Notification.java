package info.ozkan.vipera.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Bildirim
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "NOTIFICATIONS")
public class Notification implements Serializable {
    /**
     * Serial
     */
    private static final long serialVersionUID = -2584272873773133203L;
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * Hasta
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Patient patient;
    /**
     * Hekim
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Doctor doctor;
    /**
     * Bildirim sağlayıcı
     */
    @Column(name = "provider", nullable = false)
    private String provider;
    /**
     * Sağlık verisi
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "health_data_value_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private HealthDataValue healthDataValue;

    /**
     * constructor
     */
    public Notification() {
    }

    /**
     * setter kullanmadan nesne üretilmesini sağlar
     * 
     * @param patient
     *            hasta
     * @param doctor
     *            hekim
     * @param provider
     *            sağlayıcı
     * @param healthDataValue
     *            sağlık veri değeri
     */
    public Notification(final Patient patient, final Doctor doctor,
            final String provider, final HealthDataValue healthDataValue) {
        this.patient = patient;
        this.doctor = doctor;
        this.provider = provider;
        this.healthDataValue = healthDataValue;
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
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider
     *            the provider to set
     */
    public void setProvider(final String provider) {
        this.provider = provider;
    }

    /**
     * @return the healthDataValue
     */
    public HealthDataValue getHealthDataValue() {
        return healthDataValue;
    }

    /**
     * @param healthDataValue
     *            the healthDataValue to set
     */
    public void setHealthDataValue(final HealthDataValue healthDataValue) {
        this.healthDataValue = healthDataValue;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }
}
