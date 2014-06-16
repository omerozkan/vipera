package info.ozkan.vipera.business.notification.android;

/**
 * Hekim android cihaz kaydı
 * 
 * @author Ömer Özkan
 * 
 */
public class AndroidRegistrationResult {
    /**
     * status
     */
    private AndroidRegistrationStatus status;

    /**
     * işlem başarılı ise true dönderir
     * 
     * @return
     */
    public boolean isSuccess() {
        return status.equals(AndroidRegistrationStatus.SUCCESS);
    }

    /**
     * set status
     * 
     * @param status
     */
    public void setStatus(final AndroidRegistrationStatus status) {
        this.status = status;
    }

}
