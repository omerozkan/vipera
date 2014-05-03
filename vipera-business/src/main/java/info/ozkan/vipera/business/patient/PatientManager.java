package info.ozkan.vipera.business.patient;

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
}
