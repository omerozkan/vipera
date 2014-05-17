package info.ozkan.vipera.business.device;

/**
 * Cihazlar üzerinde yapılan işlemlerin sonucunu temsil eder
 * 
 * @author Ömer Özkan
 * 
 */
public enum DeviceManagerStatus {
    SUCCESS(0), NON_UNIQUE_API_KEY(1), PATIENT_NOT_EXIST(2), NOT_FOUND(3), INVALID_CREDENTIAL(
            4);

    /**
     * Kod
     */
    private int code;

    /**
     * Constructor
     * 
     * @param code
     */
    private DeviceManagerStatus(final int code) {
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
