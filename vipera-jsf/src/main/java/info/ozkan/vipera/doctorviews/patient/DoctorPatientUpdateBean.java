package info.ozkan.vipera.doctorviews.patient;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientFacade;
import info.ozkan.vipera.doctorviews.PatientAssignmentChecker;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.views.patient.PatientUpdateBean;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.sun.faces.context.FacesFileNotFoundException;

/**
 * Hekim, hasta güncelleme ekranı: Yönetim panelindeki ekrandan farkı hekimin
 * sadece kendi hastasını güncelleyebilmesidir.
 * 
 * @author Ömer Özkan
 * 
 * 
 */
@Named("doctorPatientUpdate")
@Scope("session")
public class DoctorPatientUpdateBean extends PatientUpdateBean {
    /**
     * business
     */
    @Inject
    private DoctorPatientFacade doctorPatientFacade;

    @Override
    public void loadPatient() throws FacesFileNotFoundException {
        super.loadPatient();
        final Patient patient = getPatient();
        final boolean checkAssignment =
                PatientAssignmentChecker.check(doctorPatientFacade, patient);
        if (!checkAssignment) {
            throw new FacesFileNotFoundException();
        }
    }
}
