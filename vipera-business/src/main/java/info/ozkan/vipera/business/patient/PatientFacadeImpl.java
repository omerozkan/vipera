package info.ozkan.vipera.business.patient;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hasta üzerinde CRUD işlemleri yapan Facade sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientFacade")
public class PatientFacadeImpl implements PatientFacade {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PatientFacadeImpl.class);
    /**
     * Manager
     */
    @Inject
    private PatientManager patientManager;

    public PatientManagerResult add(final Patient patient) {
        PatientManagerResult result;
        try {
            result = patientManager.add(patient);
        } catch (final Exception e) {
            result = new PatientManagerResult();
            result.setStatus(PatientManagerStatus.TCKN_HAS_EXIST);
            LOGGER.debug("The patient cannot be added!", e);
        }
        return result;
    }

    /**
     * {@link PatientManagerStatus#UNEXPECTED_ERROR} mesajı içeren bir
     * {@link PatientManagerResult} nesnesi oluşturur
     * 
     * @return
     */
    private PatientManagerResult createUnexpectedResult() {
        PatientManagerResult result;
        result = new PatientManagerResult();
        result.setStatus(PatientManagerStatus.UNEXPECTED_ERROR);
        return result;
    }

    public PatientManagerResult delete(final Patient patient) {
        PatientManagerResult result;
        try {
            result = patientManager.delete(patient);
        } catch (final Exception e) {
            result = createUnexpectedResult();
            LOGGER.error("The patient cannot be deleted!", e);
        }
        return result;
    }

    public PatientManagerResult getById(final Long id) {
        return patientManager.getById(id);
    }

    public PatientManagerResult search(final PatientSearchFilter filter) {
        return patientManager.search(filter);
    }

    public PatientManagerResult update(final Patient patient) {
        PatientManagerResult result;
        try {
            result = patientManager.update(patient);
        } catch (final Exception e) {
            result = createUnexpectedResult();
            LOGGER.error("The patient cannot be updated!", e);
        }
        return result;
    }

    public PatientManagerResult search(final PatientSearchFilter filter,
            final Doctor doctor) {
        return patientManager.search(filter, doctor);
    }

}
