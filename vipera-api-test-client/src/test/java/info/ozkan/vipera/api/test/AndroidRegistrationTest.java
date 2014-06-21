package info.ozkan.vipera.api.test;

import info.ozkan.vipera.api.notification.AndroidRegistrationModel;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import com.google.gson.Gson;

/**
 * Android cihaz ekleme testi
 * 
 * @author Ömer Özkan
 * 
 */
public class AndroidRegistrationTest {
    private static final String REGISTER_URL =
            "http://localhost:8080/vipera/rest/android/register";

    private static final String UNREGISTER_URL =
            "http://localhost:8080/vipera/rest/android/register";

    /**
     * Android cihaz ekler
     * 
     * @throws Exception
     */
    @Test
    public void registerAndroidDevice() throws Exception {
        final String json = createRequestModel();
        final Request request =
                Request.Post(REGISTER_URL).bodyString(json,
                        ContentType.APPLICATION_JSON);
        request.execute().returnContent();
    }

    @Test
    public void unregisterAndroidDevice() throws Exception {
        final String json = createRequestModel();
        final Request request =
                Request.Post(UNREGISTER_URL).bodyString(json,
                        ContentType.APPLICATION_JSON);
        request.execute().returnContent();
    }

    private String createRequestModel() {
        final Doctor doctor = DoctorTestData.getTestData(DoctorTestData.HOUSE);
        final AndroidRegistrationModel model = new AndroidRegistrationModel();
        model.setApiKey(doctor.getApiKey());
        model.setRegistrationId("testdevice");
        final Gson gson = new Gson();
        final String json = gson.toJson(model);
        return json;
    }

}
