package info.ozkan.vipera.doctor;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorTitle;

/**
 * Hekimlerle ilgili test verileri
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorTestData {

	public static Doctor getTestData() {
		final Doctor doctor = new Doctor();
		doctor.setTckn(12345678901L);
		doctor.setPassword("password");
		doctor.setEmail("doctor@doctor.com");
		doctor.setName("Gregory");
		doctor.setSurname("House");
		doctor.setTitle(DoctorTitle.SPECIALIST);
		doctor.setDiplomaNo("12345");
		doctor.setProvince("Bulaşıcı Hastalıklar ve Nefroloji");
		doctor.setWebpage("http://www.greghouse.com");
		doctor.setPhone("+905555555");
		doctor.setMobilePhone("+905553333333");
		return doctor;
	}
}
