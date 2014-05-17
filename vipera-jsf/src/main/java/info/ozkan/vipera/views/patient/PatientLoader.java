package info.ozkan.vipera.views.patient;

import info.ozkan.vipera.business.patient.PatientFacade;
import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientManagerStatus;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.jsf.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hasta yükleme işlemini gerçekleştiren utility sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public final class PatientLoader {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(PatientLoader.class);

    /**
     * private constructor
     */
    private PatientLoader() {

    }

    /**
     * Id'ye ait hastayı dönderir
     * 
     * @param patientFacade
     *            işletme katmanı nesnesi
     * @param id
     *            Hasta id
     * @return hasta nesnesi
     */
    public static Patient loadPatient(final PatientFacade patientFacade,
            final Long id) {
        if (id == null) {
            LOGGER.error("Parameter Id is empty!");
            throw new NotFoundException();
        }
        final PatientManagerResult result = patientFacade.getById(id);
        if (!isSuccess(result)) {
            LOGGER.error("Patient has not found ID: {}!", id);
            throw new NotFoundException();
        }
        return result.getPatient();
    }

    /**
     * Hastanın bulunup bulunmadığını tanımlar
     * 
     * @param result
     * @return
     */
    private static boolean isSuccess(final PatientManagerResult result) {
        final PatientManagerStatus status = result.getStatus();
        return status.equals(PatientManagerStatus.SUCCESS);
    }
}
