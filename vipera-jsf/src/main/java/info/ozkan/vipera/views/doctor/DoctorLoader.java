package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.entities.Doctor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.faces.context.FacesFileNotFoundException;

/**
 * Hekimler nesnesinin yüklenmesini sağlar. Genellikle parametre ile id alan
 * sayfalar için kullanılır.
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorLoader {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorLoader.class);

    /**
     * private constructor
     */
    private DoctorLoader() {

    }

    public static Doctor loadDoctor(final Long id, final DoctorFacade facade)
            throws FacesFileNotFoundException {
        if (id == null) {
            LOGGER.error("Parameter ID is empty!");
            throw new FacesFileNotFoundException();
        }
        final DoctorManagerResult result = facade.getById(id);
        if (!result.isSuccess()) {
            LOGGER.error("Doctor has not found ID: {}!", id);
            throw new FacesFileNotFoundException();
        }
        return result.getDoctor();
    }

}
