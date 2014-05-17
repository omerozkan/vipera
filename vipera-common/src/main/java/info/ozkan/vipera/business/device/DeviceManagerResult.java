package info.ozkan.vipera.business.device;

import info.ozkan.vipera.entities.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Cihazlar üzerinde yapılan CRUD işlemleri sonucu üretilen sonuç sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DeviceManagerResult {

    /**
     * Status
     */
    private DeviceManagerStatus status;
    /**
     * Cihazlar
     */
    private List<Device> devices = new ArrayList<Device>();

    /**
     * @return the status
     */
    public DeviceManagerStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final DeviceManagerStatus status) {
        this.status = status;
    }

    /**
     * @return the device
     */
    public Device getDevice() {
        if (devices.size() != 0) {
            return devices.get(0);
        }
        return null;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(final Device device) {
        devices.clear();
        devices.add(device);
    }

    public boolean isSuccess() {
        return status.equals(DeviceManagerStatus.SUCCESS);
    }

    /**
     * @return the devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * @param devices
     *            the devices to set
     */
    public void setDevices(final List<Device> devices) {
        this.devices = devices;
    }

}
