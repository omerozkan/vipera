package info.ozkan.vipera.api.notification;

import com.google.gson.annotations.Expose;

/**
 * Android cihaz kaydından sonra dönen json değeri
 * 
 * @author Ömer Özkan
 * 
 */
public class AndroidRegistrationResponseModel {
    /**
     * Hekim adi
     */
    @Expose
    private String doctorName;

    /**
     * @return the doctorName
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * @param doctorName
     *            the doctorName to set
     */
    public void setDoctorName(final String doctorName) {
        this.doctorName = doctorName;
    }
}
