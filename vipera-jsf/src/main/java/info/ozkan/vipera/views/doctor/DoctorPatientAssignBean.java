package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientFacade;
import info.ozkan.vipera.business.doctorpatient.DoctorPatientManagerResult;
import info.ozkan.vipera.business.doctorpatient.DoctorPatientManagerStatus;
import info.ozkan.vipera.entities.Doctor;
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
 * Hasta - Hekim atama işlemini yapan bean sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorPatientAssign")
@Scope("session")
public class DoctorPatientAssignBean {
    /**
     * atama başarılı olduğunda gösterilecek mesaj
     */
    private static final String ASSIGN_SUCCESS_MSG_PATTERN = "Hasta %s, hekim %s'e başarı ile atandı!";
    /**
     * Hasta daha önce hekime atanmışsa gösterilecek mesaj
     */
    private static final String ASIGN_EXIST_ERROR_PATTERN = "Hasta %s, hekim %s'e daha önceden atanmış!";
    /**
     * alanlar boş olduğunda gösterilecek olan mesaj
     */
    private static final String NULL_ENTRY_ERROR_MSG = "Atama işlemi için lütfen bir hekim ve bir hasta seçiniz!";
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorPatientAssignBean.class);
    /**
     * Hekim
     */
    private Doctor doctor;
    /**
     * Hasta
     */
    private Patient patient;
    /**
     * Business
     */
    @Inject
    private DoctorPatientFacade doctorPatientFacade;

    /**
     * Hekim seçer
     * 
     * @param doctor
     */
    public void selectDoctor(final Doctor doctor) {
        this.doctor = doctor;
        LOGGER.debug("Doctor selected: {}", doctor);
    }

    /**
     * Hasta seçer
     * 
     * @param patient
     */
    public void selectPatient(final Patient patient) {
        this.patient = patient;
        LOGGER.debug("Patient selected: {}", patient);
    }

    /**
     * Atama işlemini gerçekleştirir
     */
    public void assign() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final boolean success = checkFields(context);
        if (success) {
            assignPatient(context);
        }
    }

    /**
     * Hasta atama işlemini gerçekleştirir
     * 
     * @param context
     */
    private void assignPatient(final FacesContext context) {
        final DoctorPatientManagerResult result = doctorPatientFacade.assign(
                doctor, patient);
        final DoctorPatientManagerStatus status = result.getStatus();
        if (status.equals(DoctorPatientManagerStatus.SUCCESS)) {
            createSuccessMessage(context);
            LOGGER.info("{} assigned to {}!", patient, doctor);
        } else {
            createAssignmentExistErrorMsg(context);
            createAssignmentExistLog();
        }
    }

    /**
     * Alanları kontrol eder
     * 
     * @param context
     * @return
     */
    private boolean checkFields(final FacesContext context) {
        boolean success = true;
        if (entriesNull()) {
            createErrorMessage(context, NULL_ENTRY_ERROR_MSG);
            success = false;
            LOGGER.debug("Doctor and patient cannot be null!");
        }
        return success;
    }

    /**
     * Hasta daha önce atanmış hata mesajını oluşturur
     * 
     * @param context
     */
    private void createAssignmentExistErrorMsg(final FacesContext context) {
        final String summary = String.format(ASIGN_EXIST_ERROR_PATTERN,
                patient.getFullname(), doctor.getFullname());
        createErrorMessage(context, summary);
    }

    /**
     * Hasta daha önce atanmış debug logunu oluşturur
     */
    private void createAssignmentExistLog() {
        LOGGER.debug("{} has already assigned to {}!", patient, doctor);
    }

    /**
     * Hata mesajı oluşturur
     * 
     * @param context
     *            FacesContext
     * @param summary
     *            hata mesajı
     */
    private void createErrorMessage(final FacesContext context,
            final String summary) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_ERROR,
                summary, ""));
    }

    /**
     * Başarı mesajı oluşturur
     * 
     * @param context
     *            Faces Context
     */
    private void createSuccessMessage(final FacesContext context) {
        final String summary = String.format(ASSIGN_SUCCESS_MSG_PATTERN,
                doctor.getFullname(), patient.getFullname());
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_INFO,
                summary, ""));
    }

    /**
     * hekim ve hastanın null olup olmadığını kontrol eder
     * 
     * @return
     */
    private boolean entriesNull() {
        return doctor == null || patient == null;
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    private boolean patientAssignedBefore() {
        return doctor.getPatients().contains(patient);
    }

    /**
     * @param doctor
     *            the doctor to set
     */
    public void setDoctor(final Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @param doctorPatientFacade
     *            the doctorPatientFacade to set
     */
    public void setDoctorPatientFacade(
            final DoctorPatientFacade doctorPatientFacade) {
        this.doctorPatientFacade = doctorPatientFacade;
    }

    /**
     * @param patient
     *            the patient to set
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }
}
