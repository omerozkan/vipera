package info.ozkan.vipera.business.notification;

import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Android push bildirimi yapmak için gereken json modeli
 * 
 * @author Ömer Özkan
 * 
 */
public class AndroidGCMNotificationSendModel {
    @Expose
    @SerializedName("registration_ids")
    private List<String> registrationIds;
    @Expose
    private Map<String, String> data;

    /**
     * @return the registrationIds
     */
    public List<String> getRegistrationIds() {
        return registrationIds;
    }

    /**
     * @param registrationIds
     *            the registrationIds to set
     */
    public void setRegistrationIds(final List<String> registrationIds) {
        this.registrationIds = registrationIds;
    }

    /**
     * @return the data
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final Map<String, String> data) {
        this.data = data;
    }
}
