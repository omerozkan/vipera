package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.HealthData;

/**
 * Sağlık verisi servisi
 * 
 * @author Ömer Özkan
 * 
 */
public interface HealthDataService {
    /**
     * Sisteme yeni bir sağlık verisi ekler
     * 
     * @param healthData
     * @return
     */
    HealthDataResult add(HealthData healthData);

    /**
     * Sisteme kayıtlı veriler üzerinde arama yapar
     * 
     * @param filter
     * @return
     */
    HealthDataResult find(HealthDataBrowseFilter filter);
}
