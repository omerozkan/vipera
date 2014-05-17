package info.ozkan.vipera.views.device;

import info.ozkan.vipera.business.device.DeviceFacade;
import info.ozkan.vipera.business.device.DeviceManagerResult;
import info.ozkan.vipera.entities.Device;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.jsf.FacesMessage2;
import info.ozkan.vipera.jsf.NotFoundException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Cihaz güncelleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("deviceUpdate")
@Scope("session")
public class DeviceUpdateBean {
    /**
     * Cihaz güncellenemedi mesajı
     */
    private static final String DEVICE_CANNOT_BE_UPDATED =
            "Cihaz güncellenemedi";
    /**
     * cihaz güncellenemedi mesaj detay deseni
     */
    private static final String DEVICE_CANNOT_BE_UPDATED_MSG_PATTERN =
            "Cihaz %s güncellenemedi!";
    /**
     * cihaz güncellendi mesjaı
     */
    private static final String DEVICE_UPDATED = "Cihaz güncellendi!";
    /**
     * cihaz güncellendi mesaj detayı deseni
     */
    private static final String DEVICE_UPDATED_MSG_PATTERN =
            "Cihaz %s güncellendi!";
    /**
     * Id
     */
    private Long id;
    /**
     * Cihaz
     */
    private Device device;
    /**
     * Business
     */
    @Inject
    private DeviceFacade deviceFacade;
    /**
     * seçilen hasta
     */
    private Patient selectedPatient;
    /**
     * Cihaz tekrar yüklensin mi
     */
    private boolean loadDevice = true;

    /**
     * Cihazın yüklenmesini sağlar
     * 
     */
    public void loadDevice() {
        if (loadDevice) {
            getDeviceFromSystem();
        } else {
            loadDevice = true;
        }
    }

    /**
     * Sistemden ilgili cihazı getirir
     * 
     */
    private void getDeviceFromSystem() {
        if (id != null) {
            final DeviceManagerResult result = deviceFacade.getById(id);
            if (result.isSuccess()) {
                device = result.getDevice();
                setSelectedPatient(device.getPatient());
            } else {
                throw new NotFoundException();
            }
        } else {
            throw new NotFoundException();
        }
    }

    /**
     * Hasta seç
     * 
     * @param patient
     */
    public void selectPatient(final Patient patient) {
        selectedPatient = patient;
        loadDevice = false;
    }

    /**
     * parola üret
     */
    public void generate() {
        final String password = PasswordGenerator.generate();
        device.setApiPassword(password);
        loadDevice = false;
    }

    /**
     * güncelleme işlemini gerçekleştirir
     */
    public void update() {
        final FacesContext context = FacesContext.getCurrentInstance();
        device.setPatient(getSelectedPatient());
        final DeviceManagerResult result = deviceFacade.update(device);

        if (result.isSuccess()) {
            final String detail =
                    String.format(DEVICE_UPDATED_MSG_PATTERN,
                            device.getApiKey());
            context.addMessage(null, new FacesMessage2(
                    FacesMessage.SEVERITY_INFO, DEVICE_UPDATED, detail));
        } else {
            final String detail =
                    String.format(DEVICE_CANNOT_BE_UPDATED_MSG_PATTERN);
            context.addMessage(null, new FacesMessage2(null,
                    DEVICE_CANNOT_BE_UPDATED, detail));
        }
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(final Device device) {
        this.device = device;
    }

    /**
     * @return the selectedPatient
     */
    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    /**
     * @param selectedPatient
     *            the selectedPatient to set
     */
    public void setSelectedPatient(final Patient selectedPatient) {
        this.selectedPatient = selectedPatient;
    }

}
