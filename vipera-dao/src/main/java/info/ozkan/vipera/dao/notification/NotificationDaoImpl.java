package info.ozkan.vipera.dao.notification;

import info.ozkan.vipera.entities.Notification;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@link NotificationDao} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("notificationDao")
public class NotificationDaoImpl implements NotificationDao {

    /**
     * persistence context
     */
    private EntityManager em;

    /**
     * Veritabanına bildirimleri kaydeder
     */
    public void saveAll(final List<Notification> notifications) {
        for (final Notification notification : notifications) {
            em.persist(notification);
        }
    }

    /**
     * @param entityManager
     *            the entityManager to set
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        em = entityManager;
    }

}
