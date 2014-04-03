/**
 * 
 */
package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;

/**
 * Doctor Facade sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorFacade {
	DoctorManagerResult add(Doctor doctor);
}
