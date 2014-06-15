package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.business.notification.NotificationProvider;
import info.ozkan.vipera.business.notification.NotificationSettingManager;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.NotificationSetting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bildirimlerin gönderilmesini ve sistemde tanımlanmasını sağlar
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationProviderManagerImpl implements NotificationProviderManager {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(NotificationProviderManagerImpl.class);
    /**
     * Bildiri sağlayıcılar
     */
    private Map<String, NotificationProvider> providers =
            new HashMap<String, NotificationProvider>();
    /**
     * Bildiri ayarları
     */
    private Map<String, NotificationSetting> settings;
    /**
     * bildiri ayar yöneticisi
     */
    private NotificationSettingManager notificationSettingManager;

    /**
     * Bildiri yöneticisi üretir
     */
    public NotificationProviderManagerImpl() {
        getSystemSettings();
    }

    /**
     * Bildirim yöneticisi üretir
     * 
     * @param providers
     *            bildirim sağlayıcılar
     */
    public NotificationProviderManagerImpl(
            final Map<String, NotificationProvider> providers) {
        setProviders(providers);
        getSystemSettings();
    }

    /**
     * Sistem ayarını alır ve kullanmak üzere kaydeder
     */
    private void getSystemSettings() {
        final List<NotificationSetting> settings =
                notificationSettingManager.getAll();
        setSettings(settings);
    }

    /* (non-Javadoc)
     * @see info.ozkan.vipera.business.notificaiton.NotificationProviderManager#sendNotifications(java.util.List)
     */
    public void sendNotifications(final List<Notification> notifications) {
        for (final Notification notification : notifications) {
            final String provider = notification.getProvider();
            final NotificationSetting setting = settings.get(provider);
            if (provider.contains(provider) && setting.getEnabled()) {
                LOGGER.info("A notification sent to {} by {}",
                        notification.getDoctor(), provider);
                providers.get(provider).send(notification);
            }
        }
    }

    /* (non-Javadoc)
     * @see info.ozkan.vipera.business.notificaiton.NotificationProviderManager#getProviders()
     */
    public Set<String> getProviders() {
        return providers.keySet();
    }

    /* (non-Javadoc)
     * @see info.ozkan.vipera.business.notificaiton.NotificationProviderManager#setProviders(java.util.Map)
     */
    public void setProviders(final Map<String, NotificationProvider> providers) {
        this.providers = providers;
    }

    /* (non-Javadoc)
     * @see info.ozkan.vipera.business.notificaiton.NotificationProviderManager#setSettings(java.util.List)
     */
    public void setSettings(final List<NotificationSetting> settings) {
        LOGGER.info("System settings reconfigured!");
        this.settings.clear();
        for (final NotificationSetting setting : settings) {
            if (providers.containsKey(setting.getProviderId())) {
                this.settings.put(setting.getProviderId(), setting);
            }
        }
    }

}
