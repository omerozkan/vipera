package info.ozkan.vipera.business.device;

import info.ozkan.vipera.entities.Device;

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
     * Cihaz
     */
    private Device device;

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
        return device;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(final Device device) {
        this.device = device;
    }

    public boolean isSuccess() {
        return status.equals(DeviceManagerStatus.SUCCESS);
    }

}
