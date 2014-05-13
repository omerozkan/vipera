package info.ozkan.vipera.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Hasta sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "PATIENTS")
public class Patient implements Serializable, Cloneable {
    /**
     * Serial
     */
    private static final long serialVersionUID = 7501937124111247012L;
    /**
     * TCKN attribute
     */
    public static final String TCKN = "tckn";
    /**
     * NAME
     */
    public static final String NAME = "name";
    /**
     * ENABLE
     */
    public static final String ENABLE = "enable";
    /**
     * MOBILE PHONE
     */
    public static final String MOBILE_PHONE = "mobilePhone";
    /**
     * PHONE
     */
    public static final String PHONE = "phone";
    /**
     * MOTHER NAME
     */
    public static final String MOTHER_NAME = "motherName";
    /**
     * FATHER NAME
     */
    public static final String FATHER_NAME = "fatherName";
    /**
     * BIRTH PLACE
     */
    public static final String BIRTH_PLACE = "birthPlace";
    /**
     * BIRTH DATE
     */
    public static final String BIRTH_DATE = "birthDate";
    /**
     * SEX
     */
    public static final String SEX = "sex";
    /**
     * EMAIL
     */
    public static final String EMAIL = "email";
    /**
     * SURNAME
     */
    public static final String SURNAME = "surname";
    /**
     * PASSWORD
     */
    public static final String PASSWORD = "password";
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    /**
     * TC Kimlik No
     */
    @Column(name = "tckn", unique = true, nullable = false)
    private Long tckn;
    /**
     * Parola
     */
    @Column(name = "password")
    private String password;
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
     * Eposta adresi
     */
    @Column(name = "email")
    private String email;
    /**
     * Cinsiyet
     */
    @Column(name = "sex")
    @Enumerated(EnumType.ORDINAL)
    private Sex sex;
    /**
     * Doğum tarihi
     */
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    /**
     * Doğum yeri
     */
    @Column(name = "birth_place")
    private String birthPlace;
    /**
     * Baba adı
     */
    @Column(name = "father_name")
    private String fatherName;
    /**
     * Anne adı
     */
    @Column(name = "mother_name")
    private String motherName;
    /**
     * Sabit telefon
     */
    @Column(name = "phone")
    private String phone;
    /**
     * Cep telefonu
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;
    /**
     * Hesabın yetkinliği
     */
    @Column(name = "enable")
    @Enumerated(EnumType.ORDINAL)
    private Authorize enable;
    /**
     * Hekimler
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "patients")
    private List<Doctor> doctors;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Device> devices;

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Patient) {
            final Patient patient = (Patient) obj;
            final Long tckn = patient.tckn;
            return tckn.equals(this.tckn);
        }
        return false;
    }

    @Override
    public String toString() {
        return tckn + "-" + getFullname();
    }

    /**
     * @return the birtDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @return the birthPlace
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * @return the doctors
     */
    public List<Doctor> getDoctors() {
        return doctors;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the enable
     */
    public Authorize getEnable() {
        return enable;
    }

    /**
     * @return the fatherName
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * Ad ve soyadı tek bir string olarak dönderir
     * 
     * @return
     */
    public String getFullname() {
        return name + " " + surname;
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
     * @return the motherName
     */
    public String getMotherName() {
        return motherName;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the sex
     */
    public Sex getSex() {
        return sex;
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

    @Override
    public int hashCode() {
        final int prime = 71;
        return tckn.intValue() * prime;
    }

    /**
     * @param birtDate
     *            the birtDate to set
     */
    public void setBirthDate(final Date birtDate) {
        birthDate = birtDate;
    }

    /**
     * @param birthPlace
     *            the birthPlace to set
     */
    public void setBirthPlace(final String birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * @param doctors
     *            the doctors to set
     */
    public void setDoctors(final List<Doctor> doctors) {
        this.doctors = doctors;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @param enable
     *            the enable to set
     */
    public void setEnable(final Authorize enable) {
        this.enable = enable;
    }

    /**
     * @param fatherName
     *            the fatherName to set
     */
    public void setFatherName(final String fatherName) {
        this.fatherName = fatherName;
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
     * @param motherName
     *            the motherName to set
     */
    public void setMotherName(final String motherName) {
        this.motherName = motherName;
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
     * @param phone
     *            the phone to set
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(final Sex sex) {
        this.sex = sex;
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

}
