package info.ozkan.vipera.doctorviews.doctor;

import info.ozkan.vipera.doctorviews.DoctorSessionBean;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.views.doctor.DoctorUpdateBean;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Hekim profil ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorProfile")
@Scope("session")
public class DoctorProfileBean extends DoctorUpdateBean {
    /**
     * serial
     */
    private static final long serialVersionUID = -8218581090974560451L;

    /**
     * hekim setter
     */
    @PostConstruct
    public void setUp() {
        final Doctor doctor = DoctorSessionBean.getDoctor();
        setDoctor(doctor);
        initializeNotificationSettings();
    }

}
