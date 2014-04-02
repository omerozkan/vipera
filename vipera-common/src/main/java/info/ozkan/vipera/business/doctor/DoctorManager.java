package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;

/**
 * Hekimler üzerinde çeşitli işlemler yapar
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorManager {
	/**
	 * Doctor nesnesini veritabanına kaydeder
	 * 
	 * @param doctor
	 *            Doctor nesnesi
	 * @return işlem sonucu
	 */
	public DoctorManagerResult add(Doctor doctor);

	/**
	 * Veritabanından TCKN'na ait hekim nesnesini dönderir
	 * 
	 * @param tckn
	 * @return
	 */
	public DoctorManagerResult get(Long tckn);
}
