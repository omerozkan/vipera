package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerError;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.common.EmailValidator;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorTitle;
import info.ozkan.vipera.jsf.FacesMessage2;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
public class DoctorAddBean {
	private static final int TCKN_LENGTH = 11;
	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(DoctorAddBean.class);
	/**
	 * Kaydedildi mesaj deseni
	 */
	private static final String SAVED_MSG_PATTERN = "%d - %s %s %s";
	/**
	 * Kaydedildi mesajın içeriği
	 */
	private static final String SAVED_MSG = "kaydedildi!";
	/**
	 * TCKN'e ait kayıltı hekim var hata mesajı
	 */
	private static final String TCKN_HAS_EXIST_MSG = "Girdiğiniz TC Kimlik no ile kayıtlı bir başka hekim bulunmaktadır!";
	/**
	 * Geçersiz eposta hata mesajı
	 */
	private static final String INVALID_EMAIL_MSG = "Girdiğiniz eposta adresi geçerli değil!";
	/**
	 * Parola eşleşmeme hata mesajı
	 */
	private static final String PASSWORDS_DONT_MATH_MSG = "Girdiğiniz parolalar birbiriyle uyuşmuyor!";
	/**
	 * Geçersiz TC Kimlik no hata mesajı
	 */
	private static final String INVALID_TCKN_MSG = "TC Kimlik Numarasını 11 haneli ve sayılardan oluşmalıdır!";
	/**
	 * Geçersiz TC Kimlik No hatası
	 */
	protected static final FacesMessage2 INVALID_TCKN = new FacesMessage2(
	        FacesMessage.SEVERITY_ERROR, INVALID_TCKN_MSG, "");
	/**
	 * Parola eşleşmeme hatası
	 */
	protected static final FacesMessage2 PASSWORDS_DONT_MATCH = new FacesMessage2(
	        FacesMessage.SEVERITY_ERROR, PASSWORDS_DONT_MATH_MSG, "");
	/**
	 * Eposta geçersiz hatası
	 */
	protected static final FacesMessage2 EMAIL_INVALID = new FacesMessage2(
	        FacesMessage.SEVERITY_ERROR, INVALID_EMAIL_MSG, "");
	/**
	 * Sistem TCKN ile kayıtlı hekim hatası
	 */
	protected static final FacesMessage2 TCKN_HAS_EXIST = new FacesMessage2(
	        FacesMessage.SEVERITY_ERROR, TCKN_HAS_EXIST_MSG, "");
	/**
	 * Hekim kaydedildikten sonra gösterilecek mesaj
	 */
	protected static final FacesMessage2 SUCCESS = new FacesMessage2(
	        FacesMessage.SEVERITY_INFO, "", null);
	/**
	 * Doktor domain nesnesi
	 */
	private Doctor doctor = new Doctor();
	/**
	 * Üyeliği aktif olup olmadığı
	 */
	private boolean enable = true;
	/**
	 * Parola doğrulaması
	 */
	private String passwordConfirm;
	/**
	 * İşletme katmanı
	 */
	@Inject
	private DoctorFacade doctorFacade;

	/**
	 * Hekimi sisteme kaydeder
	 * 
	 * @param ae
	 */
	public void save() {
		final FacesContext context = FacesContext.getCurrentInstance();
		if (!checkFields(context)) {
			return;
		}
		final DoctorManagerResult result = doctorFacade.add(doctor);
		if (!result.isSuccess()) {
			if (result.getErrors().contains(DoctorManagerError.TCKN_HAS_EXIST)) {
				context.addMessage(null, TCKN_HAS_EXIST);
			}
		} else {
			SUCCESS.setSummary(String.format(SAVED_MSG_PATTERN,
			        doctor.getTckn(), doctor.getName(), doctor.getSurname(),
			        SAVED_MSG));
			context.addMessage(null, SUCCESS);
			doctor = new Doctor();
		}
	}

	/**
	 * Alanların geçerliliğini kontrol eder
	 * 
	 * @param context
	 *            FacesContext nesnesi
	 * @return true ise geçerli false ise geçerli dğeil
	 */
	private boolean checkFields(final FacesContext context) {
		boolean fieldHasValid = true;
		if (doctor.getTckn().toString().length() != TCKN_LENGTH) {
			LOGGER.info(
			        "Doctors have to have 11 length TKCN but it's length: {}",
			        doctor.getTckn().toString().length());
			context.addMessage(null, INVALID_TCKN);
			fieldHasValid = false;
		}
		if (!doctor.getPassword().equals(passwordConfirm)) {
			context.addMessage(null, PASSWORDS_DONT_MATCH);
			fieldHasValid = false;
		}
		if (doctor.getEmail() != null && !doctor.getEmail().isEmpty()
		        && !isValidEmail(doctor.getEmail())) {
			context.addMessage(null, EMAIL_INVALID);
			fieldHasValid = false;
		}
		return fieldHasValid;
	}

	/**
	 * Eposta adresinin geçerli olup olmadığını kontrol eder
	 * 
	 * @param email
	 *            eposta adresi
	 * @return başarılı ise true
	 */
	private boolean isValidEmail(final String email) {
		final EmailValidator validator = new EmailValidator();
		return validator.validate(email);
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
		if (enable) {
			doctor.setEnabled(1);
		} else {
			doctor.setEnabled(0);
		}
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

	/**
	 * Doktor Ünvanları
	 * 
	 * @return
	 */
	public DoctorTitle[] getDoctorTitles() {
		return DoctorTitle.values();
	}

	/**
	 * @param doctorFacade
	 *            the doctorFacade to set
	 */
	public void setDoctorFacade(final DoctorFacade doctorFacade) {
		this.doctorFacade = doctorFacade;
	}
}