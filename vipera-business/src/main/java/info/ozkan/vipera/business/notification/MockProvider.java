package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.Patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockProvider implements NotificationProvider {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(MockProvider.class);

    public void send(final Notification notification) {
        final Patient patient = notification.getPatient();
        final Doctor doctor = notification.getDoctor();
        final HealthDataField field =
                notification.getHealthDataValue().getField();
        LOGGER.info("The notification sent to {} by {} because of {}", doctor,
                patient, field.getName());
    }

    public void setKey(final String apiKey) {
        // TODO Auto-generated method stub

    }

    public void setPassword(final String password) {
        // TODO Auto-generated method stub

    }

}
