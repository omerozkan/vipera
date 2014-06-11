package info.ozkan.vipera.business.administrator;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link AdministratorFacade} arayüzü implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("administratorFacade")
public class AdministratorFacadeImpl implements AdministratorFacade {

    /**
     * Manager
     */
    @Inject
    private AdministratorManager administratorManager;

    public AdministratorManagerResult getAll() {
        return administratorManager.getAll();
    }

}
