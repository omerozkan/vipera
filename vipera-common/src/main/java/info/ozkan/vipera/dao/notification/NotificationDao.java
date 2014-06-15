package info.ozkan.vipera.dao.notification;

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
}
