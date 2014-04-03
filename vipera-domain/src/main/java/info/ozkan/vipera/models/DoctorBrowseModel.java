package info.ozkan.vipera.models;

import info.ozkan.vipera.entities.DoctorTitle;

/**
 * Hekimler üzerinde arama işlemleri yapabilmek için gereken model sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorBrowseModel {
	/**
	 * TC Kimlik No
	 */
	private Long tckn;
	/**
	 * Hekim Adı
	 */
	private String name;
	/**
	 * Hekim Soyadı
	 */
	private String surname;
	/**
	 * Hekimin Ünvanı
	 */
	private DoctorTitle title;
	/**
	 * Eposta adresi
	 */
	private String email;
	/**
	 * Üyelik Aktifliği
	 */
	private Integer enabled;
	/**
	 * @return the tckn
	 */
    public Long getTckn() {
	    return tckn;
    }
	/**
	 * @param tckn the tckn to set
	 */
    public void setTckn(Long tckn) {
	    this.tckn = tckn;
    }
	/**
	 * @return the name
	 */
    public String getName() {
	    return name;
    }
	/**
	 * @param name the name to set
	 */
    public void setName(String name) {
	    this.name = name;
    }
	/**
	 * @return the surname
	 */
    public String getSurname() {
	    return surname;
    }
	/**
	 * @param surname the surname to set
	 */
    public void setSurname(String surname) {
	    this.surname = surname;
    }
	/**
	 * @return the title
	 */
    public DoctorTitle getTitle() {
	    return title;
    }
	/**
	 * @param title the title to set
	 */
    public void setTitle(DoctorTitle title) {
	    this.title = title;
    }
	/**
	 * @return the email
	 */
    public String getEmail() {
	    return email;
    }
	/**
	 * @param email the email to set
	 */
    public void setEmail(String email) {
	    this.email = email;
    }
	/**
	 * @return the active
	 */
    public Integer getActive() {
	    return enabled;
    }
	/**
	 * @param active the active to set
	 */
    public void setActive(Integer active) {
	    this.enabled = active;
    }
}
