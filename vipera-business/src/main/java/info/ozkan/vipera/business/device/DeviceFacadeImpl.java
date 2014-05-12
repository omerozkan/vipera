package info.ozkan.vipera.business.device;

import info.ozkan.vipera.entities.Device;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link DeviceFacade} arayüzü implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("deviceFacade")
public class DeviceFacadeImpl implements DeviceFacade {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DeviceManagerImpl.class);
    /**
     * Manager
     */
    @Inject
    private DeviceManager deviceManager;

    public DeviceManagerResult add(final Device device) {
        DeviceManagerResult result;
        try {
            result = deviceManager.add(device);
        } catch (final Exception e) {
            LOGGER.error("exception: ", e);
            result = new DeviceManagerResult();
            result.setStatus(DeviceManagerStatus.NON_UNIQUE_API_KEY);
        }
        return result;
    }

    public DeviceManagerResult search(final DeviceManagerSearchFilter filter) {
        return deviceManager.search(filter);
    }

    public DeviceManagerResult delete(final Long deviceId) {
        return deviceManager.delete(deviceId);
    }
}
