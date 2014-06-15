package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.business.notification.NotificationProvider;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.NotificationSetting;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Bildirimlerin gönderilmesini ve sistemde tanımlanmasını sağlar
 * 
 * @author Ömer Özkan
 * 
 */
public interface NotificationProviderManager {

    /**
     * Bildirimlerin hekimlere gönderilmesini sağlar
     * 
     * @param notifications
     */
    void sendNotifications(List<Notification> notifications);

    /**
     * @return the providers
     */
    Set<String> getProviders();

    /**
     * @param providers
     *            the providers to set
     */
    void setProviders(Map<String, NotificationProvider> providers);

    /**
     * @param settings
     *            the settings to set
     */
    void setSettings(List<NotificationSetting> settings);

}