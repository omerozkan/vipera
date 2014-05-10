package info.ozkan.vipera.business.device;

import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientManagerStatus;
import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.device.DeviceDao;
import info.ozkan.vipera.dao.patient.PatientDao;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link DeviceManager} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("deviceManager")
public class DeviceManagerImpl implements DeviceManager {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DeviceManagerImpl.class);
    /**
     * Dao
     */
    @Inject
    private DeviceDao deviceDao;
    /**
     * PatientDao
     */
    @Inject
    private PatientDao patientDao;

    @Transactional
    @RolesAllowed(Role.ROLE_ADMIN)
    public DeviceManagerResult add(final Device device) {
        final DeviceManagerResult result;
        final Patient patient = device.getPatient();
        final PatientManagerResult patientResult = patientDao.getById(patient
                .getId());
        final PatientManagerStatus patientStatus = patientResult.getStatus();
        if (patientStatus.equals(PatientManagerStatus.NOT_FOUND)) {
            result = createPatientNonExistResult();
            LOGGER.info("The patient not found! Saving new device has failed.");
        } else {
            result = deviceDao.add(device);
        }
        return result;
    }

    /**
     * Hasta bulunamadı hata mesajı sonucu oluşturur
     * 
     * @return
     */
    private DeviceManagerResult createPatientNonExistResult() {
        final DeviceManagerResult result;
        result = new DeviceManagerResult();
        result.setStatus(DeviceManagerStatus.PATIENT_NOT_EXIST);
        return result;
    }

}
