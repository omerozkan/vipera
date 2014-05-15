/**
 * 
 */
package info.ozkan.vipera.doctorviews;

import info.ozkan.vipera.entities.Doctor;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Hekim oturumu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorSession")
@Scope("session")
public class DoctorSessionBean {
    /**
     * Hekim
     */
    private Doctor doctor;

    /**
     * setup
     */
    @PostConstruct
    public void setUp() {
        doctor = getDoctor();
    }

    /**
     * Hekim tam adı
     * 
     * @return
     */
    public String getFullname() {
        return doctor.getFullname();
    }

    /**
     * Oturum açılmış hekimi dönderir
     * 
     * @return
     */
    public static Doctor getDoctor() {
        final Authentication obj =
                SecurityContextHolder.getContext().getAuthentication();
        final Object doctor = obj.getPrincipal();
        if (doctor instanceof Doctor) {
            return (Doctor) doctor;
        }
        return null;
    }
}
