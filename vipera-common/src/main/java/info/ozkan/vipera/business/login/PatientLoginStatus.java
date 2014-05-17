package info.ozkan.vipera.business.login;

/**
 * Hasta login işlemi statüsü
 * 
 * @author Ömer Özkan
 * 
 */
public enum PatientLoginStatus {
    SUCCESS(0), INVALID_USERNAME(1), INVALID_PASSWORD(2);

    /**
     * kod
     */
    private int code;

    /**
     * private
     */
    private PatientLoginStatus(final int code) {
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
