package info.ozkan.vipera.business.doctorpatient;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hekim-hasta işlemlerini delege eden business sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorPatientFacade")
public class DoctorPatientFacadeImpl implements DoctorPatientFacade {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorPatientFacadeImpl.class);
    /**
     * Manager
     */
    @Inject
    private DoctorPatientManager doctorPatientManager;

    public DoctorPatientManagerResult assign(final Doctor doctor,
            final Patient patient) {
        DoctorPatientManagerResult result;
        try {
            result = doctorPatientManager.assign(doctor, patient);
        } catch (final Exception e) {
            result = new DoctorPatientManagerResult();
            result.setStatus(DoctorPatientManagerStatus.EXIST);
            LOGGER.info("Patient already assigned to doctor!", e);
        }
        return result;
    }

    /**
     * @param doctorPatientManager
     *            the doctorPatientManager to set
     */
    public void setDoctorPatientManager(
            final DoctorPatientManager doctorPatientManager) {
        this.doctorPatientManager = doctorPatientManager;
    }

    public void loadPatients(final Doctor doctor) {
        doctorPatientManager.loadPatients(doctor);
    }

    public DoctorPatientManagerResult removeAssignment(final Doctor doctor,
            final Patient patient) {
        DoctorPatientManagerResult result;
        try {
            result = doctorPatientManager.removeAssignment(doctor, patient);
        } catch (final Exception e) {
            result = new DoctorPatientManagerResult();
            result.setStatus(DoctorPatientManagerStatus.ASSIGNMENT_NOT_EXIST);
        }
        return result;
    }

    public void loadDoctors(final Patient patient) {
        doctorPatientManager.loadDoctors(patient);

    }

}
