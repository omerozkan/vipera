package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.doctorpatient.DoctorPatientDao;
import info.ozkan.vipera.dao.healthdata.HealthDataDao;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.Patient;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link HealthDataService} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataService")
public class HealthDataServiceImpl implements HealthDataService {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(HealthDataServiceImpl.class);

    /**
     * Dao nesnesi
     */
    @Inject
    private HealthDataDao healthDataDao;

    /**
     * DoctorPatientDAo
     */
    @Inject
    private DoctorPatientDao doctorPatientDao;

    @Transactional
    public HealthDataResult add(final HealthData healthData) {
        return healthDataDao.add(healthData);
    }

    @Transactional
    @RolesAllowed(Role.ROLE_DOCTOR)
    public HealthDataResult find(final HealthDataBrowseFilter filter) {
        return healthDataDao.find(filter);
    }

    @Transactional
    @RolesAllowed(Role.ROLE_DOCTOR)
    public HealthDataResult getById(final Long id, final Doctor doctor) {
        HealthDataResult result = healthDataDao.getById(id);
        final boolean check = checkDoctor(doctor, result);
        if (!check) {
            result = new HealthDataResult();
            result.setStatus(HealthDataManagerStatus.NOT_FOUND);
        }
        return result;
    }

    /**
     * Sağlık verisinin hekime atanan bir hastaya ait olup olmadığını kontrol
     * eder
     * 
     * @param doctor
     * @param result
     * @return
     */
    private boolean checkDoctor(final Doctor doctor,
            final HealthDataResult result) {
        boolean check = false;
        if (result.isSuccess()) {
            final HealthData data = result.getHealthData();
            final Patient patient = data.getPatient();
            doctorPatientDao.loadPatientsByDoctor(doctor);
            final List<Patient> doctorPatients = doctor.getPatients();
            if (doctorPatients.contains(patient)) {
                check = true;
            }
        }
        return check;
    }

}
