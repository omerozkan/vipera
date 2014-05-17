package info.ozkan.vipera.patientviews.device;

import info.ozkan.vipera.business.device.DeviceFacade;
import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.business.device.DeviceManagerSearchFilter;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.patientviews.PatientSessionBean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Hastanın cihaz listesi
 * 
 * @author Ömer Özkan
 * 
 */
@Named("deviceList")
@Scope("session")
public class DeviceListBean {
    /**
     * Cihaz listesi
     */
    private List<Device> deviceList;
    /**
     * işletme
     */
    @Inject
    private DeviceFacade deviceFacade;

    /**
     * @return the deviceList
     */
    public List<Device> getDeviceList() {
        initializeDeviceList();
        return deviceList;
    }

    /**
     * cihaz listesini ilklendirir
     */
    private void initializeDeviceList() {
        final DeviceManagerSearchFilter filter =
                new DeviceManagerSearchFilter();
        final Patient patient = PatientSessionBean.getPatient();
        filter.setPatient(patient);
        final DeviceManagerResult result = deviceFacade.search(filter);
        deviceList = result.getDevices();
    }

}
