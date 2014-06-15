package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.Notification;

/**
 * Bildirim sağlayıcı
 * 
 * @author Ömer Özkan
 * 
 */
public interface NotificationProvider {
    /**
     * Bildiri gönderir
     * 
     * @param notification
     */
    void send(Notification notification);

    /**
     * Api anahtarı
     * 
     * @param apiKey
     */
    void setKey(String apiKey);

    /**
     * Api parolası
     * 
     * @param password
     */
    void setPassword(String password);
}
