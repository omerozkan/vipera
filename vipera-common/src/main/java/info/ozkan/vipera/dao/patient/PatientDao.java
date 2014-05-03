package info.ozkan.vipera.dao.patient;

import info.ozkan.vipera.business.patient.PatientManagerResult;
import info.ozkan.vipera.business.patient.PatientSearchFilter;
import info.ozkan.vipera.entities.Patient;

/**
 * Patient Veritabanı işlemleri
 * 
 * @author Ömer Özkan
 * 
 */
public interface PatientDao {
    /**
     * Veritabanına yeni bir hasta ekler
     * 
     * @param patient
     *            Yeni hasta
     * @return
     */
    PatientManagerResult add(Patient patient);

    /**
     * Veritabanından hasta arama işlemi yapar
     * 
     * @param filter
     * @return
     */
    PatientManagerResult find(PatientSearchFilter filter);

}
