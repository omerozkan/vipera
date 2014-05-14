package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.HealthData;

import java.util.List;

/**
 * Sağlık alanları üzerinde yapılan işlemler sonucu üretilen sonuç sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataResult {
    /**
     * Statu
     */
    private HealthDataManagerStatus status;
    /**
     * Sağlık verileri
     */
    private List<HealthData> healthDatas;

    /**
     * @return the status
     */
    public HealthDataManagerStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final HealthDataManagerStatus status) {
        this.status = status;
    }

    /**
     * işlem başarılı mı
     * 
     * @return
     */
    public boolean isSuccess() {
        return status.equals(HealthDataManagerStatus.SUCCESS);
    }

    /**
     * @return the healthDatas
     */
    public List<HealthData> getHealthDataList() {
        return healthDatas;
    }

    /**
     * @param healthDatas
     *            the healthDatas to set
     */
    public void setHealthDatas(final List<HealthData> healthDatas) {
        this.healthDatas = healthDatas;
    }

}
