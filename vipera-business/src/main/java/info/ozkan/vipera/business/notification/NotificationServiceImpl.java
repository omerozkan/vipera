package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.business.notification.NotificationSettingManager;
import info.ozkan.vipera.dao.doctorpatient.DoctorPatientDao;
import info.ozkan.vipera.dao.notification.NotificationDao;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.NotificationSetting;
import info.ozkan.vipera.entities.Patient;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link NotificationService} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationServiceImpl implements NotificationService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(NotificationServiceImpl.class);
    /**
     * ayar yöneticisi
     */
    private NotificationSettingManager notificationSettingManager;
    /**
     * bildirim yöneticisi
     */
    private NotificationProviderManager notificationProviderManager;
    /**
     * dao nesnesi
     */
    private NotificationDao notificationDao;
    /**
     * filtreleyiic
     */
    private final HealthDataNotificationFilter filter =
            new HealthDataNotificationFilter();
    /**
     * bildirim üreteci
     */
    private final NotificationGenerator generator = new NotificationGenerator();

    private DoctorPatientDao doctorPatientDao;

    /**
     * Hekimlere bildirim gönderir
     */
    public void sendNotifications(final HealthData healthData) {
        final boolean sendNotification = checkNotificationSettings();
        if (sendNotification) {
            sendNotification(healthData);
        }
    }

    /**
     * Bir bildirimi hekime gönderir
     * 
     * @param healthData
     */
    private void sendNotification(final HealthData healthData) {
        final List<HealthDataValue> filteredData = filter.filter(healthData);
        if (filteredData.size() != 0) {
            final Patient patient = healthData.getPatient();
            LOGGER.info("The notifications will be sent for {}", patient);
            doctorPatientDao.loadDoctorsByPatient(patient);
            final List<Notification> notifications =
                    generator.generate(healthData, filteredData);
            notificationProviderManager.sendNotifications(notifications);
            notificationDao.saveAll(notifications);
        }
    }

    /**
     * Sistem ayarlarını kontrol eder eğer herhangi bir bildirim aktif değilse
     * sistemdeki diğer sınıfları çağırmaz
     * 
     * @return
     */
    private boolean checkNotificationSettings() {
        final List<NotificationSetting> settings =
                notificationSettingManager.getAll();
        boolean sendNotification = false;
        for (final NotificationSetting setting : settings) {
            if (setting.getEnabled()) {
                sendNotification = true;
                break;
            }
        }
        return sendNotification;
    }

    /**
     * @param notificationSettingManager
     *            the notificationSettingManager to set
     */
    public void setNotificationSettingManager(
            final NotificationSettingManager notificationSettingManager) {
        this.notificationSettingManager = notificationSettingManager;
    }

    /**
     * @param notificationProviderManager
     *            the notificationProviderManager to set
     */
    public void setNotificationProviderManager(
            final NotificationProviderManager notificationProviderManager) {
        this.notificationProviderManager = notificationProviderManager;
    }

    /**
     * @param notificationDao
     *            the notificationDao to set
     */
    public void setNotificationDao(final NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    /**
     * @param doctorPatientDao
     *            the doctorPatientDao to set
     */
    public void setDoctorPatientDao(final DoctorPatientDao doctorPatientDao) {
        this.doctorPatientDao = doctorPatientDao;
    }

}
