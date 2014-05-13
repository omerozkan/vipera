package info.ozkan.vipera.api.test;

import static org.hamcrest.CoreMatchers.containsString;
import info.ozkan.vipera.api.healthdata.HealthDataModel;
import info.ozkan.vipera.api.healthdata.HealthDataValueModel;
import info.ozkan.vipera.device.DeviceTestData;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.healthdata.HealthDataFieldTestData;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

/**
 * Bu testlerin doğru çalışması için uygulama API'sinin aktif olması
 * gerekmektedir.
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataAPITest {
    /**
     * sağlık alanları apisi temel url
     */
    private static String BASE_URL = "http://localhost:8080/vipera/rest/healthdata/";
    /**
     * sağlık verisi ekleme url
     */
    private static final String URL = BASE_URL + "add";
    /**
     * json dönüşümü
     */
    private final Gson gson = new Gson();
    /**
     * test alanı 1
     */
    private final HealthDataField testField1 = HealthDataFieldTestData
            .getTestData(0);
    /**
     * test alanı 2
     */
    private final HealthDataField testField2 = HealthDataFieldTestData
            .getTestData(1);
    /**
     * request için kullanılan model sınıfı
     */
    private final HealthDataModel model = new HealthDataModel();

    /**
     * apikey ve apipassword sistemde kayıtlı değildir forbidden hatası
     * görüntülenir
     * 
     * @throws Exception
     */
    @Test
    public void testWithBadCredential() throws Exception {
        try {
            setKeyAndPassword("asdasdasd", "asdasdasdasd");
            setValues();
            final String json = gson.toJson(model);
            Request.Post(URL).bodyString(json, ContentType.APPLICATION_JSON)
                    .execute().returnContent();
        } catch (final HttpResponseException e) {
            Assert.assertThat(e.getMessage(),
                    CoreMatchers.containsString("Forbidden"));
        }

    }

    /**
     * Herhangi bir veri gönderilmeden adrese post request gönderilir ve 200
     * alınmaz
     * 
     * @throws Exception
     */
    @Test(expected = HttpResponseException.class)
    public void testEmptyPost() throws Exception {
        Request.Post(URL).execute().returnContent();
    }

    /**
     * Sistemde kayıtlı olan bir cihaz ile sağlık verisi girilir sonuç
     * başarılırdır 200 hata mesajı görüntülenşr
     * 
     * @throws Exception
     */
    @Test
    public void testSuccessfullAdding() throws Exception {
        final Device device = DeviceTestData.get();
        setKeyAndPassword(device.getApiKey(), device.getApiPassword());
        setValues();
        final String json = gson.toJson(model);
        final Request request = Request.Post(URL).bodyString(json,
                ContentType.APPLICATION_JSON);
        final String result = request.execute().returnContent().asString();
        Assert.assertThat(result, containsString("200"));
    }

    /**
     * alanları ve değerleri tanımlar
     */
    private void setValues() {
        final List<HealthDataValueModel> values = new ArrayList<HealthDataValueModel>();
        final HealthDataValueModel model1 = new HealthDataValueModel();
        model1.setKey(testField1.getName());
        model1.setValue(80.12);
        final HealthDataValueModel model2 = new HealthDataValueModel();
        model2.setKey(testField2.getName());
        model2.setValue(120.312);
        values.add(model1);
        values.add(model2);

        model.setValues(values);
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
}
