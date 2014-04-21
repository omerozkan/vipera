/**
 * 
 */
package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.models.DoctorBrowseModel;

/**
 * Doctor Facade sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorFacade {
	/**
	 * Veritabanına yeni bir hekim ekler
	 * 
	 * @param doctor
	 * @return
	 */
	DoctorManagerResult add(Doctor doctor);

	/**
	 * Veritabanı üzerinden hekim arar
	 * 
	 * @param model
	 * @return
	 */
	DoctorManagerResult search(DoctorBrowseModel model);
}
