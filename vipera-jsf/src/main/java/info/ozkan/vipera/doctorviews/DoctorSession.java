/**
 * 
 */
package info.ozkan.vipera.doctorviews;

import info.ozkan.vipera.entities.Doctor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Oturumu açılan hekim nesnesini dönderen utility sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorSession {
    /**
     * constructor
     */
    private DoctorSession() {
    }

    /**
     * Oturum açılmış hekimi dönderir
     * 
     * @return
     */
    public static Doctor getDoctor() {
        final Authentication obj = SecurityContextHolder.getContext()
                .getAuthentication();
        final Object doctor = obj.getPrincipal();
        if (doctor instanceof Doctor) {
            return (Doctor) doctor;
        }
        return null;
    }
}
