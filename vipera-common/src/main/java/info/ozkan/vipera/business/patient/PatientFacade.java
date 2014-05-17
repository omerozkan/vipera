package info.ozkan.vipera.business.patient;

import info.ozkan.vipera.entities.Doctor;
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

    /**
     * Hekim'e ait hastalar üzerinde arama işlemi yapar
     * 
     * @param filter
     * @param doctor
     * @return
     */
    PatientManagerResult search(PatientSearchFilter filter, Doctor doctor);

    /**
     * Hekim ve id ye göre hasta sorgular
     * 
     * @param id
     * @param doctor
     * @return
     */
    PatientManagerResult getById(Long id, Doctor doctor);

}
