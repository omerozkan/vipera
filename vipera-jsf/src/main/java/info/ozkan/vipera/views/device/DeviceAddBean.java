package info.ozkan.vipera.views.device;

import info.ozkan.vipera.business.device.DeviceFacade;
import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.business.device.DeviceManagerStatus;
import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.jsf.FacesMessage2;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

/**
 * Cihaz ekleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("deviceAddBean")
@Scope("session")
public class DeviceAddBean {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DeviceAddBean.class);
    /**
     * api parolası uzunluğu
     */
    private static final int PASSWORD_LENGTH = 16;
    /**
     * api anahtarı uzunluğu
     */
    private static final int APIKEY_LENGTH = 16;
    /**
     * hasta buluamadı
     */
    private static final String ERROR_PATIENT_NOT_FOUND = "Hasta bulunamadı!";
    /**
     * parola hatası
     */
    private static final String ERROR_API_PASSWORD = "API parola hatası!";
    /**
     * api anahtarı hatası
     */
    private static final String ERROR_API_KEY = "API anahtar hatası!";
    /**
     * hasta null hatası
     */
    private static final String ERROR_PATIENT_NULL = "Hasta boş olamaz!";
    /**
     * ekleme başarılı mesajı
     */
    private static final String INFO_SUCCESSFULL = "Ekleme başarılı!";
    /**
     * benzersiz api anahtarı mesajı
     */
    private static final String MSG_NON_UNIQUE_API_KEY =
            "API anahtarı benzersiz olmalıdır. Lütfen yeni bir anahtar girin!";
    /**
     * Hasta bulunamadı mesajı
     */
    private static final String MSG_PATIENT_NOT_FOUND =
            "Cihaz eklemek istediğiniz hasta bulunamadı! Lütfen tekrar kontrol ediniz!";
    /**
     * yeni hasta kaydedildi mesajı
     */
    private static final String MSG_NEW_PATIENT_SAVED_PATTERN =
            "%s hastaya ait yeni cihaz %s eklendi!";
    /**
     * api parolası mesajı
     */
    private static final String MSG_API_PASSWORD =
            "Lütfen API parolasını elle girmeyip üretilen parolayı kullanınız!";
    /**
     * api key uzunluk hata mesajı
     */
    private static final String MSG_API_KEY_LENGTH =
            "API anahtarı 16 karakterden az olamaz";
    /**
     * hasta seçiniz hata mesajı
     */
    private static final String MSG_SELECT_PATIENT = "Lütfen hasta seçiniz.";
    /**
     * Hasta
     */
    private Patient patient;
    /**
     * Cihaz
     */
    private Device device = new Device();
    /**
     * işletme katmanı nesnesi
     */
    @Inject
    private DeviceFacade deviceFacade;

    /**
     * Hasta seçer
     * 
     * @param patient
     */
    public void selectPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * anahtar üretir
     */
    public void generateKey() {
        final String key = PasswordGenerator.generate();
        device.setApiKey(key);
    }

    /**
     * parola üretir
     */
    public void generatePassword() {
        final String password = PasswordGenerator.generate();
        device.setApiPassword(password);
    }

    /**
     * Cihazı kaydeder
     */
    public void save() {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (checkFields(context)) {
            addDevice(context);
        }
    }

    /**
     * Sisteme yeni bir cihaz ekler
     * 
     * @param context
     */
    private void addDevice(final FacesContext context) {
        device.setEnabled(Authorize.ENABLE);
        device.setPatient(patient);
        final DeviceManagerResult result = deviceFacade.add(device);
        final DeviceManagerStatus status = result.getStatus();
        if (result.isSuccess()) {
            final String detail =
                    String.format(MSG_NEW_PATIENT_SAVED_PATTERN,
                            patient.getFullname(), device.getApiKey());
            createInfoMessage(context, INFO_SUCCESSFULL, detail);
            LOGGER.info("A new device {} added to {} ", device.getApiKey(),
                    patient);
            patient = null;
            device = new Device();
        } else if (status.equals(DeviceManagerStatus.PATIENT_NOT_EXIST)) {
            createErrorMessage(context, ERROR_PATIENT_NOT_FOUND,
                    MSG_PATIENT_NOT_FOUND);
            LOGGER.error("The patient not found!");
        } else {
            createErrorMessage(context, ERROR_API_KEY, MSG_NON_UNIQUE_API_KEY);
            LOGGER.error("The api key is not unique!");
        }
    }

    /**
     * Alanların geçerli olup olmadığını kontrol eder
     * 
     * @param context
     * @return
     */
    private boolean checkFields(final FacesContext context) {
        boolean validFields = true;
        if (patient == null) {
            validFields = false;
            createErrorMessage(context, ERROR_PATIENT_NULL, MSG_SELECT_PATIENT);
        }
        if (device.getApiKey().length() < APIKEY_LENGTH) {
            validFields = false;
            createErrorMessage(context, ERROR_API_KEY, MSG_API_KEY_LENGTH);
        }
        if (device.getApiPassword().length() < PASSWORD_LENGTH) {
            validFields = false;
            createErrorMessage(context, ERROR_API_PASSWORD, MSG_API_PASSWORD);
        }
        return validFields;
    }

    /**
     * Bilgi mesajı oluşturur
     * 
     * @param context
     * @param summary
     *            Mesaj özeti
     * @param detail
     *            Mesaj detayı
     */
    private void createInfoMessage(final FacesContext context,
            final String summary, final String detail) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_INFO,
                summary, detail));
    }

    /**
     * Hata mesajı oluşturur
     * 
     * @param context
     * @param summary
     *            mesaj özeti
     * @param detail
     *            mesaj detayı
     */
    private void createErrorMessage(final FacesContext context,
            final String summary, final String detail) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_ERROR,
                summary, detail));
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient
     *            the patient to set
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(final Device device) {
        this.device = device;
    }
}
