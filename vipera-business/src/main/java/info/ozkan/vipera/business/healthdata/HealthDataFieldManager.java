package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.HealthDataField;

/**
 * Sağlık veri alanları üzerinde CRUD işlemi yapan Manager sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface HealthDataFieldManager {
    /**
     * Sisteme yeni bir alan ekler
     * 
     * @param field
     * @return
     */
    HealthDataFieldResult add(HealthDataField field);

    /**
     * Sistemde kayıtlı olan alanları dönderir
     * 
     * @return
     */
    HealthDataFieldResult getFields();

    /**
     * Sistemde kayıtlı olan bir alanı günceller
     * 
     * @param field
     * @return
     */
    HealthDataFieldResult update(HealthDataField field);

    /**
     * Sistemde kayıtlı olan hekimi kaldırır
     * 
     * @param field
     * @return
     */
    HealthDataFieldResult remove(HealthDataField field);

    /**
     * Sistemde kayıtlı olan bir alanı dönderir
     */
    HealthDataField getField(String key);

}
