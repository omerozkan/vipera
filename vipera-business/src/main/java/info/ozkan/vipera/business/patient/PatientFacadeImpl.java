package info.ozkan.vipera.business.patient;

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
            LOGGER.debug("The patien cannot be added!", e);
        }
        return result;
    }

    public PatientManagerResult search(final PatientSearchFilter filter) {
        return patientManager.search(filter);
    }

}
