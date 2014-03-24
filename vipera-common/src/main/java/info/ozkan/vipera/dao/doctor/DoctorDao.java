package info.ozkan.vipera.dao.doctor;

import info.ozkan.vipera.entities.Doctor;

/**
 * Hekimler üzerinde işlem yapan veri katmanı sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorDao {

	/**
	 * Veritabanına hekim ekler
	 * 
	 * @param doctor
	 * @return
	 */
	DoctorDaoResult add(Doctor doctor);

}
