package info.ozkan.vipera.doctorviews.patient;

import info.ozkan.vipera.doctorviews.DoctorSessionBean;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.views.doctor.DoctorPatientAssignBean;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Hekim, hasta atama ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientAssign")
@Scope("session")
public class PatientAssignBean extends DoctorPatientAssignBean {
    /**
     * Oturum açmış hekimi atar
     */
    @PostConstruct
    public void setUp() {
        final Doctor doctor = DoctorSessionBean.getDoctor();
        setDoctor(doctor);
    }
}
