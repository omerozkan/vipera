package info.ozkan.vipera.business.device;

import info.ozkan.vipera.entities.Device;

/**
 * Cihazlar üzerinde CRUD işlemi gerçekleştiren işletme arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface DeviceManager {
    /**
     * Sisteme yeni bir cihaz ekler
     * 
     * @param device
     * @return
     */
    DeviceManagerResult add(Device device);

    /**
     * Cihazlar üzerinde arama yapar
     * 
     * @param filter
     * @return
     */
    DeviceManagerResult search(DeviceManagerSearchFilter filter);

    /**
     * Sistemde kayıtlı olan bir cihazı siler
     * 
     * @param deviceId
     * @return
     */
    DeviceManagerResult delete(Long deviceId);

    /**
     * Sistemde kayıtlı olan bir cihazı dönderir
     * 
     * @param id
     *            Cihaz ID
     * @return
     */
    DeviceManagerResult getById(Long id);

    /**
     * Sistemde kayıtlı olan bir cihazı günceller
     * 
     * @param device
     * @return
     */
    DeviceManagerResult update(Device device);

}
