package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

/**
 * DoctorManager sınıfı ile yapılan bir işlemin sonucunu üst katmana bildirmek
 * sonuç sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorManagerResult {

	/**
	 * İşlem başarılı mı?
	 */
	private boolean success;

	/**
	 * Hata mesajları
	 */
	private final List<DoctorManagerError> errors = new ArrayList<DoctorManagerError>();

	private List<Doctor> doctors = new ArrayList<Doctor>();

	/**
	 * Hata listesine yeni bir hata ekler
	 * 
	 * @param error
	 *            Hata
	 */
	public void addError(final DoctorManagerError error) {
		getErrors().add(error);
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(final boolean success) {
		this.success = success;
	}

	/**
	 * @return the errors
	 */
	public List<DoctorManagerError> getErrors() {
		return errors;
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

	public void addDoctor(final Doctor doctor) {
		doctors.add(doctor);
	}

	public Doctor getDoctor() {
		return doctors.size() == 0 ? null : doctors.get(0);
	}
}
