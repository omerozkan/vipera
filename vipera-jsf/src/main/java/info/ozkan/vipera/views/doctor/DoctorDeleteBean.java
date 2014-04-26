package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.jsf.FacesMessage2;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.sun.faces.context.FacesFileNotFoundException;

/**
 * Hekim silme Bean sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorDelete")
@Scope("request")
public class DoctorDeleteBean {
    @Inject
    private DoctorFacade doctorFacade;
    private Long id;
    private Doctor doctor;

    public void loadDoctor() throws FacesFileNotFoundException {
        setDoctor(DoctorLoader.loadDoctor(id, doctorFacade));
    }

    public String delete() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final DoctorManagerResult result = doctorFacade.delete(doctor);
        if (result.isSuccess()) {
            final String message = "Silme Başarılı";
            final String detail = "Hekim " + doctor.getFullname() + " silindi!";
            context.addMessage(null, new FacesMessage2(
                    FacesMessage.SEVERITY_INFO, message, detail));
            context.getExternalContext().getFlash().setKeepMessages(true);
        } else {
            final String message = "Silme Başarısız!";
            final String detail = "Hekim silinemedi!";
            context.addMessage(null, new FacesMessage2(
                    FacesMessage.SEVERITY_ERROR, message, detail));
        }
        return "browse.html";
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
}
