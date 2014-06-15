package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.business.notification.NotificationService;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthData;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link HealthDataFacade} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataFacade")
public class HealthDataFacadeImpl implements HealthDataFacade {
    /**
     * sağlık alanı servisi
     */
    @Inject
    private HealthDataService healthDataService;
    /**
     * bildirim servisi
     */
    @Inject
    private NotificationService notificationService;

    public HealthDataResult add(final HealthData healthData) {
        final HealthDataResult result = healthDataService.add(healthData);
        notificationService.sendNotifications(healthData);
        return result;
    }

    public HealthDataResult find(final HealthDataSearchFilter filter) {
        return healthDataService.find(filter);
    }

    public HealthDataResult getById(final Long id, final Doctor doctor) {
        return healthDataService.getById(id, doctor);
    }

}
