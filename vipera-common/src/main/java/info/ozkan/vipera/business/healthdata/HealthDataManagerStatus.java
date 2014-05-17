package info.ozkan.vipera.business.healthdata;

/**
 * Sağlık alanları üzerinde yapılan işlemler sonucu kullanılan enum
 * 
 * @author Ömer Özkan
 * 
 */
public enum HealthDataManagerStatus {
    SUCCESS(0), NOT_FOUND(1);

    /**
     * Code
     */
    private int code;

    /**
     * private constructor
     * 
     * @param code
     */
    private HealthDataManagerStatus(final int code) {
        setCode(code);
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
