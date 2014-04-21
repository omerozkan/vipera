/**
 * 
 */
package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.models.DoctorBrowseModel;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * DoctorFacade işletme sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorFacade")
public class DoctorFacadeImpl implements DoctorFacade {
    @Inject
    private DoctorManager doctorManager;

    /*
     * (non-Javadoc)
     * 
     * @see
     * info.ozkan.vipera.business.doctor.DoctorFacade#add(info.ozkan.vipera.
     * entities.Doctor)
     */
    public DoctorManagerResult add(final Doctor doctor) {
        try {
            return doctorManager.add(doctor);
        } catch (final RuntimeException e) {
            final DoctorManagerResult result = new DoctorManagerResult();
            result.setSuccess(false);
            result.addError(DoctorManagerError.TCKN_HAS_EXIST);
            return result;
        }
    }

    /**
     * @param doctorManager
     *            the doctorManager to set
     */
    public void setDoctorManager(final DoctorManager doctorManager) {
        this.doctorManager = doctorManager;
    }

    public DoctorManagerResult search(final DoctorBrowseModel model) {
        return doctorManager.search(model);
    }

}
