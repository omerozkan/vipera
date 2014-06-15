package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.notification.NotificationSettingDao;
import info.ozkan.vipera.entities.NotificationSetting;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link NotificationSettingManager} arayüzünün gerçekleştirimi
 * 
 * @author Ömer Özkan
 * 
 */
@Named("notificationSettingManager")
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
    @Inject
    private NotificationSettingDao notificationSettingDao;
    /**
     * provider manager
     */
    @Inject
    private NotificationProviderManager notificationProviderManager;
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

    @Transactional
    @RolesAllowed(Role.ROLE_ADMIN)
    public void saveAll(final List<NotificationSetting> notificationSettings) {
        notificationSettingDao.saveAll(notificationSettings);
        notificationProviderManager.setSettings(notificationSettings);
    }

}
