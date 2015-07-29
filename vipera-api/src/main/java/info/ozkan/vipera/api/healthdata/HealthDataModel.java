package info.ozkan.vipera.api.healthdata;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * Sağlık verisi JSON Modeli
 * 
 * @author Ömer Özkan
 * 
 */

public class HealthDataModel implements Serializable {
    /**
     * Serial
     */
    private static final long serialVersionUID = 1767995299824008025L;
    /**
     * Api anahtarı
     */
    @Expose
    private String apiKey;
    /**
     * Api parolası
     */
    @Expose
    private String apiPassword;
    /**
     * Sağlık değerleri
     */
    @Expose
    private List<HealthDataValueModel> values;

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
     * @return the values
     */
    public List<HealthDataValueModel> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<HealthDataValueModel> values) {
        this.values = values;
    }

}
