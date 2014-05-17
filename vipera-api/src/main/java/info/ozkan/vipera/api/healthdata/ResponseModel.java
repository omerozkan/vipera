package info.ozkan.vipera.api.healthdata;

import com.google.gson.annotations.Expose;

/**
 * API ile gelen isteklere gönderilecek cevap modeli
 * 
 * @author Ömer Özkan
 * 
 */
public class ResponseModel {
    /**
     * Kod
     */
    @Expose
    private String code;
    /**
     * Mesaj
     */
    @Expose
    private String message;

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
