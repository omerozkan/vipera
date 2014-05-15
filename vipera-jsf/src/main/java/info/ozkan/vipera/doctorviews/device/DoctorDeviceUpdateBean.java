package info.ozkan.vipera.doctorviews.device;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientFacade;
import info.ozkan.vipera.doctorviews.PatientAssignmentChecker;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.views.device.DeviceUpdateBean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.sun.faces.context.FacesFileNotFoundException;

/**
 * Hekim paneli cihaz güncelleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorDeviceUpdate")
@Scope("session")
public class DoctorDeviceUpdateBean extends DeviceUpdateBean {
    private static final String MSG_READONLY =
            "Cihaz sizin hastanız olmadığından cihaz üzerinde değişiklik yapamazsınız!";
    /**
     * doktora ait hastalar
     */
    @Inject
    private DoctorPatientFacade doctorPatientFacade;
    /**
     * kaydetme butonun pasif olup olmadığını tanımlar
     */
    private boolean disabled = false;

    @Override
    public void loadDevice() throws FacesFileNotFoundException {
        super.loadDevice();
        checkPatientForDoctor();
    }

    /**
     * Cihazın sahibi olan hastanın hekime atanıp atanmadığını kontrol eder
     */
    private void checkPatientForDoctor() {
        final Device device = getDevice();
        final Patient patient = device.getPatient();
        final boolean patientAssigned =
                PatientAssignmentChecker.check(doctorPatientFacade, patient);
        if (!patientAssigned) {
            final FacesContext context = FacesContext.getCurrentInstance();
            final String message = MSG_READONLY;
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, message, ""));
            disabled = true;
        } else {
            disabled = false;
        }
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
