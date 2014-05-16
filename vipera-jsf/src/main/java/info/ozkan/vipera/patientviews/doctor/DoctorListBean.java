package info.ozkan.vipera.patientviews.doctor;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientFacade;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.patientviews.PatientSessionBean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Hekim listesi
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorList")
@Scope("session")
public class DoctorListBean {
    /**
     * hekim listesi
     */
    private List<Doctor> doctors;
    /**
     * business
     */
    @Inject
    private DoctorPatientFacade doctorPatientFacade;

    /**
     * @return the doctors
     */
    public List<Doctor> getDoctors() {
        getDoctorFromSystem();
        return doctors;
    }

    /**
     * sistemden hekimleri sorgular
     */
    private void getDoctorFromSystem() {
        final Patient patient = PatientSessionBean.getPatient();
        doctorPatientFacade.loadDoctors(patient);
        doctors = patient.getDoctors();
    }

    /**
     * @param doctors
     *            the doctors to set
     */
    public void setDoctors(final List<Doctor> doctors) {
        this.doctors = doctors;
    }

}
