package info.ozkan.vipera.views.patient;

import info.ozkan.vipera.business.patient.PatientFacade;
import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientSearchFilter;
import info.ozkan.vipera.entities.Patient;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hasta arama ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientBrowse")
public class PatientBrowseBean {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PatientBrowseBean.class);
    /**
     * TCKN uzunluğı
     */
    private static final int TCKN_LENGTH = 11;
    /**
     * Model
     */
    private final PatientBrowseModel model = new PatientBrowseModel();
    /**
     * Arama sonucu listesi
     */
    private List<Patient> result;
    /**
     * Business
     */
    @Inject
    private PatientFacade patientFacade;

    /**
     * Arama işlemini gerçekleştirir
     */
    public void search() {
        final PatientSearchFilter filter = createFilterFromModel();
        final PatientManagerResult list = getPatientFacade().search(filter);
        result = list.getPatients();
        LOGGER.info("Found {} patients.", result.size());
    }

    /**
     * {@link PatientBrowseModel} nesnesini {@link PatientSearchFilter}
     * nesnesine dönüştürür
     * 
     * @return
     */
    protected PatientSearchFilter createFilterFromModel() {
        final PatientSearchFilter filter = new PatientSearchFilter();
        if (model.getTckn().toString().length() == TCKN_LENGTH) {
            filter.addFilter(Patient.TCKN, model.getTckn());
        }
        if (checkNonEmpty(model.getName())) {
            filter.addFilter(Patient.NAME, model.getName());
        }
        if (checkNonEmpty(model.getSurname())) {
            filter.addFilter(Patient.SURNAME, model.getSurname());
        }
        if (checkNonEmpty(model.getEmail())) {
            filter.addFilter(Patient.EMAIL, model.getEmail());
        }
        if (checkNonEmpty(model.getFatherName())) {
            filter.addFilter(Patient.FATHER_NAME, model.getFatherName());
        }
        if (checkNonEmpty(model.getMotherName())) {
            filter.addFilter(Patient.MOTHER_NAME, model.getMotherName());
        }
        if (model.getSex() != null) {
            filter.addFilter(Patient.SEX, model.getSex().getKey());
        }
        if (checkNonEmpty(model.getBirthYear())) {
            filter.addFilter(Patient.BIRTH_DATE, model.getBirthYear());
        }
        return filter;
    }

    /**
     * Bir string'in boş olup olmadığını kontrol eder
     * 
     * @param string
     *            string
     * @return
     */
    private boolean checkNonEmpty(final String string) {
        return !string.isEmpty();
    }

    /**
     * @return the result
     */
    public List<Patient> getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(final List<Patient> result) {
        this.result = result;
    }

    /**
     * @return the model
     */
    public PatientBrowseModel getModel() {
        return model;
    }

    /**
     * @return the patientFacade
     */
    protected PatientFacade getPatientFacade() {
        return patientFacade;
    }

    /**
     * @param patientFacade
     *            the patientFacade to set
     */
    protected void setPatientFacade(final PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

}
