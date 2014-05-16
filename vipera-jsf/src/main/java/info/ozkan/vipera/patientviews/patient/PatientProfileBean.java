package info.ozkan.vipera.patientviews.patient;

import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.patientviews.PatientSessionBean;
import info.ozkan.vipera.views.patient.PatientUpdateBean;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Hasta profil ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientProfile")
@Scope("session")
public class PatientProfileBean extends PatientUpdateBean {
    /**
     * Session
     */
    @PostConstruct
    public void setUp() {
        final Patient patient = PatientSessionBean.getPatient();
        setPatient(patient);
    }

    @Override
    protected void setPatientActivation() {
        getPatient().setEnable(Authorize.ENABLE);
    }
}
