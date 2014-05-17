package info.ozkan.vipera.device;

import info.ozkan.vipera.entities.Device;

import java.util.HashMap;
import java.util.Map;

/**
 * Cihaz test verileri
 * 
 * @author Ömer Özkan
 * 
 */
public final class DeviceTestData {
    /**
     * cihazlar
     */
    private static final Map<Integer, Device> DEVICES =
            new HashMap<Integer, Device>();

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
    public static Device get(final int key) {
        if (DEVICES.isEmpty()) {
            initializeDevices();
        }
        return DEVICES.get(key);
    }

    /**
     * cihazları ilklendirir
     */
    private static void initializeDevices() {
        Device device = new Device();
        device.setApiKey("u8UAvzOTL1zJp87o");
        device.setApiPassword("lFQ0BbsmGpfpAAI5");
        DEVICES.put(0, device);

        device = new Device();
        device.setApiKey("UuIpbdV3W9a1IZny");
        device.setApiPassword("34e3TnPyRC3WLhOt");
        DEVICES.put(1, device);

        device = new Device();
        device.setApiKey("A9tydtP08vMQEwpx");
        device.setApiPassword("vtnP22YGNci3m3po");
        DEVICES.put(2, device);

    }

}
