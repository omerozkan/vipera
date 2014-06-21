package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.Patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bildirim sistemini test etmek için kullanılan sadece gelen bildirimleri log
 * olarak gösteren bildirim sağlayıcı
 * 
 * @author Ömer Özkan
 * 
 */
public class MockProvider implements NotificationProvider {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MockProvider.class);

    /**
     * Bildirim gönderir
     */
    public void send(final Notification notification) {
        final Patient patient = notification.getPatient();
        final Doctor doctor = notification.getDoctor();
        final HealthDataField field =
                notification.getHealthDataValue().getField();
        LOGGER.info("The notification sent to {} by {} because of {}", doctor,
                patient, field.getName());
    }

    /**
     * Api anahtarı
     */
    public void setKey(final String apiKey) {

    }

    /**
     * Api password
     */
    public void setPassword(final String password) {

    }

}
