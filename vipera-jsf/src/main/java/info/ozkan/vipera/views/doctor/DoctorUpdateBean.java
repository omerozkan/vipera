package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.business.notification.NotificationSettingFacade;
import info.ozkan.vipera.common.EmailValidator;
import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorNotificationSetting;
import info.ozkan.vipera.entities.NotificationSetting;
import info.ozkan.vipera.jsf.FacesMessage2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

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
    private static final String EMAIL_INVALID_MSG =
            "Girdiğiniz eposta adresi geçersiz!";
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
     * bildirim ayarları işletme nesnesi
     */
    @Inject
    private NotificationSettingFacade notificationSettingFacade;

    /**
     * Hekim in veritabanından sorgulanıp formda gösterilmesini sağlar
     */
    public void loadDoctor() {
        doctor = DoctorLoader.loadDoctor(id, doctorFacade);
        final Authorize enabled = doctor.getEnabled();
        enable = enabled.equals(Authorize.ENABLE);
        initializeNotificationSettings();
    }

    /**
     * Bildirim ayarlarını ilklendirir
     */
    protected void initializeNotificationSettings() {
        final List<NotificationSetting> notificationSettings =
                notificationSettingFacade.getAll();
        final Map<String, DoctorNotificationSetting> doctorNotificationSettings =
                createMapFromSettings();
        final List<DoctorNotificationSetting> newSettings =
                refreshDoctorNotificationSettings(notificationSettings,
                        doctorNotificationSettings);
        doctor.setSettings(newSettings);
    }

    /**
     * hekimin bildirim ayarlarını yeniler
     * 
     * @param systemSettings
     * @param doctorSettings
     * @return
     */
    private List<DoctorNotificationSetting> refreshDoctorNotificationSettings(
            final List<NotificationSetting> systemSettings,
            final Map<String, DoctorNotificationSetting> doctorSettings) {
        final List<DoctorNotificationSetting> newSettings =
                new ArrayList<DoctorNotificationSetting>();
        for (final NotificationSetting notificationSetting : systemSettings) {
            final String providerId = notificationSetting.getProviderId();
            if (doctorSettings.containsKey(providerId)) {
                newSettings.add(doctorSettings.get(providerId));
            } else {
                final DoctorNotificationSetting setting =
                        createNewNotificationSetting(providerId);
                newSettings.add(setting);
            }
        }
        return newSettings;
    }

    /**
     * yeni bir bildirim ayarı üretir
     * 
     * @param providerId
     * @return
     */
    private DoctorNotificationSetting createNewNotificationSetting(
            final String providerId) {
        final DoctorNotificationSetting setting =
                new DoctorNotificationSetting();
        setting.setDoctor(doctor);
        setting.setProviderId(providerId);
        setting.setEnabled(false);
        return setting;
    }

    /**
     * bildirimlerden map üretir
     * 
     * @return
     */
    private Map<String, DoctorNotificationSetting> createMapFromSettings() {
        final Map<String, DoctorNotificationSetting> map =
                new HashMap<String, DoctorNotificationSetting>();
        final List<DoctorNotificationSetting> settings = doctor.getSettings();
        for (final DoctorNotificationSetting setting : settings) {
            map.put(setting.getProviderId(), setting);
        }
        return map;
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
        setDoctorActivation();
        final DoctorManagerResult result = doctorFacade.update(doctor);
        if (result.isSuccess()) {
            LOGGER.info("The doctor {} has been updated!", doctor.getFullname());
            SUCCESS.setSummary(doctor.getFullname() + " güncellendi!");
            context.addMessage(null, SUCCESS);
        }
    }

    /**
     * Hekimin hesap aktifliğini tanımlar
     */
    private void setDoctorActivation() {
        if (enable) {
            doctor.setEnabled(Authorize.ENABLE);
        } else {
            doctor.setEnabled(Authorize.DISABLE);
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
        return enable;
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
