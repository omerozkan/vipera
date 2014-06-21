package info.ozkan.vipera.api.notification;

import java.util.List;

import com.google.gson.annotations.Expose;

/**
 * Bildiri listesi modeli
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationListModel {
    /**
     * Toplam
     */
    @Expose
    private Integer count;
    /**
     * Bildiri listesi
     */
    @Expose
    private List<NotificationModel> notifications;

    /**
     * @return the notifications
     */
    public List<NotificationModel> getNotifications() {
        return notifications;
    }

    /**
     * @param notifications
     *            the notifications to set
     */
    public void setNotifications(final List<NotificationModel> notifications) {
        this.notifications = notifications;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(final Integer count) {
        this.count = count;
    }
}
