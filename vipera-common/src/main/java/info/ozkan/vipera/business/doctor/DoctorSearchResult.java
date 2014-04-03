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
	 * @return the doctors
	 */
    public List<Doctor> getDoctors() {
	    return doctors;
    }

	/**
	 * @param doctors the doctors to set
	 */
    public void setDoctors(List<Doctor> doctors) {
	    this.doctors = doctors;
    }

}
