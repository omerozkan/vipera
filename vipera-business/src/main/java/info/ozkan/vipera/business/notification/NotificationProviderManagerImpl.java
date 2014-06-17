package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.NotificationSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Bildirimlerin gönderilmesini ve sistemde tanımlanmasını sağlar
 * 
 * @author Ömer Özkan
 * 
 */
@Named("notificationProviderManager")
public class NotificationProviderManagerImpl implements
        NotificationProviderManager {
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
    private final Map<String, NotificationSetting> settings =
            new HashMap<String, NotificationSetting>();
    /**
     * bildiri ayar yöneticisi
     */
    @Inject
    private NotificationSettingManager notificationSettingManager;

    /**
     * Bildiri yöneticisi üretir
     */
    public NotificationProviderManagerImpl() {
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
    }

    /**
     * Sistem ayarını alır ve kullanmak üzere kaydeder
     */
    @PostConstruct
    private void setUp() {
        final List<NotificationSetting> settings =
                notificationSettingManager.getAll();
        setSettings(settings);
    }

    @Transactional
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

    /**
     * Bildirim sağlayıcılar
     */
    public void setProviders(final Map<String, NotificationProvider> providers) {
        this.providers = providers;
    }

    /**
     * Ayarlar
     */
    public void setSettings(final List<NotificationSetting> settings) {
        LOGGER.info("System settings reconfigured!");
        this.settings.clear();
        for (final NotificationSetting setting : settings) {
            if (providers.containsKey(setting.getProviderId())) {
                this.settings.put(setting.getProviderId(), setting);
            }
        }
        checkNewProviders();
        setApiAndPassword();
    }

    /**
     * sağlayıcıların kullanıcı adlarını ve parolalarını yeniden tanımlar
     */
    private void setApiAndPassword() {
        for (final String providerId : providers.keySet()) {
            final NotificationProvider provider = providers.get(providerId);
            final NotificationSetting setting = settings.get(providerId);
            provider.setKey(setting.getKey());
            provider.setPassword(setting.getPassword());
        }
    }

    /**
     * Yeni sağlayıcıların ayarları olup olmadığını kontrol eder eğer yok ise
     * boş bir ayar nesnesi üretir
     */
    private void checkNewProviders() {
        for (final String providerId : providers.keySet()) {
            if (!settings.containsKey(providerId)) {
                createEmptyNotificationSetting(providerId);
            }
        }
    }

    /**
     * sağlayıcı için boş bir ayar nesnesi üretir
     * 
     * @param providerId
     */
    private void createEmptyNotificationSetting(final String providerId) {
        final NotificationSetting notificationSetting =
                new NotificationSetting();
        notificationSetting.setProviderId(providerId);
        notificationSetting.setEnabled(false);
        settings.put(providerId, notificationSetting);
    }

    /**
     * Sistemde kayıtlı olan ayarları dönderir
     * 
     * @return
     */
    public List<NotificationSetting> getNotificationSettings() {
        return new ArrayList<NotificationSetting>(settings.values());
    }

}
