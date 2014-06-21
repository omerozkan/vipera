/**
 * 
 */
package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * DoctorFacade işletme sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorFacade")
public class DoctorFacadeImpl implements DoctorFacade, Serializable {
    /**
     * Serial
     */
    private static final long serialVersionUID = 8697253621847617425L;
    /**
     * DoctorManager
     */
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

    public DoctorManagerResult getById(final Long id) {
        return doctorManager.getById(id);
    }

    public DoctorManagerResult update(final Doctor doctor) {
        return doctorManager.update(doctor);
    }

    public DoctorManagerResult delete(final Doctor doctor) {
        return doctorManager.delete(doctor);
    }

    public DoctorManagerResult getByApi(final String apiKey) {
        return doctorManager.getByApi(apiKey);
    }

}
