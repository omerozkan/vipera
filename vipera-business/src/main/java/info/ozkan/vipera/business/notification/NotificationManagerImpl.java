package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.dao.notification.NotificationDao;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Notification;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

/**
 * {@link NotificationManager} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationManagerImpl implements NotificationManager {
    /**
     * Dao
     */
    @Inject
    private NotificationDao notificationDao;

    @Transactional
    public List<Notification> get(final Doctor doctor, final String provider) {
        return notificationDao.getAll(doctor, provider);
    }

}
