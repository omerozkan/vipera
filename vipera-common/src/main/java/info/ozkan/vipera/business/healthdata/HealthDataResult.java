package info.ozkan.vipera.business.healthdata;

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

}
