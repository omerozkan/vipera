package info.ozkan.vipera.views.patient;

import info.ozkan.vipera.business.patient.PatientFacade;
import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientManagerStatus;
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
 * Hasta silme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientDelete")
@Scope("session")
public class PatientDeleteBean {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PatientDeleteBean.class);
    /**
     * Silme başarısız ise gösterilecek olan mesajın özeti
     */
    private static final String DELETE_UNSUCCESSFULL_MSG_SUMMARY = "Silme Başarısız!";
    /**
     * Silme işlemi başarılı ise gösterilecek olan mesajın özeti
     */
    private static final String DELETED_MSG_SUMMARY = "Hasta silindi!";
    /**
     * Silme işlemi gerçekleştikten sonra form tekrar yüklenir. Fakat silme
     * işlemi tekrar yapılamaz.
     */
    private boolean disabled;
    /**
     * Id
     */
    private Long id;
    /**
     * Hasta
     */
    private Patient patient;
    /**
     * İşletme nesnesi
     */
    @Inject
    private PatientFacade patientFacade;

    /**
     * Hasta yükleme işlemi yapar
     * 
     * @throws FacesFileNotFoundException
     */
    public void loadPatient() throws FacesFileNotFoundException {
        if (differentId()) {
            setPatient(PatientLoader.loadPatient(patientFacade, getId()));
            setDisabled(false);
        }
    }

    /**
     * Başarılı mesajı oluşturur
     * 
     * @param context
     */
    private void addSuccessfullMessage(final FacesContext context) {
        final String detail = String.format(
                "Hasta %s-%s sistemden kaldırıldı!", getPatient().getTckn(),
                getPatient().getFullname());
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_INFO,
                DELETED_MSG_SUMMARY, detail));
    }

    /**
     * Başarısız mesajı oluşturur
     * 
     * @param context
     */
    private void addUnsuccessfullMessage(final FacesContext context) {
        final String detail = "Silme işlemi başarısız oldu! Hasta daha önce silinmiş olabilir!";
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_FATAL,
                DELETE_UNSUCCESSFULL_MSG_SUMMARY, detail));
    }

    /**
     * Silme işlemini gerçekleştirir
     */
    public void delete() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final PatientManagerResult result = patientFacade.delete(getPatient());
        if (deleteSuccess(result)) {
            addSuccessfullMessage(context);
            LOGGER.info("The patient '{} - {}' has deleted!",
                    patient.getTckn(), patient.getFullname());
            disabled = true;
        } else {
            addUnsuccessfullMessage(context);
            LOGGER.error("The patient '{} - {}' cannot be deleted!",
                    patient.getTckn(), patient.getFullname());
        }
    }

    /**
     * Silme işlemi başarılı ise true dönderir
     * 
     * @param result
     * @return
     */
    private boolean deleteSuccess(final PatientManagerResult result) {
        final PatientManagerStatus status = result.getStatus();
        return status.equals(PatientManagerStatus.SUCCESS);
    }

    /**
     * Bir önceki yüklenen hastanın farklı olup olmadığı kontrol edilir.
     * 
     * @return
     */
    private boolean differentId() {
        return getPatient() == null || !getPatient().getId().equals(getId());
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * @param disabled
     *            the disabled to set
     */
    public void setDisabled(final boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param patient
     *            the patient to set
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * @param patientFacade
     *            the patientFacade to set
     */
    public void setPatientFacade(final PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    /**
     * Hasta silindi ise Silindi değilse Evet yazısı dönderir
     * 
     * @return
     */
    public String getButtonValue() {
        return disabled ? "Silindi" : "Evet";
    }
}
