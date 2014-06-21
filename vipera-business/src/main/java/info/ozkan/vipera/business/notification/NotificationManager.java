package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Notification;

import java.util.List;

/**
 * Bildirim yöneticisi
 * 
 * @author Ömer Özkan
 * 
 */
public interface NotificationManager {
    /**
     * Hekimlere gönderilen bildirim listesini dönderir
     * 
     * @param doctor
     * @param provider
     * @return
     */
    List<Notification> get(Doctor doctor, String provider);

}
