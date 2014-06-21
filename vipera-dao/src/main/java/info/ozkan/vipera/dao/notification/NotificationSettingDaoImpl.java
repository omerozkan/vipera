package info.ozkan.vipera.dao.notification;

import info.ozkan.vipera.entities.NotificationSetting;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * {@link} {@link NotificationSettingDao} implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("notificationSettingDao")
public class NotificationSettingDaoImpl implements NotificationSettingDao {
    /**
     * sistemdeki bütün ayarları dönderen JQL sorgusu
     */
    private static final String JQL_SELECT_ALL = "from NotificationSetting";
    /**
     * Persistence context
     */
    private EntityManager em;

    public List<NotificationSetting> getAll() {
        final String jql = JQL_SELECT_ALL;
        final Query query = em.createQuery(jql);
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

    public void saveAll(final List<NotificationSetting> notificationSettings) {
        for (final NotificationSetting setting : notificationSettings) {
            em.merge(setting);
        }
    }

}
