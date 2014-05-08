package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
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

import com.sun.faces.context.FacesFileNotFoundException;

/**
 * Hekime ait hasta listesi ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorPatientList")
@Scope("session")
public class DoctorPatientListBean {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorPatientListBean.class);
    /**
     * Hata mesajı
     */
    private static final String ERROR_MSG = "Hasta ataması daha önce kaldırıldı!";
    /**
     * Başarılı sonuç mesaj deseni
     */
    private static final String SUCCESS_MSG_PATTERN = "Hasta %s, %s hasta listesinden kaldırıldı!";
    /**
     * Hekim
     */
    private Doctor doctor;
    /**
     * Hekim Id
     */
    private Long id;
    /**
     * Hekim yükleme işlemini yapan business object
     */
    @Inject
    private DoctorFacade doctorFacade;
    /**
     * hekime ait hastaları dönderen business object
     */
    @Inject
    private DoctorPatientFacade doctorPatientFacade;
    /**
     * Hekim {@link DoctorPatientListBean#id} değerinden tekrar üretilsin mi?
     */
    private boolean loadDoctor = true;

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Hekim nesnesinin yüklenmesini sağlar
     * 
     * @throws FacesFileNotFoundException
     */
    public void loadDoctor() throws FacesFileNotFoundException {
        if (id != null && loadDoctor) {
            doctor = DoctorLoader.loadDoctor(id, doctorFacade);
            doctorPatientFacade.loadPatients(doctor);
            resetIdForNextRequest();
        } else if (id == null && loadDoctor && doctor != null) {
            doctorPatientFacade.loadPatients(doctor);
        }
        loadDoctor = true;
        LOGGER.debug("Loaded doctor: {}", doctor);
    }

    /**
     * sonraki request'lerde idlerin null kalmasını garantiler
     */
    private void resetIdForNextRequest() {
        id = null;
    }

    /**
     * Hekim seçer
     * 
     * @param doctor
     */
    public void selectDoctor(final Doctor doctor) {
        this.doctor = doctor;
        doctorPatientFacade.loadPatients(doctor);
        loadDoctor = false;
        LOGGER.debug("Selected doctor: {}", doctor);
    }

    /**
     * Atamayı kaldırır
     * 
     * @param patient
     *            Hasta
     */
    public void removeAssignment(final Patient patient) {
        final FacesContext context = FacesContext.getCurrentInstance();
        final DoctorPatientManagerResult result = doctorPatientFacade
                .removeAssignment(doctor, patient);
        final DoctorPatientManagerStatus status = result.getStatus();
        if (status.equals(DoctorPatientManagerStatus.SUCCESS)) {
            final String summary = String.format(SUCCESS_MSG_PATTERN,
                    patient.getFullname(), doctor.getFullname());
            createSuccessMessage(context, summary);
            LOGGER.info("The patient {} has removed from {}", patient, doctor);
        } else {
            createErrorMessage(context, ERROR_MSG);
            LOGGER.info("The patient {} cannot removed from {}", patient,
                    doctor);
        }
        loadDoctor = false;
    }

    /**
     * Hata mesajı oluşturur
     * 
     * @param context
     * @param summary
     */
    private void createErrorMessage(final FacesContext context,
            final String summary) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_ERROR,
                summary, ""));
    }

    /**
     * Info mesajı oluşturur
     * 
     * @param context
     * @param summary
     */
    private void createSuccessMessage(final FacesContext context,
            final String summary) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_INFO,
                summary, ""));
    }

    /**
     * @param doctor
     *            the doctor to set
     */
    public void setDoctor(final Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

}
