package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorTitle;
import info.ozkan.vipera.models.DoctorBrowseModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/**
 * Hekimler üzerinde arama işlemi yapan Bean sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorBrowse")
public class DoctorBrowseBean {
	/**
	 * Arama model sınıfı nesnesi
	 */
	private DoctorBrowseModel model = new DoctorBrowseModel();
	/**
	 * Üye aktif mi?
	 */
	private boolean active;

	/**
	 * Arama sonucu
	 */
	private List<Doctor> result;

	/**
	 * @return the model
	 */
	public DoctorBrowseModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(final DoctorBrowseModel model) {
		this.model = model;
	}

	/**
	 * Hekim Ünvanları
	 * 
	 * @return
	 */
	public DoctorTitle[] getDoctorTitles() {
		return DoctorTitle.values();
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	public void search() {
		result = new ArrayList<Doctor>();
		final Doctor doctor = new Doctor();
		doctor.setName("Ömer");
		doctor.setSurname("Özkan");
		doctor.setTckn(18217084416l);
		doctor.setTitle(DoctorTitle.SPECIALIST);
		doctor.setProvince("Testing");
		doctor.setEmail("omer@ozkan.info");
		doctor.setEnabled(1);
		result.add(doctor);
		System.out.println("çağrıldı");
	}

	/**
	 * @return the result
	 */
	public List<Doctor> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(final List<Doctor> result) {
		this.result = result;
	}
}
