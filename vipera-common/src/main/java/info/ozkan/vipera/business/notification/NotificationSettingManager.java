package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.NotificationSetting;

import java.util.List;

/**
 * Bildiri ayar yöneticisi
 * 
 * @author Ömer Özkan
 * 
 */
public interface NotificationSettingManager {
    /**
     * Sistemde kayıtlı olan bütün bildiri ayarlarını dönderir
     * 
     * @return
     */
    List<NotificationSetting> getAll();

    /**
     * Sisteme bildirim ayarlarını kaydeder
     * 
     * @param notificationSettings
     */
    void saveAll(List<NotificationSetting> notificationSettings);

}
