package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.models.DoctorBrowseModel;

/**
 * Hekimler üzerinde çeşitli işlemler yapar
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorManager {
    /**
     * Doctor nesnesini veritabanına kaydeder
     * 
     * @param doctor
     *            Doctor nesnesi
     * @return işlem sonucu
     */
    DoctorManagerResult add(Doctor doctor);

    /**
     * Veritabanından TCKN'na ait hekim nesnesini dönderir
     * 
     * @param tckn
     * @return
     */
    DoctorManagerResult getByTckn(Long tckn);

    /**
     * Veritabanı üzerinde hekim arama işlemi yapar
     * 
     * @param model
     * @return
     */
    DoctorManagerResult search(DoctorBrowseModel model);
}
