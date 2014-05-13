package info.ozkan.vipera.api.healthdata;

import info.ozkan.vipera.business.device.DeviceFacade;
import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.business.healthdata.HealthDataFacade;
import info.ozkan.vipera.business.healthdata.HealthDataFieldFacade;
import info.ozkan.vipera.business.healthdata.HealthDataResult;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * Sağlık alanı ekleyen web servisi
 * 
 * @author Ömer Özkan
 * 
 */
@Path("/healthdata")
@Named
public class HealthDataAddService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(HealthDataAddService.class);
    /**
     * Gson
     */
    private final Gson gson = new Gson();
    /**
     * Cihaz işletme nesnesi
     */
    @Inject
    private DeviceFacade deviceFacade;
    /**
     * sağlık verisi alanı işletme nesnesi
     */
    @Inject
    private HealthDataFieldFacade healthDataFieldFacade;
    /**
     * sağlık verisi işletme nesnesi
     */
    @Inject
    private HealthDataFacade healthDataFacade;
    /**
     * response
     */
    private Response response;
    /**
     * cihaz işlemi sonucu
     */
    private DeviceManagerResult deviceResult;
    /**
     * cihaz
     */
    private Device device;
    /**
     * sağlık verisi modeli
     */
    private HealthDataModel model;

    /**
     * Sisteme yeni bir sağlık verisi ekler
     * 
     * @param body
     * @return
     */
    @POST
    @Path("/add")
    public Response addHealthData(final String body) {
        model = gson.fromJson(body, HealthDataModel.class);
        final boolean credentialValid = checkCredential();
        if (!credentialValid) {
            response = Response.status(403).build();
        } else {
            response = saveData();
        }
        return response;
    }

    /**
     * sağlık verisini sisteme kaydeder
     * 
     * @return
     */
    private Response saveData() {
        Response response;
        device = deviceResult.getDevice();
        final HealthData healthData = createHealthDataObject();
        final HealthDataResult result = healthDataFacade.add(healthData);
        if (result.isSuccess()) {
            final ResponseModel responseModel = createSuccessResponseModel();
            final String json = gson.toJson(responseModel);
            response = Response.status(200).entity(json).build();
            LOGGER.info("The new health data added to system by device-{}",
                    model.getApiKey());
        } else {
            response = Response.status(500).build();
            LOGGER.error("The new health data cannot be added to system!");
        }
        return response;
    }

    /**
     * kayıt işleminden sonra response mesajı üretir
     * 
     * @return
     */
    private ResponseModel createSuccessResponseModel() {
        final ResponseModel responseModel = new ResponseModel();
        responseModel.setCode("200");
        responseModel.setMessage("The health data added to system.");
        return responseModel;
    }

    /**
     * gönderilen verilerden {@link HealthData} nesnesi üretir
     * 
     * @return
     */
    private HealthData createHealthDataObject() {
        final Patient patient = device.getPatient();
        final HealthData healthData = new HealthData();
        healthData.setPatient(patient);
        healthData.setSendBy(String.format("Device - %s", device.getApiKey()));
        healthData.setDate(new Date());
        final List<HealthDataValue> values = createValues(healthData);
        healthData.setValues(values);
        return healthData;
    }

    /**
     * Gönderilen verilerden değerleri alarak {@link HealthDataValue}
     * nesnelerine dönüştürür
     * 
     * @param healthData
     * @return
     */
    private List<HealthDataValue> createValues(final HealthData healthData) {
        final List<HealthDataValueModel> valueModels = model.getValues();
        final List<HealthDataValue> values = new ArrayList<HealthDataValue>();
        for (final HealthDataValueModel valueModel : valueModels) {
            final String key = valueModel.getKey();
            final HealthDataField field = healthDataFieldFacade.getField(key);
            final Double value = valueModel.getValue();
            final HealthDataValue dataValue = new HealthDataValue();
            dataValue.setData(healthData);
            dataValue.setField(field);
            dataValue.setValue(value);
            values.add(dataValue);
        }
        return values;
    }

    /**
     * api anahtarı ve parolasını kontrol eder
     * 
     * @return
     */
    private boolean checkCredential() {
        final String apiKey = model.getApiKey();
        final String apiPassword = model.getApiPassword();
        boolean result = checkApiKeyAndPassword(apiKey, apiPassword);
        if (result) {
            deviceResult = deviceFacade.checkCredential(apiKey, apiPassword);
            result = deviceResult.isSuccess();
        } else {
            LOGGER.error("The cridentials is not valid: {} {}", apiKey,
                    apiPassword);
        }
        return result;
    }

    /**
     * api anahtarı ve parolanın boş veya null olup olmadığını kontrol eder
     * 
     * @param apiKey
     * @param apiPassword
     * @return
     */
    private boolean checkApiKeyAndPassword(final String apiKey,
            final String apiPassword) {
        boolean credentialValid = true;
        if (apiKey == null || apiKey.isEmpty()) {
            response = Response.status(401).build();
            credentialValid = false;
        }
        if (apiPassword == null || apiPassword.isEmpty()) {
            response = Response.status(401).build();
            credentialValid = false;
        }
        return credentialValid;
    }
}
