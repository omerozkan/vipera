package info.ozkan.vipera.business.patient;

import info.ozkan.vipera.entities.Patient;

/**
 * Hasta işlemlerini yapan Facade arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface PatientFacade {
    /**
     * Sisteme yeni bir hasta ekler
     * 
     * @param patient
     * @return
     */
    PatientManagerResult add(Patient patient);

    /**
     * Sistemde hasta arama işlemi yapar
     * 
     * @param filter
     * @return
     */
    PatientManagerResult search(PatientSearchFilter filter);

    /**
     * Sistemde kayıtlı bir hastayı dönderir
     * 
     * @param id
     *            Hasta id
     * @return
     */
    PatientManagerResult getById(Long id);

    /**
     * Sistemde kayıtlı olan bir hastayı günceller
     * 
     * @param patient
     * @return
     */
    PatientManagerResult update(Patient patient);

    /**
     * Sistemden hasta siler
     * 
     * @param patient
     *            Hasta
     * @return
     */
    PatientManagerResult delete(Patient patient);

}
