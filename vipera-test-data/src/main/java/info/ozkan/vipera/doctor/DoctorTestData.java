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
	/**
	 * Gregory House test id
	 */
	public static int HOUSE = 0;
	/**
	 * Ismail Demirci test id
	 */
	public static int DEMIRCI = 1;

	/**
	 * private constructor
	 */
	private DoctorTestData() {
	}

	/**
	 * İsmail Demirci adında bir hekim nesnesi oluşturur
	 * 
	 * @return
	 */
	private static Doctor createDemirci() {
		final Doctor demirci = new Doctor();
		demirci.setTckn(12345678902L);
		demirci.setPassword("password");
		demirci.setEmail("ismail@drdemirci.com");
		demirci.setName("Ismail");
		demirci.setSurname("Demirci");
		demirci.setTitle(DoctorTitle.SPECIALIST);
		demirci.setDiplomaNo("12345");
		demirci.setProvince("Dahiliye");
		demirci.setPhone("+905555555");
		demirci.setMobilePhone("+905553333333");
		return demirci;
	}

	/**
	 * Gregory House adında bir hekim nesnesi oluşturur
	 * 
	 * @return
	 */
	private static Doctor createHouse() {
		final Doctor house = new Doctor();
		house.setTckn(12345678901L);
		house.setPassword("password");
		house.setEmail("doctor@doctor.com");
		house.setName("Gregory");
		house.setSurname("House");
		house.setTitle(DoctorTitle.SPECIALIST);
		house.setDiplomaNo("12345");
		house.setProvince("Bulaşıcı Hastalıklar ve Nefroloji");
		house.setWebpage("http://www.greghouse.com");
		house.setPhone("+905555555");
		house.setMobilePhone("+905553333333");
		return house;
	}

	/**
	 * Test Hekim verisi al
	 * 
	 * @param id
	 *            0 -> Gregory House, 1 -> Ismail Demirci
	 * @return
	 */
	public static Doctor getTestData(final int id) {
		if (HOUSE == id) {
			return createHouse();
		} else {
			return createDemirci();
		}
	}

}
