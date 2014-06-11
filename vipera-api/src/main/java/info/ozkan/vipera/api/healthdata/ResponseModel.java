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
    private int code;
    /**
     * Mesaj
     */
    @Expose
    private String message;

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

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(final int code) {
        this.code = code;
    }
}
