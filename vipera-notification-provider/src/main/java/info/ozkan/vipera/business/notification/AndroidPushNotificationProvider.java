package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.business.notification.android.AndroidRegistrationDao;
import info.ozkan.vipera.business.notification.android.AndroidRegistrationResult;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorAndroidDevice;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.Patient;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * Android Push notification Provider
 * 
 * @author Ömer Özkan
 * 
 */
public class AndroidPushNotificationProvider implements NotificationProvider {
    /**
     * Gönderilecek olan tckn boyutu
     */
    private static final int TCKN_SIZE = 4;
    /**
     * Bildiri mesaj deseni
     */
    private static final String NOTIFICATION_MSG_PATTERN =
            "%s isimli hastanıza ait , %s değeri sınırların dışında %s %s olarak ölçülmüştür!";
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AndroidPushNotificationProvider.class);
    /**
     * Api anahtarı
     */
    private String apiKey;
    /**
     * GCM adresi
     */
    private static final String GCM_URL =
            "https://android.googleapis.com/gcm/send";

    /**
     * DAo
     */
    @Inject
    private AndroidRegistrationDao androidRegistrationDao;

    /**
     * push notification gönderme işlemlerini yürütür
     */
    public void send(final Notification notification) {
        final Doctor doctor = notification.getDoctor();
        final AndroidRegistrationResult result =
                androidRegistrationDao.findDevices(doctor);
        final List<DoctorAndroidDevice> devices = result.getDevices();
        for (final DoctorAndroidDevice device : devices) {
            final String registerId = device.getRegistrationId();
            sendNotification(registerId, notification);
        }
    }

    /**
     * push notification gönderir
     * 
     * @param registerId
     * @param notification
     */
    private void sendNotification(final String registerId,
            final Notification notification) {

        try {
            String result;
            final AndroidGCMNotificationSendModel model =
                    new AndroidGCMNotificationSendModel();
            final Map<String, String> map =
                    createNotificationValues(notification);
            model.setData(map);
            model.setRegistrationIds(Arrays.asList(registerId));
            final Gson gson = new Gson();
            final String json = gson.toJson(model);
            final Request request =
                    Request.Post(GCM_URL)
                            .addHeader("Authorization", "key=" + apiKey)
                            .bodyString(json, ContentType.APPLICATION_JSON);
            result = request.execute().returnContent().toString();
            LOGGER.info("System result: " + result);
        } catch (final ClientProtocolException e) {
            LOGGER.error("exception: " + e);
        } catch (final IOException e) {
            LOGGER.error("exception: " + e);
        }
    }

    /**
     * Bildirim için gereken verileri üretir
     * 
     * @param notification
     * @return
     */
    private Map<String, String> createNotificationValues(
            final Notification notification) {
        final Patient patient = notification.getPatient();
        final HealthDataValue value = notification.getHealthDataValue();
        final HealthDataField field = value.getField();
        final Map<String, String> map = new HashMap<String, String>();
        final String patientName =
                patient.getTckn().toString().substring(0, TCKN_SIZE) + "-"
                        + patient.getFullname();
        final String msg =
                String.format(NOTIFICATION_MSG_PATTERN, patientName,
                        field.getName(), value.getValue(), field.getUnit());
        map.put("message", msg);
        map.put("patientName", patientName);
        map.put("patientPhone", patient.getPhone());
        map.put("patientMobilePhone", patient.getMobilePhone());
        map.put("fieldName", field.getTitle());
        map.put("fieldUnit", field.getUnit());
        map.put("value", value.getValue().toString());
        return map;
    }

    public void setKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public void setPassword(final String password) {

    }

}
