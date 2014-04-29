package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.common.EmailValidator;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.jsf.FacesMessage2;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.sun.faces.context.FacesFileNotFoundException;

/**
 * Hekim güncelleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorUpdate")
@Scope("session")
public class DoctorUpdateBean implements Serializable {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorUpdateBean.class);
    /**
     * Emtpy string
     */
    private static final String EMPTY = "";
    /**
     * serial
     */
    private static final long serialVersionUID = 8328617167146313495L;
    /**
     * Eposta geçersiz mesaj özeti
     */
    private static final String EMAIL_INVALID_MSG = "Girdiğiniz eposta adresi geçersiz!";
    /**
     * Eposta geçersiz Faces mesajı
     */
    protected static final FacesMessage2 EMAIL_INVALID = new FacesMessage2(
            FacesMessage.SEVERITY_ERROR, EMAIL_INVALID_MSG, EMPTY);
    /**
     * Güncelleme işlemi başarılı olduğunda gösterilecek mesaj
     */
    protected static final FacesMessage2 SUCCESS = new FacesMessage2(
            FacesMessage.SEVERITY_INFO, EMPTY, EMPTY);
    /**
     * Hekim id
     */
    private Long id;
    /**
     * Doctor nesnesi
     */
    private Doctor doctor;
    /**
     * İşletme katmanı
     */
    @Inject
    private DoctorFacade doctorFacade;
    /**
     * Parola
     */
    private String password;
    /**
     * Parola Tekrarı
     */
    private String password2;
    /**
     * Hekim üyelik aktifliği
     */
    private boolean enable;

    /**
     * Hekim in veritabanından sorgulanıp formda gösterilmesini sağlar
     * 
     * @throws FacesFileNotFoundException
     *             Hekim bulunamadı hatası
     */
    public void loadDoctor() throws FacesFileNotFoundException {
        doctor = DoctorLoader.loadDoctor(id, doctorFacade);
    }

    /**
     * Hekimi günceller
     */
    public void save() {
        final FacesContext context = FacesContext.getCurrentInstance();
        if (!isEmailValid()) {
            context.addMessage(null, EMAIL_INVALID);
            LOGGER.error("The email '{}' is invalid!", doctor.getEmail());
            return;
        }
        if (password != null && !password.isEmpty()) {
            doctor.setPassword(password);
        }
        if (enable == true) {
            doctor.setEnabled(Doctor.ENABLE);
        }
        final DoctorManagerResult result = doctorFacade.update(doctor);
        if (result.isSuccess()) {
            LOGGER.info("The doctor {} has been updated!", doctor.getFullname());
            SUCCESS.setSummary(doctor.getFullname() + " güncellendi!");
            context.addMessage(null, SUCCESS);
        }
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
     * @return the doctorFacade
     */
    public DoctorFacade getDoctorFacade() {
        return doctorFacade;
    }

    /**
     * @param doctorFacade
     *            the doctorFacade to set
     */
    public void setDoctorFacade(final DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }

    /**
     * Üyelik aktif olduğunda true olmadığında false dönderir
     * 
     * @return
     */
    public boolean getEnable() {
        return doctor.getEnabled().equals(Doctor.ENABLE);
    }

    /**
     * TCKN numarasının dışardan değiştirilmesine izin vermez
     * 
     * @return
     */
    public Long getTckn() {
        return doctor.getTckn();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Eposta adresinin geçerli olup olmadığını kontrol eder
     * 
     * @return
     */
    public boolean isEmailValid() {
        return EmailValidator.isValid(doctor.getEmail());
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2
     *            the password2 to set
     */
    public void setPassword2(final String password2) {
        this.password2 = password2;
    }

    /**
     * @param enable
     *            the enable to set
     */
    public void setEnable(final boolean enable) {
        this.enable = enable;
    }

}
