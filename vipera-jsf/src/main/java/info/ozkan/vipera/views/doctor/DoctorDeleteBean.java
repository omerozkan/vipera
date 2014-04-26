package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.entities.Doctor;
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
 * Hekim silme Bean sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorDelete")
@Scope("session")
public class DoctorDeleteBean {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorDeleteBean.class);
    /**
     * Hekim silinememsi durumunda üretilen mesaj
     */
    private static final String UNSUCCESFULLY_MSG = "Hekim silinemedi!";
    /**
     * Hekim silinememsi durumunda üretilen mesaj özeti
     */
    private static final String UNSUCCESSFULL_MSG_TITLE = "Silme Başarısız!";
    /**
     * Hekimin silinmesi durumunda üretilen mesaj
     */
    private static final String SUCCESSFULL_MSG_TITLE = "Silme Başarılı";
    /**
     * İşletme katmanı nesnesi
     */
    @Inject
    private DoctorFacade doctorFacade;
    /**
     * Silmek istenilen hekimin ID'si
     */
    private Long id;
    /**
     * Silmek istenilen hekim
     */
    private Doctor doctor;
    /**
     * Kaydet butonunun aktif olup olmadığını tanımlar
     */
    private boolean disabled = false;

    /**
     * Silinmek istenen hekimi yükler eğer hekim silinmiş ise tekrar yüklenmez.
     * 
     * @throws FacesFileNotFoundException
     */
    public void loadDoctor() throws FacesFileNotFoundException {
        if (doctor == null || doctor.getId() != id) {
            setDoctor(DoctorLoader.loadDoctor(id, doctorFacade));
            disabled = false;
        }
    }

    /**
     * Silme işlemini gerçekleştirir
     */
    public void delete() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final DoctorManagerResult result = doctorFacade.delete(doctor);
        if (result.isSuccess()) {
            final String message = SUCCESSFULL_MSG_TITLE;
            final String detail = "Hekim " + doctor.getFullname() + " silindi!";
            context.addMessage(null, new FacesMessage2(
                    FacesMessage.SEVERITY_INFO, message, detail));
            setDisabled(true);
            LOGGER.info("The doctor {}-{} has been deleted!", doctor.getTckn(),
                    doctor.getFullname());
        } else {
            final String message = UNSUCCESSFULL_MSG_TITLE;
            final String detail = UNSUCCESFULLY_MSG;
            context.addMessage(null, new FacesMessage2(
                    FacesMessage.SEVERITY_ERROR, message, detail));
            LOGGER.info("The doctor {}-{} CANNOT be deleted!",
                    doctor.getTckn(), doctor.getFullname());
        }
    }

    /**
     * @return the doctorFacade
     */
    public DoctorFacade getDoctorFacade() {
        return doctorFacade;
    }

    /**
     * @param doctorFacade
     *            the doctorFacade to set
     */
    public void setDoctorFacade(final DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
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
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor
     *            the doctor to set
     */
    public void setDoctor(final Doctor doctor) {
        this.doctor = doctor;
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
}
