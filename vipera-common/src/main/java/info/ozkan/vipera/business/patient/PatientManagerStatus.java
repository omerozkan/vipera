package info.ozkan.vipera.business.patient;

/**
 * Hasta crud işlemleri sonucunda oluşan durumlar
 * 
 * @author Ömer Özkan
 * 
 */
public enum PatientManagerStatus {
    UNEXPECTED_ERROR(-1), SUCCESS(0), TCKN_HAS_EXIST(1), NOT_FOUND(2);

    private int code;

    private PatientManagerStatus(final int code) {
        this.code = code;
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
