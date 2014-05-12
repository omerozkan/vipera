package info.ozkan.vipera.business.device;

import info.ozkan.vipera.entities.Device;

/**
 * Cihaz işlemleri için kullanılan işletme katmanı arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface DeviceFacade {
    /**
     * Sisteme yeni bir cihaz ekler
     * 
     * @param device
     * @return
     */
    DeviceManagerResult add(Device device);

    /**
     * Sistemde cihazlar üzerinde arama yapar
     * 
     * @param model
     *            filtre
     * @return
     */
    DeviceManagerResult search(DeviceManagerSearchFilter filter);

    /**
     * Sistemden cihaz siler
     * 
     * @param deviceId
     * @return
     */
    DeviceManagerResult delete(Long deviceId);

    /**
     * Sistemde kayıtlı olan bir cihazı ID'sine görer arar
     * 
     * @param id
     * @return
     */
    DeviceManagerResult getById(Long id);

    /**
     * Cihazı günceller
     * 
     * @param device
     * @return
     */
    DeviceManagerResult update(Device device);
}
