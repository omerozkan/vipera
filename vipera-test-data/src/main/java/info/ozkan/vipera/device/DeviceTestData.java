package info.ozkan.vipera.device;

import info.ozkan.vipera.entities.Device;

/**
 * Cihaz test verileri
 * 
 * @author Ömer Özkan
 * 
 */
public class DeviceTestData {

    /**
     * private constructor
     */
    private DeviceTestData() {
    }

    /**
     * test cihazını dönderir
     * 
     * @return
     */
    public static Device get() {
        final Device device = new Device();
        device.setApiKey("u8UAvzOTL1zJp87o");
        device.setApiPassword("lFQ0BbsmGpfpAAI5");
        return device;
    }

}
