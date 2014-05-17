package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.jsf.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hekimler nesnesinin yüklenmesini sağlar. Genellikle parametre ile id alan
 * sayfalar için kullanılır.
 * 
 * @author Ömer Özkan
 * 
 */
final class DoctorLoader {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorLoader.class);

    /**
     * private constructor
     */
    private DoctorLoader() {

    }

    /**
     * ID'e ait hekimi dönderir
     * 
     * @param id
     * @param facade
     * @return
     */
    public static Doctor loadDoctor(final Long id, final DoctorFacade facade) {
        if (id == null) {
            LOGGER.error("Parameter ID is empty!");
            throw new NotFoundException();
        }
        final DoctorManagerResult result = facade.getById(id);
        if (!result.isSuccess()) {
            LOGGER.error("Doctor has not found ID: {}!", id);
            throw new NotFoundException();
        }
        return result.getDoctor();
    }

}
