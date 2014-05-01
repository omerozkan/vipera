package info.ozkan.vipera.business.patient;

/**
 * Hastalar üzerinde yapılan CRUD işlemleri sonucunda istemci için üretilen
 * sonuç sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class PatientManagerResult {
    /**
     * Status
     */
    private PatientManagerStatus status;

    /**
     * @return the status
     */
    public PatientManagerStatus getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final PatientManagerStatus status) {
        this.status = status;
    }

}
