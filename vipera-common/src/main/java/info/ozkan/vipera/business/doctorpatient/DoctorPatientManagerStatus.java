package info.ozkan.vipera.business.doctorpatient;

/**
 * Hekim hasta ilişkisi yönetimi
 * 
 * @author Ömer Özkan
 * 
 */
public enum DoctorPatientManagerStatus {
    SUCCESS(0), EXIST(1);
    /**
     * code
     */
    private int code;

    /**
     * constructor
     * 
     * @param code
     */
    private DoctorPatientManagerStatus(final int code) {
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
