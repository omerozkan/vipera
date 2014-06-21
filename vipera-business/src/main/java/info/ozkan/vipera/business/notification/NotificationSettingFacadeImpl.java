package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.NotificationSetting;

import java.util.List;

import javax.inject.Inject;

/**
 * {@link NotificationFacade} implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationSettingFacadeImpl implements NotificationSettingFacade {

    /**
     * notification setting manager
     */
    @Inject
    private NotificationSettingManager notificationSettingManager;
    /**
     * notification provider manager
     */
    @Inject
    private NotificationProviderManager notificationProviderManager;

    public void saveAll(final List<NotificationSetting> notificationSettings) {
        notificationSettingManager.saveAll(notificationSettings);
    }

    public List<NotificationSetting> getAll() {
        return notificationProviderManager.getNotificationSettings();
    }

}
