package info.ozkan.vipera.views.device;

import info.ozkan.vipera.business.device.DeviceFacade;
import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.business.device.DeviceManagerSearchFilter;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

/**
 * Cihaz arama listeleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("deviceBrowse")
@Scope("session")
public class DeviceBrowseBean {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DeviceBrowseBean.class);
    /**
     * Cihaz listesi
     */
    private List<Device> devices;
    /**
     * Model
     */
    private DeviceBrowseModel model = new DeviceBrowseModel();
    /**
     * business object
     */
    @Inject
    private DeviceFacade deviceFacade;

    /**
     * hasta seçer
     * 
     * @param patient
     */
    public void selectPatient(final Patient patient) {
        getModel().setPatient(patient);
    }

    /**
     * @return the model
     */
    public DeviceBrowseModel getModel() {
        return model;
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

    /**
     * @param model
     *            the model to set
     */
    public void setModel(final DeviceBrowseModel model) {
        this.model = model;
    }

    /**
     * Arama işlemi yapar
     */
    public void search() {
        final DeviceManagerSearchFilter filter = createFilter();
        final DeviceManagerResult result = deviceFacade.search(filter);
        devices = result.getDevices();
        LOGGER.info("Found {} devices.", devices.size());
    }

    /**
     * Hasta seçimini sıfırlar
     */
    public void resetPatient() {
        model.setPatient(null);
    }

    /**
     * Model nesnesinden filtre üretir
     * 
     * @return
     */
    private DeviceManagerSearchFilter createFilter() {
        final DeviceManagerSearchFilter filter = new DeviceManagerSearchFilter();
        if (model.getPatient() != null) {
            filter.setPatient(model.getPatient());
        }
        if (model.getApiKey() != null && model.getApiKey().isEmpty()) {
            filter.setApiKey(model.getApiKey());
        }
        return filter;
    }
}
