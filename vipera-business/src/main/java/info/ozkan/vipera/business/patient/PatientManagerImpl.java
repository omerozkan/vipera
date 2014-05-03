package info.ozkan.vipera.business.patient;

import info.ozkan.vipera.business.login.AdministratorLoginManager;
import info.ozkan.vipera.dao.patient.PatientDao;
import info.ozkan.vipera.entities.Patient;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

/**
 * Hasta CRUD işlemleri işletme katmanı sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientManager")
public class PatientManagerImpl implements PatientManager {
    /**
     * Persistence katmanı nesnesi
     */
    @Inject
    private PatientDao patientDao;

    /**
     * Sisteme yeni bir hasta ekler
     */
    @Transactional
    @RolesAllowed(AdministratorLoginManager.ROLE_ADMIN)
    public PatientManagerResult add(final Patient patient) {
        return patientDao.add(patient);
    }

    @Transactional
    @RolesAllowed(AdministratorLoginManager.ROLE_ADMIN)
    public PatientManagerResult search(final PatientSearchFilter filter) {
        return patientDao.find(filter);
    }

    @Transactional
    @RolesAllowed(AdministratorLoginManager.ROLE_ADMIN)
    public PatientManagerResult getById(final Long id) {
        return patientDao.getById(id);
    }

    @Transactional
    @RolesAllowed(AdministratorLoginManager.ROLE_ADMIN)
    public PatientManagerResult update(final Patient patient) {
        return patientDao.update(patient);
    }

    /**
     * @return the patientDao
     */
    public PatientDao getPatientDao() {
        return patientDao;
    }

    /**
     * @param patientDao
     *            the patientDao to set
     */
    public void setPatientDao(final PatientDao patientDao) {
        this.patientDao = patientDao;
    }

}
