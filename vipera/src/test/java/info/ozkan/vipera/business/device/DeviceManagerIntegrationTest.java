package info.ozkan.vipera.business.device;

import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.test.IntegrationTest;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

/**
 * DeviceManager entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class DeviceManagerIntegrationTest extends IntegrationTest {
    /**
     * Test edilen sınıf nesnesi
     */
    @Inject
    private DeviceManager deviceManager;

    /**
     * Sistemde kayıtlı olmayan bir hastaya cihaz eklenmek ister hasta sistemde
     * kayıtlı değil hata mesajı gönderilir
     * 
     * @throws Exception
     */
    @Test
    public void tryAddNonExistPatient() throws Exception {
        final Patient patient = new Patient();
        patient.setId(123123123l);
        final Device device = createDevice(patient);
        final DeviceManagerResult result = deviceManager.add(device);
        Assert.assertEquals(DeviceManagerStatus.PATIENT_NOT_EXIST,
                result.getStatus());
    }

    /**
     * Cihaz nesnesi oluşturur
     * 
     * @param patient
     * @return
     */
    private Device createDevice(final Patient patient) {
        final Device device = new Device();
        device.setApiKey("blabla");
        device.setApiPassword("password");
        device.setEnabled(Authorize.ENABLE);
        device.setPatient(patient);
        return device;
    }
}
