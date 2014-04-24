package info.ozkan.vipera.dao.doctor;

import info.ozkan.vipera.entities.Doctor;

import java.util.List;

/**
 * Hekimler üzerinde işlem yapan veri katmanı sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorDao {

    /**
     * Veritabanına hekim ekler
     * 
     * @param doctor
     * @return
     */
    DoctorDaoResult add(Doctor doctor);

    /**
     * 
     * Veritabanından TCKN'ye ait hekimi sorgular
     * 
     * @param tckn
     *            TC Kimlik No
     * @return Doctor nesnesi
     * 
     * @param tckn
     * @return
     */
    DoctorDaoResult getByTckn(Long tckn);

    /**
     * Veritabanından girilen kriterlere göre hekim arama işlemi yapar
     * 
     * @param filter
     * @return
     */
    List<Doctor> find(DoctorBrowseFilter filter);
}
