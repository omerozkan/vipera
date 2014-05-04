package info.ozkan.vipera.views.patient;

import info.ozkan.vipera.entities.Sex;

/**
 * Hasta arama model sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
class PatientBrowseModel {
    /**
     * Cinsiyet
     */
    private Long tckn;
    /**
     * Ad
     */
    private String name;
    /**
     * Soyad
     */
    private String surname;
    /**
     * Doğum tarihi
     */
    private String birthYear;
    /**
     * Cinsiyet
     */
    private Sex sex;
    /**
     * Eposta
     */
    private String email;
    /**
     * Baba adı
     */
    private String fatherName;
    /**
     * Anne adı
     */
    private String motherName;

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
     * @return the birthYear
     */
    public String getBirthYear() {
        return birthYear;
    }

    /**
     * @param birthYear
     *            the birthYear to set
     */
    public void setBirthYear(final String birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * @return the sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(final Sex sex) {
        this.sex = sex;
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
     * @return the fatherName
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * @param fatherName
     *            the fatherName to set
     */
    public void setFatherName(final String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     * @return the motherName
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     * @param motherName
     *            the motherName to set
     */
    public void setMotherName(final String motherName) {
        this.motherName = motherName;
    }
}
