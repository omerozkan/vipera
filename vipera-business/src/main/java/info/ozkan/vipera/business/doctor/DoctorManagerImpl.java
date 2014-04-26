package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.business.login.AdministratorLoginManager;
import info.ozkan.vipera.dao.doctor.DoctorBrowseFilter;
import info.ozkan.vipera.dao.doctor.DoctorDao;
import info.ozkan.vipera.dao.doctor.DoctorDaoResult;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.models.DoctorBrowseModel;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hekimler üzerinde işlem yapan işletme katmanı sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorManager")
public class DoctorManagerImpl implements DoctorManager {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DoctorManagerImpl.class);
    /**
     * TCKN uzunluk
     */
    private static final int TCKN_LENGTH = 11;
    /**
     * Veritabanı işlemleri
     */
    @Inject
    private DoctorDao doctorDao;

    /*
     * (non-Javadoc)
     * 
     * @see
     * info.ozkan.vipera.business.doctor.DoctorManager#add(info.ozkan.vipera
     * .entities.Doctor)
     */
    @Transactional
    @RolesAllowed(AdministratorLoginManager.ROLE_ADMIN)
    public DoctorManagerResult add(final Doctor doctor) {
        final DoctorManagerResult result = new DoctorManagerResult();
        final DoctorDaoResult daoResult = doctorDao.add(doctor);
        if (!daoResult.isSuccess()) {
            LOGGER.info("The new doctor cannot be added");
            result.addError(daoResult.getError());
            result.setSuccess(false);
        } else {
            LOGGER.info("The new doctor {} is added", doctor.getFullname());
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * @param doctorDao
     *            the doctorDao to set
     */
    public void setDoctorDao(final DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see info.ozkan.vipera.business.doctor.DoctorManager#get(Long)
     */
    @Transactional
    public DoctorManagerResult getByTckn(final Long tckn) {
        final DoctorDaoResult daoResult = doctorDao.getByTckn(tckn);
        final DoctorManagerResult result = new DoctorManagerResult();
        result.addDoctor(daoResult.getDoctor());
        result.setSuccess(daoResult.isSuccess());
        result.addError(daoResult.getError());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * info.ozkan.vipera.business.doctor.DoctorManager#search(DoctorBrowseModel)
     */
    @Transactional
    @RolesAllowed(AdministratorLoginManager.ROLE_ADMIN)
    public DoctorManagerResult search(final DoctorBrowseModel model) {
        final DoctorBrowseFilter filter = new DoctorBrowseFilter();
        if (model.getTckn() != null
                && model.getTckn().toString().length() == TCKN_LENGTH) {
            filter.addFilter(Doctor.TCKN, model.getTckn());
        } else {
            filter.addFilter(Doctor.NAME, model.getName());
            filter.addFilter(Doctor.SURNAME, model.getSurname());
            filter.addFilter(Doctor.TITLE, model.getTitle());
            filter.addFilter(Doctor.ENABLED, model.getActive());
        }
        final List<Doctor> doctors = doctorDao.find(filter);
        final DoctorManagerResult result = new DoctorManagerResult();
        result.setDoctors(doctors);
        return result;
    }

    @Transactional
    public DoctorManagerResult getById(final Long id) {
        final DoctorDaoResult daoResult = doctorDao.getById(id);
        return daoResultToManagerResult(daoResult);
    }

    @Transactional
    @RolesAllowed(AdministratorLoginManager.ROLE_ADMIN)
    public DoctorManagerResult update(final Doctor doctor) {
        final DoctorDaoResult daoResult = doctorDao.update(doctor);
        return daoResultToManagerResult(daoResult);
    }

    public DoctorManagerResult delete(final Doctor doctor) {
        final DoctorDaoResult daoResult = doctorDao.delete(doctor);
        return daoResultToManagerResult(daoResult);
    }

    /**
     * Dao sonuç nesnesini Manager sonuç nesnesine çevirir
     * 
     * @param daoResult
     * @return
     */
    private DoctorManagerResult daoResultToManagerResult(
            final DoctorDaoResult daoResult) {
        final DoctorManagerResult result = new DoctorManagerResult();
        result.setSuccess(daoResult.isSuccess());
        result.addDoctor(daoResult.getDoctor());
        return result;
    }

}
