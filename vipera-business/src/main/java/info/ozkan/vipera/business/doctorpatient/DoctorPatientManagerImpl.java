package info.ozkan.vipera.business.doctorpatient;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.doctor.DoctorDao;
import info.ozkan.vipera.dao.doctor.DoctorDaoResult;
import info.ozkan.vipera.dao.doctorpatient.DoctorPatientDao;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

import java.util.List;

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
    /**
     * Hekim üzerinde veritabanı işlemleri yapan veri katmanı nesnesi
     */
    @Inject
    private DoctorDao doctorDao;

    @Transactional
    @RolesAllowed(Role.ROLE_ADMIN)
    public DoctorPatientManagerResult assign(final Doctor doctor,
            final Patient patient) {
        return doctorPatientDao.addPatientToDoctor(doctor, patient);
    }

    @Transactional
    @RolesAllowed(Role.ROLE_ADMIN)
    public void loadPatients(final Doctor doctor) {
        doctorPatientDao.loadPatientsByDoctor(doctor);
    }

    /**
     * @param doctorPatientDao
     *            the doctorPatientDao to set
     */
    public void setDoctorPatientDao(final DoctorPatientDao doctorPatientDao) {
        this.doctorPatientDao = doctorPatientDao;
    }

    @Transactional
    @RolesAllowed(Role.ROLE_ADMIN)
    public DoctorPatientManagerResult removeAssignment(final Doctor doctor,
            final Patient patient) {
        final List<Patient> patientList = doctor.getPatients();
        DoctorPatientManagerResult result;
        if (patientList.remove(patient)) {
            result = updateDoctor(doctor);
        } else {
            result = assigmentNotExistResult();
        }
        return result;
    }

    /**
     * Hekim güncellenir
     * 
     * @param doctor
     *            Hekim
     * @return
     */
    private DoctorPatientManagerResult updateDoctor(final Doctor doctor) {
        DoctorPatientManagerResult result;
        final DoctorDaoResult daoResult = doctorDao.update(doctor);
        if (daoResult.isSuccess()) {
            result = successResult();
        } else {
            result = doctorNotExistResult();
        }
        return result;
    }

    /**
     * Atama olmadığında gösterilecek olan hata
     * 
     * @return
     */
    private DoctorPatientManagerResult assigmentNotExistResult() {
        final DoctorPatientManagerResult result =
                new DoctorPatientManagerResult();
        result.setStatus(DoctorPatientManagerStatus.ASSIGNMENT_NOT_EXIST);
        return null;
    }

    /**
     * Hekim kayıtlı olmadığında gösterilecek olan hata
     * 
     * @return
     */
    private DoctorPatientManagerResult doctorNotExistResult() {
        final DoctorPatientManagerResult result =
                new DoctorPatientManagerResult();
        result.setStatus(DoctorPatientManagerStatus.DOCTOR_NOT_EXIST);
        return result;
    }

    /**
     * Başarılı sonuç oluşturur
     * 
     * @return
     */
    private DoctorPatientManagerResult successResult() {
        final DoctorPatientManagerResult result =
                new DoctorPatientManagerResult();
        result.setStatus(DoctorPatientManagerStatus.SUCCESS);
        return result;
    }

    /**
     * @return the doctorDao
     */
    public DoctorDao getDoctorDao() {
        return doctorDao;
    }

    /**
     * @param doctorDao
     *            the doctorDao to set
     */
    public void setDoctorDao(final DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Transactional
    @RolesAllowed(Role.ROLE_PATIENT)
    public void loadDoctors(final Patient patient) {
        doctorPatientDao.loadDoctorsByPatient(patient);

    }

}
