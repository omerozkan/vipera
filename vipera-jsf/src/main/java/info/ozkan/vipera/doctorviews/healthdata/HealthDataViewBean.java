package info.ozkan.vipera.doctorviews.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataFacade;
import info.ozkan.vipera.business.healthdata.HealthDataResult;
import info.ozkan.vipera.doctorviews.DoctorSessionBean;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.HealthData;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.sun.faces.context.FacesFileNotFoundException;

/**
 * Sağlık verisi görüntüleme ekranı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataView")
@Scope("request")
public class HealthDataViewBean implements Serializable {
    /**
     * serial
     */
    private static final long serialVersionUID = 6411468026745404034L;

    /**
     * Id
     */
    private Long id;

    /**
     * Data
     */
    private HealthData data;
    /**
     * Business
     */
    @Inject
    private HealthDataFacade healthDataFacade;

    /**
     * sağlık verisinin yüklenmesini sağlar
     * 
     * @throws FacesFileNotFoundException
     */
    public void loadData() throws FacesFileNotFoundException {
        if (id != null) {
            final Doctor doctor = DoctorSessionBean.getDoctor();
            final HealthDataResult result =
                    healthDataFacade.getById(id, doctor);
            if (result.isSuccess()) {
                data = result.getHealthData();
            } else {
                throw new FacesFileNotFoundException();
            }
        } else {
            throw new FacesFileNotFoundException();
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
     * @return the data
     */
    public HealthData getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final HealthData data) {
        this.data = data;
    }

}
