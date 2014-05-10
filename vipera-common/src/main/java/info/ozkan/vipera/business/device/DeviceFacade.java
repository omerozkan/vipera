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
}
