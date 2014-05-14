package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.HealthData;

/**
 * Sağlık verileri üzerinde işlem yapan Facade arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface HealthDataFacade {
    /**
     * Sisteme yeni bir sağlık verisi ekler
     * 
     * @param healthData
     * @return
     */
    HealthDataResult add(HealthData healthData);

    /**
     * Sistem üzerinde sağlık alanı arar
     * 
     * @param filter
     * @return
     */
    HealthDataResult find(HealthDataBrowseFilter filter);
}
