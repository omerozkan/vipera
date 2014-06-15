package info.ozkan.vipera.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Hekim domain sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "DOCTORS")
public class Doctor implements Serializable, Cloneable {
    /**
     * Serial
     */
    private static final long serialVersionUID = 7281884028602017476L;
    /**
     * ID
     */
    public static final String ID = "id";
    /**
     * TCKN
     */
    public static final String TCKN = "tckn";
    /**
     * NAME
     */
    public static final String NAME = "name";
    /**
     * SURNAME
     */
    public static final String SURNAME = "surname";
    /**
     * EMAIL
     */
    public static final String EMAIL = "email";
    /**
     * PASSWORD
     */
    public static final String PASSWORD = "password";
    /**
     * TITLE
     */
    public static final String TITLE = "title";
    /**
     * DIPLOMA NO
     */
    public static final String DIPLOMA_NO = "diplomaNo";
    /**
     * PROVINCE
     */
    public static final String PROVINCE = "province";
    /**
     * PHONE
     */
    public static final String PHONE = "phone";
    /**
     * MOBILE PHONE
     */
    public static final String MOBILE_PHONE = "mobilePhone";
    /**
     * WEB SAYFASI
     */
    public static final String WEBPAGE = "webpage";
    /**
     * ÜYELİK AKTİFLİĞİ
     */
    public static final String ENABLED = "enabled";
    /**
     * Üyelik aktif olduğunda alınan değer
     */
    public static final Integer ENABLE = 1;
    /**
     * Identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    /**
     * TC Kimlik No
     */
    @Column(name = "tckn", unique = true)
    private Long tckn;
    /**
     * Adı
     */
    @Column(name = "name")
    private String name;
    /**
     * Soyadı
     */
    @Column(name = "surname")
    private String surname;
    /**
     * Eposta
     */
    @Column(name = "email")
    private String email;
    /**
     * Parola
     */
    @Column(name = "password")
    private String password;
    /**
     * Ünvan
     */
    @Column(name = "title")
    @Enumerated(EnumType.ORDINAL)
    private DoctorTitle title;
    /**
     * Diploma No
     */
    @Column(name = "diploma_no")
    private String diplomaNo;
    /**
     * Uzmanlık Alanı
     */
    @Column(name = "province")
    private String province;
    /**
     * Telefon
     */
    @Column(name = "phone")
    private String phone;
    /**
     * Cep Telefonu
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;
    /**
     * Web sayfası
     */
    @Column(name = "webpage")
    private String webpage;
    /**
     * Aktiflik
     */
    @Column(name = "enabled")
    @Enumerated(EnumType.ORDINAL)
    private Authorize enabled;
    /**
     * Hastalar
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "DOCTOR_PATIENT", joinColumns = { @JoinColumn(
            referencedColumnName = "id", name = "doctor_id") },
            inverseJoinColumns = { @JoinColumn(referencedColumnName = "id",
                    name = "patient_id") })
    private List<Patient> patients;
    /**
     * Doktor bildirim ayaları
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private List<DoctorNotificationSetting> settings;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Hekim nesnelerinin eşit olup olmadığını kontrol eder
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Doctor) {
            final Doctor doctor = (Doctor) obj;
            if (tckn.equals(doctor.getTckn())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the diplomaNo
     */
    public String getDiplomaNo() {
        return diplomaNo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Hekimin adını Ünvan Ad Soyad şeklinde alır
     * 
     * @return
     */
    public String getFullname() {
        return String.format("%s %s %s", title.getTitle(), name, surname);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the patients
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return the tckn
     */
    public Long getTckn() {
        return tckn;
    }

    /**
     * @return the title
     */
    public DoctorTitle getTitle() {
        return title;
    }

    /**
     * @return the webpage
     */
    public String getWebpage() {
        return webpage;
    }

    @Override
    public int hashCode() {
        final int result = tckn.intValue();
        final int prime = 41;
        return result * prime;
    }

    /**
     * @return Üyeliği aktif ise true
     */
    public boolean isEnable() {
        return getEnabled().equals(Authorize.ENABLE);
    }

    @Override
    public String toString() {
        return tckn + "-" + getFullname();
    }

    /**
     * @param diplomaNo
     *            the diplomaNo to set
     */
    public void setDiplomaNo(final String diplomaNo) {
        this.diplomaNo = diplomaNo;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @param mobilePhone
     *            the mobilePhone to set
     */
    public void setMobilePhone(final String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @param patients
     *            the patients to set
     */
    public void setPatients(final List<Patient> patients) {
        this.patients = patients;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * @param province
     *            the province to set
     */
    public void setProvince(final String province) {
        this.province = province;
    }

    /**
     * @param surname
     *            the surname to set
     */
    public void setSurname(final String surname) {
        this.surname = surname;
    }

    /**
     * @param tckn
     *            the tckn to set
     */
    public void setTckn(final Long tckn) {
        this.tckn = tckn;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(final DoctorTitle title) {
        this.title = title;
    }

    /**
     * @param webpage
     *            the webpage to set
     */
    public void setWebpage(final String webpage) {
        this.webpage = webpage;
    }

    /**
     * @return the enabled
     */
    public Authorize getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(final Authorize enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the settings
     */
    public List<DoctorNotificationSetting> getSettings() {
        return settings;
    }

    /**
     * @param settings
     *            the settings to set
     */
    public void setSettings(final List<DoctorNotificationSetting> settings) {
        this.settings = settings;
    }
}
