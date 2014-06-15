package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.business.notification.NotificationSettingManager;
import info.ozkan.vipera.dao.notification.NotificationSettingDao;
import info.ozkan.vipera.entities.NotificationSetting;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link NotificationSettingManager} arayüzünün gerçekleştirimi
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationSettingManagerImpl implements
        NotificationSettingManager {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(NotificationSettingManagerImpl.class);
    /**
     * Dao
     */
    private NotificationSettingDao notificationSettingDao;
    /**
     * Ayarlar, her seferinde veritabanından sürekli sorgulamamak için
     */
    private List<NotificationSetting> settings;

    /**
     * Sistem ayarlını dönderir. İlk değer çağırmada veritabanından sorgulanır.
     */
    public List<NotificationSetting> getAll() {
        if (settings == null) {
            LOGGER.info("All notification settings loaded from database");
            settings = notificationSettingDao.getAll();
        }
        return settings;
    }

}
