package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Notification;

import java.util.List;

import javax.inject.Inject;

/**
 * {@link NotificationFacade} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationFacadeImpl implements NotificationFacade {
    /**
     * bildirim yöneticisi
     */
    @Inject
    private NotificationManager notificationManager;

    public List<Notification> get(final Doctor doctor, final String provider) {
        return notificationManager.get(doctor, provider);
    }

}
