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
import java.util.Random;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * logger
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(HealthDataAPITest.class);
    /**
     * sağlık alanları apisi temel url
     */
    private static String BASE_URL =
            "http://localhost:8080/vipera/rest/healthdata/";
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
        final Device device = DeviceTestData.get(0);
        setKeyAndPassword(device.getApiKey(), device.getApiPassword());
        setValues();
        final String json = gson.toJson(model);
        final Request request =
                Request.Post(URL)
                        .bodyString(json, ContentType.APPLICATION_JSON);
        final String result = request.execute().returnContent().asString();
        Assert.assertThat(result, containsString("200"));
    }

    /**
     * test verilerini girer
     * 
     * @throws Exception
     */
    @Test
    @Ignore
    public void generateTestData() throws Exception {
        for (int i = 0; i < 3; i++) {
            final Device device = DeviceTestData.get(i);
            final List<HealthDataModel> models =
                    generateRandomValues(device.getApiKey(),
                            device.getApiPassword());
            LOGGER.info("Random values generated for {}", i);
            for (final HealthDataModel model : models) {
                final String json = gson.toJson(model);
                final Request request =
                        Request.Post(URL).bodyString(json,
                                ContentType.APPLICATION_JSON);
                final String result =
                        request.execute().returnContent().asString();
                Assert.assertThat(result, containsString("200"));
            }
            LOGGER.info("Random values added for {}", i);
        }
    }

    /**
     * alanları ve değerleri tanımlar
     */
    private void setValues() {
        final List<HealthDataValueModel> values =
                new ArrayList<HealthDataValueModel>();
        final HealthDataValueModel model1 = new HealthDataValueModel();
        model1.setKey(testField1.getName());
        model1.setValue(130.12);
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

    /**
     * rastgele veriler üretir
     */
    private List<HealthDataModel> generateRandomValues(final String apiKey,
            final String password) {
        final List<HealthDataModel> modelList =
                new ArrayList<HealthDataModel>();
        final Random random = new Random();
        for (int i = 0; i < 50; i++) {
            final List<HealthDataValueModel> valueModels =
                    new ArrayList<HealthDataValueModel>();
            final HealthDataModel model = new HealthDataModel();
            model.setApiKey(apiKey);
            model.setApiPassword(password);

            HealthDataField field = getTestData(0);
            double value = 60 + random.nextInt(40);
            valueModels.add(createValueModel(field, value));

            field = getTestData(1);
            value = 90 + random.nextInt(50);
            valueModels.add(createValueModel(field, value));

            field = getTestData(2);
            value = 60 + random.nextInt(30);
            valueModels.add(createValueModel(field, value));

            field = getTestData(3);
            value = 12 + random.nextInt(8);
            valueModels.add(createValueModel(field, value));

            field = getTestData(4);
            value = 12 + random.nextDouble() * 5;
            valueModels.add(createValueModel(field, value));

            field = getTestData(5);
            value = 36.5 + random.nextDouble();
            valueModels.add(createValueModel(field, value));

            field = getTestData(6);
            value = 18.5 + random.nextDouble() * 6.4;
            valueModels.add(createValueModel(field, value));

            field = getTestData(7);
            value = 59 + random.nextInt(61);
            valueModels.add(createValueModel(field, value));

            model.setValues(valueModels);
            modelList.add(model);
        }
        return modelList;
    }

    private HealthDataValueModel createValueModel(final HealthDataField field,
            final double value) {
        final HealthDataValueModel valueModel = new HealthDataValueModel();
        valueModel.setKey(field.getName());
        valueModel.setValue(value);
        return valueModel;
    }

    private HealthDataField getTestData(final int code) {
        return HealthDataFieldTestData.getTestData(code);
    }
}
