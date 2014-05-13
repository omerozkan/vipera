package info.ozkan.vipera.dao.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataResult;
import info.ozkan.vipera.entities.HealthData;

/**
 * Sağlık verileri ile ilgili CRUD işlemleri yapan DAO sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface HealthDataDao {
    /**
     * Veritabanına yeni bir sağlık verisi ekler
     * 
     * @param healthData
     * @return
     */
    HealthDataResult add(HealthData healthData);
}
