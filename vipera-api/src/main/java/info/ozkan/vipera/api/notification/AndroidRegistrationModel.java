package info.ozkan.vipera.api.notification;

import com.google.gson.annotations.Expose;

/**
 * Android registration model
 * 
 * @author Ömer Özkan
 * 
 */
public class AndroidRegistrationModel {
    /**
     * Api anahtarı
     */
    @Expose
    private String apiKey;
    /**
     * cihaz id
     */
    @Expose
    private String registrationId;

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
