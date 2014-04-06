package info.ozkan.vipera.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hekim domain sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "DOCTORS")
public class Doctor implements Serializable {
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
	 * Identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = ID)
	private Long id;
	/**
	 * TC Kimlik No
	 */
	@Column(name = TCKN, unique = true)
	private Long tckn;
	/**
	 * Adı
	 */
	@Column(name = NAME)
	private String name;
	/**
	 * Soyadı
	 */
	@Column(name = SURNAME)
	private String surname;
	/**
	 * Eposta
	 */
	@Column(name = EMAIL)
	private String email;
	/**
	 * Parola
	 */
	@Column(name = PASSWORD)
	private String password;
	/**
	 * Ünvan
	 */
	@Column(name = TITLE)
	@Enumerated(EnumType.ORDINAL)
	private DoctorTitle title;
	/**
	 * Diploma No
	 */
	@Column(name = DIPLOMA_NO)
	private String diplomaNo;
	/**
	 * Uzmanlık Alanı
	 */
	@Column(name = PROVINCE)
	private String province;
	/**
	 * Telefon
	 */
	@Column(name = PHONE)
	private String phone;
	/**
	 * Cep Telefonu
	 */
	@Column(name = MOBILE_PHONE)
	private String mobilePhone;
	/**
	 * Web sayfası
	 */
	@Column(name = WEBPAGE)
	private String webpage;
	/**
	 * Aktiflik
	 */
	@Column(name = ENABLED)
	private Integer enabled;

	/**
	 * @return the tckn
	 */
	public Long getTckn() {
		return tckn;
	}

	/**
	 * @param tckn
	 *            the tckn to set
	 */
	public void setTckn(final Long tckn) {
		this.tckn = tckn;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
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
	 * @return the title
	 */
	public DoctorTitle getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final DoctorTitle title) {
		this.title = title;
	}

	/**
	 * @return the diplomaNo
	 */
	public String getDiplomaNo() {
		return diplomaNo;
	}

	/**
	 * @param diplomaNo
	 *            the diplomaNo to set
	 */
	public void setDiplomaNo(final String diplomaNo) {
		this.diplomaNo = diplomaNo;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(final String province) {
		this.province = province;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(final String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone
	 *            the mobilePhone to set
	 */
	public void setMobilePhone(final String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return the webpage
	 */
	public String getWebpage() {
		return webpage;
	}

	/**
	 * @param webpage
	 *            the webpage to set
	 */
	public void setWebpage(final String webpage) {
		this.webpage = webpage;
	}

	/**
	 * @return the enable
	 */
	public Integer getEnabled() {
		return enabled;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnabled(final Integer enable) {
		enabled = enable;
	}

	/**
	 * @return Üyeliği aktif ise true
	 */
	public boolean isEnable() {
		return enabled == 1;
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
	 * Hekimin adını Ünvan Ad Soyad şeklinde alır
	 * 
	 * @return
	 */
	public String getFullname() {
		return String.format("%s %s %s", title.getTitle(), name, surname);
	}
}
