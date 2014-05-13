package info.ozkan.vipera.business.login;

public enum DoctorLoginStatus {
    SUCCESS(0), INVALID_USERNAME(1), INVALID_PASSWORD(2);
    /**
     * code
     */
    private int code;

    /**
     * constructor
     * 
     * @param code
     */
    private DoctorLoginStatus(final int code) {
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
