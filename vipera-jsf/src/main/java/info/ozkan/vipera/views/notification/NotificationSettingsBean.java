package info.ozkan.vipera.views.notification;

import info.ozkan.vipera.business.notification.NotificationSettingFacade;
import info.ozkan.vipera.entities.NotificationSetting;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bildirim ayarları sayfası
 * 
 * @author Ömer Özkan
 * 
 */
@Named("notificationSettings")
public class NotificationSettingsBean implements Serializable {

    /**
     * kaydedildi mesajı
     */
    private static final String SAVED = "Ayarlar Kaydedildi!";

    /**
     * Serial
     */
    private static final long serialVersionUID = -3422211005015794330L;

    /**
     * bildirim ayarı listesi
     */
    private List<NotificationSetting> notificationSettings;
    /**
     * business
     */
    @Inject
    private NotificationSettingFacade notificationSettingFacade;

    /**
     * bildirimleri bilgibankasından yükler
     */
    @PostConstruct
    public void setUp() {
        setNotificationSettings(notificationSettingFacade.getAll());
    }

    /**
     * bildirim ayarlarını kaydeder
     */
    public void save() {
        notificationSettingFacade.saveAll(notificationSettings);
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

    /**
     * eğer herhangi bir sağlayıcı yoksa true dönderir
     * 
     * @return
     */
    public boolean getEmptyProvider() {
        return notificationSettings.size() == 0;
    }
}
