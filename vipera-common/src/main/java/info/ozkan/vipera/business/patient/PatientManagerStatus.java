package info.ozkan.vipera.business.patient;

/**
 * Hasta crud işlemleri sonucunda oluşan durumlar
 * 
 * @author Ömer Özkan
 * 
 */
public enum PatientManagerStatus {
    SUCCESS(0), TCKN_HAS_EXIST(1);

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
