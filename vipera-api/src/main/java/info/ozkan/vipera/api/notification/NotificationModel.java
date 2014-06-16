package info.ozkan.vipera.api.notification;

/**
 * Bildiri modeli
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationModel {
    /**
     * hasta tck kimlik no ilk 4 hanesi
     */
    private String patientTckn;
    /**
     * Hasta adı
     */
    private String patientName;
    /**
     * Alanın adı
     */
    private String fieldTitle;
    /**
     * Değeri
     */
    private Double value;
    /**
     * @return the patientTckn
     */
    public String getPatientTckn() {
        return patientTckn;
    }
    /**
     * @param patientTckn the patientTckn to set
     */
    public void setPatientTckn(String patientTckn) {
        this.patientTckn = patientTckn;
    }
    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }
    /**
     * @param patientName the patientName to set
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    /**
     * @return the fieldTitle
     */
    public String getFieldTitle() {
        return fieldTitle;
    }
    /**
     * @param fieldTitle the fieldTitle to set
     */
    public void setFieldTitle(String fieldTitle) {
        this.fieldTitle = fieldTitle;
    }
    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(Double value) {
        this.value = value;
    }
}
