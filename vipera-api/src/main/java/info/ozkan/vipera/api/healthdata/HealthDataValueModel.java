package info.ozkan.vipera.api.healthdata;

import com.google.gson.annotations.Expose;

/**
 * Sağlık verisi değer modeli
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataValueModel {
    /**
     * Anahtar
     */
    @Expose
    private String key;
    /**
     * Değer
     */
    @Expose
    private Double value;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final Double value) {
        this.value = value;
    }
}
