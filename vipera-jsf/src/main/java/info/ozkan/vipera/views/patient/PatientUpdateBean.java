package info.ozkan.vipera.views.patient;

import info.ozkan.vipera.business.patient.PatientFacade;
import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientManagerStatus;
import info.ozkan.vipera.common.EmailValidator;
import info.ozkan.vipera.entities.Authorize;
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
 * Hasta güncelleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientUpdate")
@Scope("session")
public class PatientUpdateBean {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PatientUpdateBean.class);
    /**
     * Beklenmeyen hata mesajı
     */
    private static final String UNEXPECTED_ERROR_MSG =
            "Hasta güncellenemedi! Beklenmeyen hata!";
    /**
     * Doğum tarihi geçersiz hata mesajı
     */
    private static final String BIRTH_DATE_INVALID_ERROR_MSG =
            "Girdiğiniz doğum tarihi geçersiz!";
    /**
     * Eposta geçersiz hata mesajı
     */
    private static final String EMAIL_INVALID_ERROR_MSG =
            "Girdiğiniz eposta adresi geçersiz!";
    /**
     * Boş string
     */
    private static final String EMPTY = "";
    /**
     * Hasta nesnesi
     */
    private Patient patient;
    /**
     * Hasta id
     */
    private Long id;
    /**
     * Business object
     */
    @Inject
    private PatientFacade patientFacade;
    /**
     * FacesContext
     */
    private FacesContext context;
    /**
     * Üye aktifliği
     */
    private boolean enabled;
    /**
     * hasta yeniden yüklensin mi
     */
    private boolean loadAgain = true;

    /**
     * Hastanın sistemden getirilmesini sağlar
     * 
     */
    public void loadPatient() {
        if (loadAgain) {
            patient = PatientLoader.loadPatient(patientFacade, id);
            setEnabledByPatient();
        } else {
            loadAgain = true;
        }
    }

    /**
     * Üye aktif ise {@link PatientUpdateBean#enabled} değerini true yapar
     */
    private void setEnabledByPatient() {
        if (patient.getEnable().equals(Authorize.ENABLE)) {
            enabled = true;
        }
    }

    /**
     * Hasta bilgilerinin güncellenmesini sağlar
     */
    public void save() {
        loadAgain = false;
        context = FacesContext.getCurrentInstance();
        final boolean success = checkFields();
        setPatientActivation();
        if (success) {
            updatePatient();
        }

    }

    /**
     * Hastanın üye aktifliğini nesneye atar
     */
    protected void setPatientActivation() {
        if (enabled) {
            patient.setEnable(Authorize.ENABLE);
        } else {
            patient.setEnable(Authorize.DISABLE);
        }
    }

    /**
     * Alanların kontrolünü gerçekleştirir
     * 
     * @return
     */
    private boolean checkFields() {
        boolean success = true;
        if (!EmailValidator.isValid(patient.getEmail())) {
            createErrorMessage(EMAIL_INVALID_ERROR_MSG);
            success = false;
        }
        if (!BirthDateChecker.checkBirthDateInPast(patient.getBirthDate())) {
            createErrorMessage(BIRTH_DATE_INVALID_ERROR_MSG);
            success = false;
        }
        return success;
    }

    /**
     * Hekimi günceller
     */
    private void updatePatient() {
        final PatientManagerResult result = patientFacade.update(patient);
        if (isSuccess(result)) {
            final String message =
                    String.format("%s %s güncellendi!", patient.getTckn(),
                            patient.getFullname());
            createSuccessMessage(message);
            LOGGER.info("The patient({}-{}) has been updated!",
                    patient.getTckn(), patient.getFullname());
        } else {
            createErrorMessage(UNEXPECTED_ERROR_MSG);
            LOGGER.error("The patient ({}-{}) cannot be updated!",
                    patient.getTckn(), patient.getFullname());
        }
    }

    /**
     * Güncelleme işlemini başarılı olup olmadığını belirler
     * 
     * @param result
     * @return
     */
    private boolean isSuccess(final PatientManagerResult result) {
        final PatientManagerStatus status = result.getStatus();
        return status.equals(PatientManagerStatus.SUCCESS);
    }

    /**
     * JSF bilgi mesajı ekler
     * 
     * @param message
     *            mesaj
     */
    private void createSuccessMessage(final String message) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_INFO,
                message, EMPTY));
    }

    /**
     * JSF hata mesajı ekler
     * 
     * @param message
     *            Mesaj
     */
    private void createErrorMessage(final String message) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_ERROR,
                message, EMPTY));
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

}
