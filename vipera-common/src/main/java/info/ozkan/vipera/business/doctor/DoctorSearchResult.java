package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;

import java.util.List;

/**
 * Veritabanından hekimleri arama sonucunda elde edilen sonuç sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorSearchResult {
	/**
	 * Hekimler
	 */
	private List<Doctor> doctors;

	/**
	 * DoctorSearchResult nesnesi oluşturur default constructor
	 */
	public DoctorSearchResult() {

	}

	/**
	 * DoctorSearchResult nesnesi oluşturur
	 * 
	 * @param doctors
	 *            Hekim Listesi
	 */
	public DoctorSearchResult(final List<Doctor> doctors) {
		this.doctors = doctors;
	}

	/**
	 * @return the doctors
	 */
	public List<Doctor> getDoctors() {
		return doctors;
	}

	/**
	 * @param doctors
	 *            the doctors to set
	 */
	public void setDoctors(final List<Doctor> doctors) {
		this.doctors = doctors;
	}

}
