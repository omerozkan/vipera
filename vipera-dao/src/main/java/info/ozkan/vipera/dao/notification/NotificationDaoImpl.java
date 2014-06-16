package info.ozkan.vipera.dao.notification;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Notification;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * {@link NotificationDao} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("notificationDao")
public class NotificationDaoImpl implements NotificationDao {
    /**
     * Hekime ve sağlayıcıya göre veritabanından bildirimleri sorgulamak için
     * tanımlanan JQL sorgusu
     */
    private static final String JQL_GET_BY_DOCTOR =
            "from Notification n WHERE n.doctor = :doctor AND n.provider = :provider";
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

    public List<Notification>
            getAll(final Doctor doctor, final String provider) {
        final Query query = em.createQuery(JQL_GET_BY_DOCTOR);
        query.setParameter("doctor", doctor);
        query.setParameter("provider", provider);
        return query.getResultList();
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
