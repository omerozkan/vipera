package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorNotificationSetting;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Sağlık verilerinden hekimlere gönderilecek olan bildirimleri üretir
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationGenerator {

    /**
     * Sağlık verilerinden bildirim üretir
     * 
     * @param healthData
     *            Sağlık verisi
     * @param filteredValues
     *            {@link HealthDataNotificationFilter} ile üretilen sağlık
     *            değerleri
     * @return bildirim listesi
     */
    public List<Notification> generate(final HealthData healthData,
            final List<HealthDataValue> filteredValues) {
        final List<Notification> generated = new ArrayList<Notification>();
        final Patient patient = healthData.getPatient();
        final List<Doctor> doctors = patient.getDoctors();
        for (final Doctor doctor : doctors) {
            final List<Notification> doctorNotifications =
                    createNotifications(filteredValues, patient, doctor);
            generated.addAll(doctorNotifications);
        }
        return generated;
    }

    /**
     * Hekime ait ibldirimleri üretir
     * 
     * @param filteredValues
     * @param patient
     * @param doctor
     * @return
     */
    private List<Notification> createNotifications(
            final List<HealthDataValue> filteredValues, final Patient patient,
            final Doctor doctor) {
        final List<Notification> generated = new ArrayList<Notification>();
        final List<DoctorNotificationSetting> settings = doctor.getSettings();
        for (final DoctorNotificationSetting setting : settings) {
            if (setting.getEnabled() != null && setting.getEnabled()) {
                for (final HealthDataValue value : filteredValues) {
                    final String provider = setting.getProviderId();
                    final Notification notification =
                            new Notification(patient, doctor, provider, value);
                    generated.add(notification);
                }
            }
        }
        return generated;
    }
}
