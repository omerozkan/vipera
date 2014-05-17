/**
 * 
 */
package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;

/**
 * Doctor Facade sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorFacade {
    /**
     * Veritabanına yeni bir hekim ekler
     * 
     * @param doctor
     * @return
     */
    DoctorManagerResult add(Doctor doctor);

    /**
     * Veritabanı üzerinden hekim arar
     * 
     * @param model
     * @return
     */
    DoctorManagerResult search(DoctorBrowseModel model);

    /**
     * ID'ye göre hekim'i elde eder
     * 
     * @param id
     * @return
     */
    DoctorManagerResult getById(Long id);

    /**
     * Hekim güncelleme işlemi yapar
     * 
     * @param doctor
     * @return
     */
    DoctorManagerResult update(Doctor doctor);

    /**
     * Hekim silme işlemi yapar
     * 
     * @param doctor
     * @return
     */
    DoctorManagerResult delete(Doctor doctor);

}
