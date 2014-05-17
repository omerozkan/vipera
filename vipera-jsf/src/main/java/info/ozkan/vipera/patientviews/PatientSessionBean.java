package info.ozkan.vipera.patientviews;

import info.ozkan.vipera.entities.Patient;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Hasta oturum bilgisi
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientSession")
@Scope("session")
public class PatientSessionBean {
    /**
     * Hasta
     */
    private Patient patient;

    /**
     * setup
     */
    @PostConstruct
    public void setUp() {
        patient = getPatient();
    }

    /**
     * Hasta tam adı
     * 
     * @return
     */
    public String getFullname() {
        return patient.getFullname();
    }

    /**
     * Oturum açılmış hastayı dönderir
     * 
     * @return
     */
    public static Patient getPatient() {
        final Authentication obj =
                SecurityContextHolder.getContext().getAuthentication();
        final Object patient = obj.getPrincipal();
        if (patient instanceof Patient) {
            return (Patient) patient;
        }
        return null;
    }
}
