package info.ozkan.vipera.api.notification;

import com.google.gson.annotations.Expose;

/**
 * Bildirimlerin alınmasını sağlayan json modeli
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationListRequestModel {
    /**
     * Api anahtarı
     */
    @Expose
    private String apiKey;
    /**
     * Sağlayıcı
     */
    @Expose
    private String provider;

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
}
