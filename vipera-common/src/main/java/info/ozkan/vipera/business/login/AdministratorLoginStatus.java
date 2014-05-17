package info.ozkan.vipera.business.login;

/**
 * Login işlemi sonucu üretilen sonuç statüs enum'u
 * 
 * @author Ömer Özkan
 * 
 */
public enum AdministratorLoginStatus {
    SUCCESS(0), INVALID_USERNAME(1), INVALID_PASSWORD(2);
    /**
     * Code
     */
    private int code;

    /**
     * Private constructor
     * 
     * @param code
     *            Code
     */
    private AdministratorLoginStatus(final int code) {
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
