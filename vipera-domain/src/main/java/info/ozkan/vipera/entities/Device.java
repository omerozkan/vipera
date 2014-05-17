package info.ozkan.vipera.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
 * Cihaz
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "DEVICES")
public class Device implements Serializable {
    /**
     * serial
     */
    private static final long serialVersionUID = 1011293402063137518L;
    /**
     * Cihaz ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    /**
     * Api anahtari
     */
    @Column(name = "api_key", unique = true, nullable = false)
    private String apiKey;
    /**
     * Api parolası
     */
    @Column(name = "api_password", nullable = false)
    private String apiPassword;
    /**
     * Sahibi
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Patient patient;
    /**
     * Aktiflik
     */
    @Column(name = "enabled")
    @Enumerated(EnumType.ORDINAL)
    private Authorize enabled;

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

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey
     *            the apiKey to set
     */
    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return the apiPassword
     */
    public String getApiPassword() {
        return apiPassword;
    }

    /**
     * @param apiPassword
     *            the apiPassword to set
     */
    public void setApiPassword(final String apiPassword) {
        this.apiPassword = apiPassword;
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
     * @return the enabled
     */
    public Authorize getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(final Authorize enabled) {
        this.enabled = enabled;
    }
}
