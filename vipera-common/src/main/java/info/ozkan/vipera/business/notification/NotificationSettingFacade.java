package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.NotificationSetting;

import java.util.List;

/**
 * Bildirimler üzerinde işlem yapan Facade
 * 
 * @author Ömer Özkan
 * 
 */
public interface NotificationSettingFacade {
    /**
     * Bildiri ayarlarını sisteme kaydeder
     * 
     * @param notificationSettings
     */
    void saveAll(List<NotificationSetting> notificationSettings);

}
