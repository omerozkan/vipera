package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.entities.Doctor;

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

    /**
     * Veritabanından ID'e ait hekim nesnesini dönderir
     * 
     * @param id
     * @return
     */
    DoctorManagerResult getById(Long id);

    /**
     * Hekimi günceller
     * 
     * @param doctor
     * @return
     */
    DoctorManagerResult update(Doctor doctor);

    /**
     * Hekim i veritabanından siler
     * 
     * @param doctor
     * @return
     */
    DoctorManagerResult delete(Doctor doctor);

    /**
     * Sistemde kayıtlı hekimi api anahtarına göre sorgular
     * 
     * @param apiKey
     * @return
     */
    DoctorManagerResult getByApi(String apiKey);
}
