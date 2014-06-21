package info.ozkan.vipera.api.notification;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.business.notification.NotificationFacade;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.Patient;

import java.util.ArrayList;
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
 * Hekimlerin bildirimlerini almasını sağlayan web servisi
 * 
 * @author omer
 * 
 */
@Path("/notification")
@Named
public class NotificationRestService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(NotificationRestService.class);
    /**
     * gson
     */
    private final Gson gson = new Gson();

    /**
     * DoctorFacade
     */
    @Inject
    private DoctorFacade doctorFacade;

    /**
     * NotificationFacade
     */
    @Inject
    private NotificationFacade notificationFacade;

    /**
     * Hekimlerin aldığı bildirileri listeleyen web servis metodu
     * 
     * @param body
     * @return
     */
    @POST
    @Path("list")
    public Response getNotifications(final String body) {
        Response response;
        final NotificationListRequestModel requestModel =
                gson.fromJson(body, NotificationListRequestModel.class);
        if (fieldsIsNotValid(requestModel)) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            final String apiKey = requestModel.getApiKey();
            final String provider = requestModel.getProvider();
            final DoctorManagerResult authResult =
                    doctorFacade.getByApi(apiKey);
            if (authResult.isSuccess()) {
                response = getNotifications(authResult, provider);
            } else {
                response = Response.status(Response.Status.FORBIDDEN).build();
            }

        }

        return response;
    }

    /**
     * Sistemden hekime ait sağlayıcı ile gönderilen bildirimleri sorgular
     * 
     * @param authResult
     * @param provider
     * @return
     */
    private Response getNotifications(final DoctorManagerResult authResult,
            final String provider) {
        Response response;
        Doctor doctor;
        doctor = authResult.getDoctor();
        LOGGER.info("The doctor {} have got notifications. Provider {}",
                doctor, provider);
        final List<Notification> notifications =
                notificationFacade.get(doctor, provider);

        if (notifications.size() == 0) {
            response = createEmptyResult();
        } else {
            response = createReponseModel(notifications);
        }
        return response;
    }

    /**
     * Alanlar geçerli değil mi?
     * 
     * @param requestModel
     * @return
     */
    private boolean fieldsIsNotValid(
            final NotificationListRequestModel requestModel) {
        return requestModel == null || requestModel.getApiKey().isEmpty()
                || requestModel.getProvider().isEmpty();
    }

    /**
     * Response modeli üretir
     * 
     * @param notifications
     * @return
     */
    private Response createReponseModel(final List<Notification> notifications) {
        final NotificationListModel listModel = new NotificationListModel();
        listModel.setCount(notifications.size());
        final List<NotificationModel> notificationModels =
                new ArrayList<NotificationModel>();
        for (final Notification notification : notifications) {
            final NotificationModel notificationModel =
                    createNotificationModel(notification);
            notificationModels.add(notificationModel);
        }
        listModel.setNotifications(notificationModels);
        final String json = gson.toJson(listModel);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    /**
     * Bildirilerden bildiri json modeli üretir
     * 
     * @param notification
     * @return
     */
    private NotificationModel createNotificationModel(
            final Notification notification) {
        final Patient patient = notification.getPatient();
        final String tckn = patient.getTckn().toString().substring(0, 4);
        final HealthDataValue healthDataValue =
                notification.getHealthDataValue();
        final HealthDataField field = healthDataValue.getField();
        final NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPatientName(tckn + "-" + patient.getFullname());
        notificationModel.setFieldTitle(field.getTitle());
        notificationModel.setValue(healthDataValue.getValue());
        notificationModel.setFieldUnit(field.getUnit());
        notificationModel.setPhone(patient.getPhone());
        notificationModel.setMobilePhone(patient.getMobilePhone());
        return notificationModel;
    }

    /**
     * Boş bildiri içeren bir sonuç üretir
     * 
     * @return
     */
    private Response createEmptyResult() {
        final NotificationListModel listModel = new NotificationListModel();
        listModel.setCount(0);
        final String json = gson.toJson(listModel);
        return Response.status(Response.Status.NOT_FOUND).entity(json).build();
    }

    /**
     * @param doctorFacade
     *            the doctorFacade to set
     */
    public void setDoctorFacade(final DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }
}
