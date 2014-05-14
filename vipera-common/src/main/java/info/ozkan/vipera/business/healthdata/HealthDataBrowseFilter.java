package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.Patient;

import java.util.Date;

/**
 * Sağlık verisi arama filtresi
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataBrowseFilter {
    /**
     * Hasta
     */
    private Patient patient;
    /**
     * Başlangıç tarihi
     */
    private Date startDate;
    /**
     * Bitiş tarihi
     */
    private Date endDate;

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient
     *            the patient to set
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }
}
