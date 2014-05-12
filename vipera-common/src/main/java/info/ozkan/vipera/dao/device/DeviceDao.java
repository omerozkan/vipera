package info.ozkan.vipera.dao.device;

import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.business.device.DeviceManagerSearchFilter;
import info.ozkan.vipera.entities.Device;

/**
 * Cihazlar üzerinde veritabanında CRUD işlemler yapar
 * 
 * @author Ömer Özkan
 * 
 */
public interface DeviceDao {
    /**
     * Veritabanına yeni bir cihaz ekler
     * 
     * @param device
     *            cihaz
     * @return
     */
    DeviceManagerResult add(Device device);

    /**
     * Veritabanında cihaz arar
     * 
     * @param filter
     * @return
     */
    DeviceManagerResult find(DeviceManagerSearchFilter filter);

    /**
     * Veritabanından cihaz siler
     * 
     * @param deviceId
     * @return
     */
    DeviceManagerResult delete(Long deviceId);

    /**
     * Veritabanında kayıtlı bir cihazı dönderir
     * 
     * @param id
     * @return
     */
    Device getById(Long id);

    /**
     * Veritabanında kayıtlı olan bir cihazı günceller
     * 
     * @param device
     * @return
     */
    DeviceManagerResult update(Device device);
}
