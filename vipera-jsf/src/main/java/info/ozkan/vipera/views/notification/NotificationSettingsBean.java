package info.ozkan.vipera.views.notification;

import info.ozkan.vipera.business.notification.NotificationSettingFacade;
import info.ozkan.vipera.business.notification.NotificationProviderManager;
import info.ozkan.vipera.entities.NotificationSetting;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bildirim ayarları sayfası
 * 
 * @author Ömer Özkan
 * 
 */
@Named("notificationSettings")
public class NotificationSettingsBean implements Serializable {

    private static final String SAVED = "Ayarlar Kaydedildi!";

    /**
     * Serial
     */
    private static final long serialVersionUID = -3422211005015794330L;

    private static final Logger LOGGER = LoggerFactory
            .getLogger(NotificationSettingsBean.class);

    private List<NotificationSetting> notificationSettings;

    @Inject
    private NotificationProviderManager notificationProviderManager;

    @Inject
    private NotificationSettingFacade notificationFacade;

    @PostConstruct
    public void setUp() {
        setNotificationSettings(notificationProviderManager
                .getNotificationSettings());
        LOGGER.info(getNotificationSettings().size() + "");
    }

    public void save() {
        notificationFacade.saveAll(notificationSettings);
        final FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                SAVED, ""));
    }

    /**
     * @return the notificationSettings
     */
    public List<NotificationSetting> getNotificationSettings() {
        return notificationSettings;
    }

    /**
     * @param notificationSettings
     *            the notificationSettings to set
     */
    public void setNotificationSettings(
            final List<NotificationSetting> notificationSettings) {
        this.notificationSettings = notificationSettings;
    }

    public boolean getEmptyProvider() {
        return notificationSettings.size() == 0;
    }
}
