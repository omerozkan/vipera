package info.ozkan.vipera.business.notification.android;

import info.ozkan.vipera.entities.DoctorAndroidDevice;

import java.util.List;

/**
 * Hekim android cihaz kaydı
 * 
 * @author Ömer Özkan
 * 
 */
public class AndroidRegistrationResult {
    /**
     * status
     */
    private AndroidRegistrationStatus status;
    private List<DoctorAndroidDevice> devices;

    /**
     * işlem başarılı ise true dönderir
     * 
     * @return
     */
    public boolean isSuccess() {
        return status.equals(AndroidRegistrationStatus.SUCCESS);
    }

    /**
     * set status
     * 
     * @param status
     */
    public void setStatus(final AndroidRegistrationStatus status) {
        this.status = status;
    }

    /**
     * @return the devices
     */
    public List<DoctorAndroidDevice> getDevices() {
        return devices;
    }

    /**
     * @param devices
     *            the devices to set
     */
    public void setDevices(final List<DoctorAndroidDevice> devices) {
        this.devices = devices;
    }

    /**
     * cihaz daha önce eklendi ise true dönderir
     * 
     * @return
     */
    public boolean hasExist() {
        return status.equals(AndroidRegistrationStatus.HAS_EXIST);
    }

}
