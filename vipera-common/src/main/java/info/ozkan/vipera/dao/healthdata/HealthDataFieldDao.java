package info.ozkan.vipera.dao.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataFieldResult;
import info.ozkan.vipera.entities.HealthDataField;

/**
 * Sağlıkl alanları üzeirnde CRUD işlemleri gerçekleştiren Dao arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface HealthDataFieldDao {
    /**
     * Veritabanına yeni bir alan ekler
     * 
     * @param field
     *            alan
     * @return
     */
    HealthDataFieldResult add(HealthDataField field);

    /**
     * Veritabanında kayıtlı alanları dönderir
     * 
     * @return
     */
    HealthDataFieldResult getAllFields();

    /**
     * Veritabanında kayıtlı olan bir alanı günceller
     * 
     * @param field
     * @return
     */
    HealthDataFieldResult update(HealthDataField field);

    /**
     * Veritabanında kayıtlı olan bir alanı kaldırır
     * 
     * @param field
     * @return
     */
    HealthDataFieldResult remove(HealthDataField field);

}
