package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.HealthDataField;

import java.util.ArrayList;
import java.util.List;

/**
 * Sağlık alanları üzerinde yapılan CRUD işlemleri için sonuç sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataFieldResult {
    /**
     * statü
     */
    private HealthDataFieldStatus status;
    /**
     * Sağlık alanları listesi
     */
    private List<HealthDataField> healthDataFields = new ArrayList<HealthDataField>();

    /**
     * @return the status
     */
    public HealthDataFieldStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final HealthDataFieldStatus status) {
        this.status = status;
    }

    /**
     * İşlem sonucu başarılı ise true döner
     * 
     * @return
     */
    public boolean isSuccess() {
        return status.equals(HealthDataFieldStatus.SUCCESS);
    }

    /**
     * @return the healthDataField
     */
    public HealthDataField getHealthDataField() {
        if (getHealthDataFields().size() != 0) {
            return getHealthDataFields().get(0);
        }
        return null;
    }

    /**
     * @return the healthDataFields
     */
    public List<HealthDataField> getHealthDataFields() {
        return healthDataFields;
    }

    /**
     * @param healthDataField
     *            the healthDataField to set
     */
    public void setHealthDataField(final HealthDataField healthDataField) {
        getHealthDataFields().clear();
        getHealthDataFields().add(healthDataField);
    }

    /**
     * @param healthDataFields
     *            the healthDataFields to set
     */
    public void setHealthDataFields(final List<HealthDataField> healthDataFields) {
        this.healthDataFields = healthDataFields;
    }

}
