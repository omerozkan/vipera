package info.ozkan.vipera.api.notification;

/**
 * Bildiri modeli
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationModel {
    /**
     * Hasta adı
     */
    private String patientName;
    /**
     * Alanın adı
     */
    private String fieldTitle;
    /**
     * Birim
     */
    private String fieldUnit;
    /**
     * Değeri
     */
    private Double value;
    /**
     * Telefon
     */
    private String phone;
    /**
     * Mobil telefon
     */
    private String mobilePhone;

    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param patientName
     *            the patientName to set
     */
    public void setPatientName(final String patientName) {
        this.patientName = patientName;
    }

    /**
     * @return the fieldTitle
     */
    public String getFieldTitle() {
        return fieldTitle;
    }

    /**
     * @param fieldTitle
     *            the fieldTitle to set
     */
    public void setFieldTitle(final String fieldTitle) {
        this.fieldTitle = fieldTitle;
    }

    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final Double value) {
        this.value = value;
    }

    /**
     * @return the fieldUnit
     */
    public String getFieldUnit() {
        return fieldUnit;
    }

    /**
     * @param fieldUnit
     *            the fieldUnit to set
     */
    public void setFieldUnit(final String fieldUnit) {
        this.fieldUnit = fieldUnit;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone
     *            the mobilePhone to set
     */
    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
