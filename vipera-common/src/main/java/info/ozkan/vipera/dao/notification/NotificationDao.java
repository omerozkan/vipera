package info.ozkan.vipera.dao.notification;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Notification;

import java.util.List;

/**
 * Bildirimler için veritabanı üzerinde işlem yapan DAO sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface NotificationDao {
    /**
     * liste ile gönderilen bütün bildirimleri veritabanına kaydeder
     * 
     * @param notification
     */
    void saveAll(List<Notification> notifications);

    /**
     * Veritabanında kayıtlı olan bildirimleri dönderir
     * 
     * @param doctor
     * @param provider
     * @return
     */
    List<Notification> getAll(Doctor doctor, String provider);
}
