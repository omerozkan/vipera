package info.ozkan.vipera.dao.patient;

import info.ozkan.vipera.business.patient.PatientManagerResult;
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

}
