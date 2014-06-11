package info.ozkan.vipera.business.administrator;

import info.ozkan.vipera.entities.Administrator;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link AdministratorFacade} arayüzü implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("administratorFacade")
public class AdministratorFacadeImpl implements AdministratorFacade {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AdministratorFacadeImpl.class);
    /**
     * Manager
     */
    @Inject
    private AdministratorManager administratorManager;

    public AdministratorManagerResult getAll() {
        return administratorManager.getAll();
    }

    public AdministratorManagerResult update(final Administrator administrator) {
        AdministratorManagerResult result;
        try {
            result = administratorManager.update(administrator);
        } catch (final Exception e) {
            LOGGER.error("administrator {} cannot be updated", administrator, e);
            result = createNonUniqueResult();
        }
        return result;
    }

    private AdministratorManagerResult createNonUniqueResult() {
        AdministratorManagerResult result;
        result = new AdministratorManagerResult();
        result.setStatus(AdministratorManagerStatus.NON_UNIQUE);
        return result;
    }

    public AdministratorManagerResult add(final Administrator administrator) {
        AdministratorManagerResult result;
        try {
            result = administratorManager.add(administrator);
        } catch (final Exception e) {
            LOGGER.error("The new admin {} cannot be added to system",
                    administrator, e);
            result = createNonUniqueResult();
        }
        return result;
    }

}
