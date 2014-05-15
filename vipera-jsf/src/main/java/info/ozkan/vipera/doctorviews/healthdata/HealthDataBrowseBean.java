package info.ozkan.vipera.doctorviews.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataFacade;
import info.ozkan.vipera.business.healthdata.HealthDataResult;
import info.ozkan.vipera.business.healthdata.HealthDataSearchFilter;
import info.ozkan.vipera.business.patient.PatientFacade;
import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.doctorviews.DoctorSession;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.Patient;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

/**
 * Sağlık verilerini görüntüleyen arama yapan ekran
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataBrowse")
@Scope("session")
public class HealthDataBrowseBean {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(HealthDataBrowseBean.class);
    /**
     * boş string
     */
    private static final String EMPTY = "";
    /**
     * veri bulunamadı
     */
    private static final String MSG_DATA_NOT_FOUND =
            "Kayıtlı veri bulunmamaktadır!";
    /**
     * hasta seçiniz mesajı
     */
    private static final String MSG_SELECT_PATIENT = "Lütfen hasta seçiniz!";
    /**
     * Hasta id
     */
    private Long id;
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
     * Sağlık alanı listesi
     */
    private List<HealthData> healthDataList;
    /**
     * healthData
     */
    @Inject
    private HealthDataFacade healthDataFacade;
    /**
     * patient
     */
    @Inject
    private PatientFacade patientFacade;

    /**
     * hasta önceden tanımlanmışsa yükleme işlemini gerçekleştirir
     */
    public void loadPatient() {
        if (id != null) {
            final Doctor doctor = DoctorSession.getDoctor();
            final PatientManagerResult result =
                    patientFacade.getById(id, doctor);
            if (result.isSuccess()) {
                patient = result.getPatient();
            }
        }

    }

    /**
     * hasta seçme işlemini gerçekleştirir
     * 
     * @param patient
     */
    public void selectPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * arama işlemini gerçekleştirir
     */
    public void search() {
        final HealthDataSearchFilter filter = new HealthDataSearchFilter();
        final FacesContext context = FacesContext.getCurrentInstance();
        if (patient == null) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, MSG_SELECT_PATIENT, EMPTY));
        } else {
            filter.setPatient(patient);
            filter.setStartDate(startDate);
            filter.setEndDate(endDate);
            final HealthDataResult result = healthDataFacade.find(filter);
            healthDataList = result.getHealthDataList();
            checkEmptySearch(context);
            LOGGER.info("Found {} health data.", healthDataList.size());
        }
    }

    /**
     * arama sonucu boş olursa mesaj gösterir
     * 
     * @param context
     */
    private void checkEmptySearch(final FacesContext context) {
        if (healthDataList.isEmpty()) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, MSG_DATA_NOT_FOUND, EMPTY));
        }
    }

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

    /**
     * @return the healthDataList
     */
    public List<HealthData> getHealthDataList() {
        return healthDataList;
    }

    /**
     * @param healthDataList
     *            the healthDataList to set
     */
    public void setHealthDataList(final List<HealthData> healthDataList) {
        this.healthDataList = healthDataList;
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

}
