package info.ozkan.vipera.api.notification;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.business.notification.android.AndroidRegistrationFacade;
import info.ozkan.vipera.business.notification.android.AndroidRegistrationResult;
import info.ozkan.vipera.entities.Doctor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * Android cihazların sisteme bağlanmasını ve bağlantının koparılmasını sağlar
 * 
 * @author Ömer Özkan
 * 
 */
@Path("/android")
@Named
public class AndroidPushNotificationRestService {
    /**
     * Unregister
     */
    private static final String UNREGISTER = "unregister";
    /**
     * Register
     */
    private static final String REGISTER = "register";
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AndroidPushNotificationRestService.class);
    /**
     * Gson
     */
    private final Gson gson = new Gson();
    /**
     * Doctor Facade
     */
    @Inject
    private DoctorFacade doctorFacade;
    /**
     * Android facade
     */
    @Inject
    private AndroidRegistrationFacade androidRegistrationFacade;

    /**
     * Kayıt işlemini gerçekleştirir
     * 
     * @param body
     * @return
     */
    @POST
    @Path(REGISTER)
    public Response registerService(final String body) {
        return doOperation(body, REGISTER);
    }

    /**
     * unregister işlemini gerçekleştirir
     * 
     * @param body
     * @return
     */
    @POST
    @Path(UNREGISTER)
    public Response unregisterService(final String body) {
        return doOperation(body, UNREGISTER);

    }

    /**
     * register veya unregister işlemlerini yürütür
     * 
     * @param body
     * @param operation
     * @return
     */
    private Response doOperation(final String body, final String operation) {
        final Response response;
        final AndroidRegistrationModel model =
                gson.fromJson(body, AndroidRegistrationModel.class);

        if (model == null) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            response = processModel(model, operation);
        }
        return response;
    }

    /**
     * Model nesnesini işleme koyar
     * 
     * @param model
     * @return
     */
    private Response processModel(final AndroidRegistrationModel model,
            final String operation) {
        final Response response;
        final String apiKey = model.getApiKey();
        final DoctorManagerResult result = doctorFacade.getByApi(apiKey);
        if (!result.isSuccess()) {
            response = Response.status(Response.Status.UNAUTHORIZED).build();
        } else {
            if (operation.equals(REGISTER)) {
                response = register(model, result);
            } else {
                response = unregister(model, result);
            }
        }
        return response;
    }

    /**
     * Cihazı hekim cihaz listesinden çıkartır
     * 
     * @param model
     * @param result
     * @return
     */
    private Response unregister(final AndroidRegistrationModel model,
            final DoctorManagerResult result) {
        return checkDeviceAndProcess(model, result, UNREGISTER);
    }

    /**
     * Cihazı hekim listesine ekler
     * 
     * @param model
     * @param result
     * @return
     */
    private Response register(final AndroidRegistrationModel model,
            final DoctorManagerResult result) {
        return checkDeviceAndProcess(model, result, REGISTER);
    }

    /**
     * Cihazın kayıt işlemlerini yürütür
     * 
     * @param model
     * @param result
     * @return
     */
    private Response checkDeviceAndProcess(
            final AndroidRegistrationModel model,
            final DoctorManagerResult result, final String operation) {
        final Response response;
        final String registrationId = model.getRegistrationId();
        if (registrationId == null || registrationId.isEmpty()) {
            response = Response.status(Response.Status.FORBIDDEN).build();
        } else {
            if (operation.equals(REGISTER)) {
                response = saveDevice(result, registrationId);
            } else {
                response = removeDevice(result, registrationId);
            }
        }
        return response;
    }

    /**
     * Cihazı sisteme kaydeder
     * 
     * @param result
     * @param registrationId
     * @return
     */
    private Response saveDevice(final DoctorManagerResult result,
            final String registrationId) {
        final Response response;
        final Doctor doctor = result.getDoctor();
        final AndroidRegistrationResponseModel model =
                new AndroidRegistrationResponseModel();
        final AndroidRegistrationResult registrationResult =
                androidRegistrationFacade.register(doctor, registrationId);
        if (registrationResult.isSuccess()) {
            model.setDoctorName(doctor.getFullname());
            final String json = gson.toJson(model);
            LOGGER.info("{} has added new android device: {}", doctor,
                    registrationId);
            response = Response.status(Response.Status.OK).entity(json).build();
        } else if (registrationResult.hasExist()) {
            model.setDoctorName(doctor.getFullname());
            final String json = gson.toJson(model);
            response = Response.status(Response.Status.OK).entity(json).build();
            LOGGER.info(
                    "{} has added android device but the device has already registered!",
                    doctor);
        } else {
            response =
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .build();
        }
        return response;
    }

    /**
     * Cihazı sistemden çıkartır
     * 
     * @param result
     * @param registrationId
     * @return
     */
    private Response removeDevice(final DoctorManagerResult result,
            final String registrationId) {
        final Response response;
        final Doctor doctor = result.getDoctor();
        final AndroidRegistrationResult registrationResult =
                androidRegistrationFacade.remove(doctor, registrationId);
        if (registrationResult.isSuccess()) {
            LOGGER.info("{} has removed the android device: {}", doctor,
                    registrationId);
            response = Response.status(Response.Status.OK).build();
        } else {
            response =
                    Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                            .build();
        }
        return response;
    }
}
