package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.HealthDataField;

/**
 * Sağlık alanları üzerinde işlem yapan işletme katmanı arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface HealthDataFieldFacade {
    /**
     * Yeni bir sağlık alanı ekler
     * 
     * @param field
     *            alan
     * @return
     */
    HealthDataFieldResult add(HealthDataField field);

    /**
     * Sistemde kayıtlı sağlık alanlarını alır
     * 
     * @return
     */
    HealthDataFieldResult getFields();

    /**
     * Sistemde kayıtlı sağlık alanını günceller
     * 
     * @param field
     * @return
     */
    HealthDataFieldResult update(HealthDataField field);

    /**
     * Sistemde kayıltı sağlık alanını sistemden kaldırır
     * 
     * @param field
     * @return
     */
    HealthDataFieldResult remove(HealthDataField field);
}
