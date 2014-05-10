package info.ozkan.vipera.dao.device;

import info.ozkan.vipera.business.device.DeviceManagerResult;
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
}
