package info.ozkan.vipera.entities;

import java.io.Serializable;

/**
 * Hekim domain sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class Doctor implements Serializable {
	/**
	 * TC Kimlik No
	 */
	private Long tckn;
	/**
	 * Adı
	 */
	private String name;
	/**
	 * Soyadı
	 */
	private String surname;
	/**
	 * Eposta
	 */
	private String email;
	/**
	 * Parola
	 */
	private String password;
	/**
	 * Ünvan
	 */
	private String title;
	/**
	 * Diploma No
	 */
	private String diplomaNo;
	/**
	 * Uzmanlık Alanı
	 */
	private String province;
	/**
	 * Telefon
	 */
	private String phone;
	/**
	 * Cep Telefonu
	 */
	private String mobilePhone;
	/**
	 * Web sayfası
	 */
	private String webpage;
	/**
	 * Aktiflik
	 */
	private int enable;

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
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title) {
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
	public int getEnable() {
		return enable;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(final int enable) {
		this.enable = enable;
	}

	/**
	 * @return Üyeliği aktif ise true
	 */
	public boolean isEnable() {
		return enable == 1;
	}
}
