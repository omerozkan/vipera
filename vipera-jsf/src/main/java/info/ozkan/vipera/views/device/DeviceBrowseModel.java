package info.ozkan.vipera.views.device;

import info.ozkan.vipera.entities.Patient;

/**
 * Cihaz arama model sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DeviceBrowseModel {
    /**
     * Hasta
     */
    private Patient patient;
    /**
     * API anahtarı
     */
    private String apiKey;

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
}
