package info.ozkan.vipera.business.device;

import info.ozkan.vipera.entities.Patient;

/**
 * Sistemde cihaz aramak için oluşturulan filtre sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DeviceManagerSearchFilter {
    /**
     * Hasta
     */
    private Patient patient;
    /**
     * Api anahtarı
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
