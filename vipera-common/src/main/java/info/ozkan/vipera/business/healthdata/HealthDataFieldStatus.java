package info.ozkan.vipera.business.healthdata;

/**
 * Hasta sağlık alanı üzerinde yapılan işlemlerin sonucunu tanımlayan
 * enumaration sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public enum HealthDataFieldStatus {
    SUCCESS(0), NON_UNIQUE_NAME(1);

    /**
     * sonuç kodu
     */
    private int code;

    /**
     * private constructor
     * 
     * @param code
     */
    private HealthDataFieldStatus(final int code) {
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
