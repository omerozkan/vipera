package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.dao.healthdata.HealthDataDao;
import info.ozkan.vipera.entities.HealthData;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

/**
 * {@link HealthDataService} arayüzünün implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataService")
public class HealthDataServiceImpl implements HealthDataService {
    /**
     * Dao nesnesi
     */
    @Inject
    private HealthDataDao healthDataDao;

    @Transactional
    public HealthDataResult add(final HealthData healthData) {
        return healthDataDao.add(healthData);
    }

}
