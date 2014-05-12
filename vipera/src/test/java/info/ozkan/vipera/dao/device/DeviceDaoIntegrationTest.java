package info.ozkan.vipera.dao.device;

import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.business.device.DeviceManagerSearchFilter;
import info.ozkan.vipera.business.device.DeviceManagerStatus;
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
    private final Patient patient = PatientTestData
            .getTestData(PatientTestData.MARVIN);

    /**
     * Veritabanına yeni bir cihazın eklenebilirliğini test eder
     * 
     * @throws Exception
     */
    @Test
    public void canAddNewDevice() throws Exception {
        final Device device = createDevice(patient);
        final DeviceManagerResult result = deviceDao.add(device);
        Assert.assertTrue(result.isSuccess());
        Assert.assertNotNull(device.getId());
    }

    /**
     * Hasta ya göre arama yapar
     * 
     * @throws Exception
     */
    @Test
    public void canFindByPatient() throws Exception {
        final DeviceManagerSearchFilter filter = new DeviceManagerSearchFilter();
        filter.setPatient(patient);
        final DeviceManagerResult result = deviceDao.find(filter);
        Assert.assertEquals(DeviceManagerStatus.SUCCESS, result.getStatus());
        Assert.assertEquals(1, result.getDevices().size());
    }

    /**
     * Api anahtarına göre arama yapar
     * 
     * @throws Exception
     */
    @Test
    public void canFindByAPIKey() throws Exception {
        final DeviceManagerSearchFilter filter = new DeviceManagerSearchFilter();
        filter.setApiKey("AvzOTL");
        final DeviceManagerResult result = deviceDao.find(filter);
        Assert.assertEquals(1, result.getDevices().size());
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
