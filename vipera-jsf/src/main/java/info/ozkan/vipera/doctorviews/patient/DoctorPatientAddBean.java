package info.ozkan.vipera.doctorviews.patient;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientFacade;
import info.ozkan.vipera.doctorviews.DoctorSession;
import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.views.patient.PatientAddBean;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Hekim, hasta ekleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorPatientAdd")
@Scope("session")
public class DoctorPatientAddBean extends PatientAddBean {

    /**
     * Serial
     */
    private static final long serialVersionUID = 4606586958934508698L;
    /**
     * Facade
     */
    @Inject
    private DoctorPatientFacade doctorPatientAssignFacade;

    @Override
    public void save() {
        patient.setEnable(Authorize.ENABLE);
        super.save();
        if (patientSaved) {
            final Doctor doctor = DoctorSession.getDoctor();
            doctorPatientAssignFacade.assign(doctor, addedPatient);
        }
    }
}
