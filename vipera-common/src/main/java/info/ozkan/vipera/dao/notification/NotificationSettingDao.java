package info.ozkan.vipera.dao.notification;

import info.ozkan.vipera.entities.NotificationSetting;

import java.util.List;

/**
 * Sistem ayarlarını veritabanına kaydeden ve yüklenmesini sağlayan DAO sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface NotificationSettingDao {
    /**
     * Sistemde kayıtlı bildirimleri alır
     * 
     * @return
     */
    List<NotificationSetting> getAll();

}
