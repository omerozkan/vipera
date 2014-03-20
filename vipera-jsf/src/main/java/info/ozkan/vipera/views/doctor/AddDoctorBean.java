package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.entities.Doctor;

import java.awt.event.ActionListener;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Doktor ekleme bean sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("addDoctor")
public class AddDoctorBean {
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(AddDoctorBean.class);
	/**
	 * Doktor domain nesnesi
	 */
	private Doctor doctor = new Doctor();
	private boolean enable = true;
	private String passwordConfirm;

	public void save(final ActionListener ae) {
		LOGGER.info("result is " + (isEnable() ? "true" : "false"));
	}

	/**
	 * @return the doctor
	 */
	public Doctor getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor
	 *            the doctor to set
	 */
	public void setDoctor(final Doctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(final boolean enable) {
		this.enable = enable;
	}

	/**
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * @param passwordConfirm
	 *            the passwordConfirm to set
	 */
	public void setPasswordConfirm(final String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}
