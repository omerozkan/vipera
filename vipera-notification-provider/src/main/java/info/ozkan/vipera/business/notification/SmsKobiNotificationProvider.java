package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.Patient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SMSKobi'den alinan sms sistemi sağlayisi
 * 
 * @author Ömer Özkan
 * 
 */
public class SmsKobiNotificationProvider implements NotificationProvider {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(SmsKobiNotificationProvider.class);
    /**
     * SMS Kobi Url
     */
    private static final String SMS_KOBI_URL =
            "http://api.smskobi.com/api/xml_api.php";
    /**
     * SMS kobi pattern
     */
    private static final String SMS_PATTERN =
            "Sayin %s, hastaniz %s-%s a ait %s degeri %s ve sinirlarin disinda oldugu saptanmistir.";
    /**
     * Hekim adı sınırı
     */
    private static final int DOCTOR_NAME_LIMIT = 25;
    /**
     * hasta tckn sınırı
     */
    private static final int PATIENT_TCKN_LIMIT = 4;
    /**
     * hasta adı sınırı
     */
    private static final int PATIENT_NAME_LIMIT = 20;
    /**
     * alan adı sınırı
     */
    private static final int FIELD_NAME_LIMIT = 25;
    /**
     * değer sınırı
     */
    private static final int VALUE_LIMIT = 8;
    /**
     * api anahtarı
     */
    private String apiKey;
    /**
     * parola
     */
    private String password;

    public void send(final Notification notification) {
        final String message = generateMessage(notification);
        final Doctor doctor = notification.getDoctor();
        final String mobileNumber = doctor.getMobilePhone();

        if (mobileNumber == null || mobileNumber.isEmpty()) {
            LOGGER.info(
                    "The doctor ({}) has no defined mobile phone number! SMS notification cannot be sent!",
                    doctor);
        } else {
            try {
                final String xml = createXML(message, mobileNumber);
                LOGGER.info("XML: {}", xml);
                LOGGER.info("Notification has been sent to doctor {}", doctor);
                final Request request =
                        Request.Post(SMS_KOBI_URL).bodyString(xml,
                                ContentType.APPLICATION_XML);
                String result;
                result = request.execute().returnContent().asString();
                LOGGER.debug("SMSKobi result: {}", result);
            } catch (final ClientProtocolException e) {
                LOGGER.error("SMS Kobi service has failed", e);
            } catch (final IOException e) {
                LOGGER.error("SMS Kobi service has failed", e);
            }

        }

    }

    /**
     * XML üretir
     * 
     * @param message
     * @param mobileNumber
     * @return
     */
    private String createXML(final String message, final String mobileNumber) {
        final String xmlPattern =
                "<SMS>" + "<oturum>" + "<kullanici>%s</kullanici>"
                        + "<sifre>%s</sifre>" + "</oturum>" + "<mesaj>"
                        + "<baslik>%s</baslik>" + "<metin>%s</metin>"
                        + "<alicilar>%s</alicilar>" + "</mesaj>" + "</SMS>";
        final String xml =
                String.format(xmlPattern, apiKey, password, "VIPERA", message,
                        mobileNumber);
        return xml;
    }

    /**
     * Gönderilecek smsi üretir
     * 
     * @param notification
     * @return
     */
    private String generateMessage(final Notification notification) {
        final Doctor doctor = notification.getDoctor();
        final Patient patient = notification.getPatient();
        final HealthDataValue value = notification.getHealthDataValue();
        final HealthDataField field = value.getField();
        final String doctorName = getDoctorName(doctor);

        final String patientTckn =
                patient.getTckn().toString().substring(0, PATIENT_TCKN_LIMIT);

        final String patientName = getPatientName(patient);

        final String fieldName = getFieldName(field);

        final String valueString = getValue(value);

        String message =
                String.format(SMS_PATTERN, doctorName, patientTckn,
                        patientName, fieldName, valueString);
        message = filterTurkishCharacter(message);
        return message;
    }

    /**
     * Türkçe karakterleri filtreler
     * 
     * @param message
     * @return
     */
    private String filterTurkishCharacter(final String message) {
        String result = message;
        result = result.replaceAll("Ö", "O");
        result = result.replaceAll("Ü", "U");
        result = result.replaceAll("Ç", "C");
        result = result.replaceAll("Ç", "C");
        result = result.replaceAll("Ş", "S");
        result = result.replaceAll("Ğ", "G");
        result = result.replaceAll("İ", "I");
        return result;
    }

    /**
     * Değeri üretir
     * 
     * @param value
     * @return
     */
    private String getValue(final HealthDataValue value) {
        final Double fieldValue = value.getValue();

        String valueString = fieldValue.toString();

        if (valueString.length() > VALUE_LIMIT) {
            valueString = valueString.substring(VALUE_LIMIT);
        }
        return valueString.toUpperCase();
    }

    /**
     * hekim adını üretir
     * 
     * @param doctor
     * @return
     */
    private String getDoctorName(final Doctor doctor) {
        String doctorName = doctor.getFullname();
        if (doctorName.length() > DOCTOR_NAME_LIMIT) {
            doctorName = doctor.getName() + " " + doctor.getSurname();
            if (doctorName.length() > DOCTOR_NAME_LIMIT) {
                doctorName =
                        doctor.getName().substring(0, 1) + ". "
                                + doctor.getSurname();
            }
            if (doctorName.length() > DOCTOR_NAME_LIMIT) {
                doctorName = doctorName.substring(0, DOCTOR_NAME_LIMIT);
            }
        }
        return doctorName.toUpperCase();
    }

    /**
     * alanı üretir
     * 
     * @param field
     * @return
     */
    private String getFieldName(final HealthDataField field) {
        String fieldName = field.getTitle();
        if (fieldName.length() > FIELD_NAME_LIMIT) {
            fieldName = field.getName();
        }
        if (fieldName.length() > FIELD_NAME_LIMIT) {
            fieldName = field.getName().substring(0, FIELD_NAME_LIMIT);
        }
        return fieldName.toUpperCase();
    }

    /**
     * hasta adını üretir
     * 
     * @param patient
     * @return
     */
    private String getPatientName(final Patient patient) {
        String patientName = patient.getFullname();
        if (patientName.length() > PATIENT_NAME_LIMIT) {
            patientName =
                    patient.getName().substring(0, 1) + ". "
                            + patient.getSurname();
        }
        if (patientName.length() > PATIENT_NAME_LIMIT) {
            patientName = patientName.substring(0, PATIENT_NAME_LIMIT);
        }
        return patientName.toUpperCase();
    }

    public void setKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
