package info.ozkan.vipera.business.doctorpatient;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.doctorpatient.DoctorPatientDao;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

/**
 * Hekim hasta işlemlerini yapan manager sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorPatientManager")
public class DoctorPatientManagerImpl implements DoctorPatientManager {
    /**
     * Veri katmanı nesnesi
     */
    @Inject
    private DoctorPatientDao doctorPatientDao;

    @Transactional
    @RolesAllowed(Role.ROLE_ADMIN)
    public DoctorPatientManagerResult assign(final Doctor doctor,
            final Patient patient) {
        return doctorPatientDao.addPatientToDoctor(doctor, patient);
    }

    /**
     * @param doctorPatientDao
     *            the doctorPatientDao to set
     */
    public void setDoctorPatientDao(final DoctorPatientDao doctorPatientDao) {
        this.doctorPatientDao = doctorPatientDao;
    }

}
