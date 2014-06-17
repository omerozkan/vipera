package info.ozkan.vipera.api.test;

import info.ozkan.vipera.api.healthdata.HealthDataModel;
import info.ozkan.vipera.api.healthdata.HealthDataValueModel;
import info.ozkan.vipera.api.notification.NotificationListRequestModel;
import info.ozkan.vipera.device.DeviceTestData;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.healthdata.HealthDataFieldTestData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * hekimlerin bildirim işlemlerini test eder
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationListAPITest {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(NotificationListAPITest.class);
    /**
     * sağlık alanları apisi temel url
     */
    private static String BASE_URL = "http://localhost:8080/vipera/rest/";
    /**
     * sağlık verisi ekleme url
     */
    private static final String URL_FOR_ADDING = BASE_URL + "healthdata/add";
    /**
     * sağlık verisi ekleme url
     */
    private static final String URL_FOR_QUERYING = BASE_URL
            + "notification/list";
    /**
     * json dönüşümü
     */
    private final Gson gson = new Gson();
    /**
     * test alanı 1
     */
    private final HealthDataField testField = HealthDataFieldTestData
            .getTestNotificationField();
    /**
     * Hekim
     */
    private final Doctor doctor = DoctorTestData
            .getTestData(DoctorTestData.HOUSE);
    /**
     * request için kullanılan model sınıfı
     */
    private final HealthDataModel model = new HealthDataModel();

    @Test
    public void getNotifications() throws Exception {
        addNotificationByDevice();
        final NotificationListRequestModel notificationListModel =
                new NotificationListRequestModel();
        notificationListModel.setApiKey(doctor.getApiKey());
        notificationListModel.setProvider("sms");
        final String json = gson.toJson(notificationListModel);
        final Request request =
                Request.Post(URL_FOR_QUERYING).bodyString(json,
                        ContentType.APPLICATION_JSON);
        final String result = request.execute().returnContent().asString();
        LOGGER.info(result);
    }

    private void addNotificationByDevice() throws ClientProtocolException,
            IOException {
        final Device device = DeviceTestData.get(0);
        setKeyAndPassword(device.getApiKey(), device.getApiPassword());
        setValues();
        final String json = gson.toJson(model);
        final Request request =
                Request.Post(URL_FOR_ADDING).bodyString(json,
                        ContentType.APPLICATION_JSON);
        final String result = request.execute().returnContent().asString();
        LOGGER.info(result);
    }

    /**
     * api anahtarını ve parolasnını tanımlar
     * 
     * @param key
     * @param password
     */
    private void setKeyAndPassword(final String key, final String password) {
        model.setApiKey(key);
        model.setApiPassword(password);
    }

    /**
     * alanları ve değerleri tanımlar
     */
    private void setValues() {
        final List<HealthDataValueModel> values =
                new ArrayList<HealthDataValueModel>();
        final HealthDataValueModel model1 = new HealthDataValueModel();
        model1.setKey(testField.getName());
        model1.setValue(120.12);
        values.add(model1);

        model.setValues(values);
    }
}
