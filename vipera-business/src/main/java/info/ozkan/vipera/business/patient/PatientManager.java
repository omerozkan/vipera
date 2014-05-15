package info.ozkan.vipera.business.patient;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

/**
 * Hastalar üzerinde CRUD işlemi yapan business arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface PatientManager {
    /**
     * Sisteme hasta ekler
     * 
     * @param patient
     *            Yeni hasta
     * @return ekleme sonucu
     */
    PatientManagerResult add(Patient patient);

    /**
     * Filtreye göre bilgi bankasından hasta arama işlemi yapar
     * 
     * @param filter
     * @return
     */
    PatientManagerResult search(PatientSearchFilter filter);

    /**
     * Sistemde kayıtlı Id'e ait hastayı dönderir
     * 
     * @param id
     *            Hasta id
     * @return
     */
    PatientManagerResult getById(Long id);

    /**
     * Hasta ile ilgili değişiklikleri sisteme kaydeder
     * 
     * @param patient
     *            hasta
     * @return
     */
    PatientManagerResult update(Patient patient);

    /**
     * Hastayı sistemden siler
     * 
     * @param patient
     *            hasta
     * @return
     */
    PatientManagerResult delete(Patient patient);

    /**
     * Hekime ait hastaları arar
     * 
     * @param filter
     * @param doctor
     * @return
     */
    PatientManagerResult search(PatientSearchFilter filter, Doctor doctor);

    /**
     * hekim ve id ye göre hasta sorgular
     * 
     * @param id
     * @param doctor
     * @return
     */
    PatientManagerResult getById(Long id, Doctor doctor);
}
