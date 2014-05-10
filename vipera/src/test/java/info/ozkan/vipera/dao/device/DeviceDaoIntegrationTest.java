package info.ozkan.vipera.dao.device;

import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.doctor.PatientTestData;
import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.test.IntegrationTest;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

/**
 * DeviceDao entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class DeviceDaoIntegrationTest extends IntegrationTest {
    /**
     * Dao
     */
    @Inject
    private DeviceDao deviceDao;

    /**
     * Veritabanına yeni bir cihazın eklenebilirliğini test eder
     * 
     * @throws Exception
     */
    @Test
    public void canAddNewDevice() throws Exception {
        final Patient patient = PatientTestData
                .getTestData(PatientTestData.MARVIN);
        final Device device = createDevice(patient);

        final DeviceManagerResult result = deviceDao.add(device);
        Assert.assertTrue(result.isSuccess());
        Assert.assertNotNull(device.getId());
    }

    private Device createDevice(final Patient patient) {
        final Device device = new Device();
        device.setApiKey("testing");
        device.setApiPassword("password");
        device.setEnabled(Authorize.ENABLE);
        device.setPatient(patient);
        return device;
    }
}
