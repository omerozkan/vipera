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
     * business object
     */
    @Inject
    private NotificationSettingManager notificationSettingManager;

    public void saveAll(final List<NotificationSetting> notificationSettings) {
        notificationSettingManager.saveAll(notificationSettings);
    }

}
