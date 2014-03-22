package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;

/**
 * Hekimler üzerinde çeşitli işlemler yapar
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorManager {
	public DoctorManagerResult save(Doctor doctor);
}
