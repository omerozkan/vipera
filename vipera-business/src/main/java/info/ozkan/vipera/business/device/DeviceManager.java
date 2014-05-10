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

}
