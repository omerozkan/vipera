package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.HealthData;

/**
 * Hekimlere bildiri gönderme işlemlerini yürütür
 * 
 * @author Ömer Özkan
 * 
 */
public interface NotificationService {
    /**
     * Bildirleri hekimlere gönderir
     * 
     * @param healthData
     */
    void sendNotifications(HealthData healthData);
}
