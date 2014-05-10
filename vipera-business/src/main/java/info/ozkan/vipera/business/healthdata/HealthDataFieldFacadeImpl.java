package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.HealthDataField;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Sağlık alanı işlemleri yapan işletme sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataFieldFacade")
public class HealthDataFieldFacadeImpl implements HealthDataFieldFacade {
    /**
     * Manager nesnesi
     */
    @Inject
    private HealthDataFieldManager healthDataFieldManager;

    public HealthDataFieldResult add(final HealthDataField field) {
        HealthDataFieldResult result;
        try {
            result = healthDataFieldManager.add(field);
        } catch (final Exception e) {
            result = createNonUniqueNameResult();
        }
        return result;
    }

    public HealthDataFieldResult getFields() {
        return healthDataFieldManager.getFields();
    }

    public HealthDataFieldResult update(final HealthDataField field) {
        HealthDataFieldResult result;
        try {
            result = healthDataFieldManager.update(field);
        } catch (final Exception e) {
            result = createNonUniqueNameResult();
        }
        return result;
    }

    public HealthDataFieldResult remove(final HealthDataField field) {
        return healthDataFieldManager.remove(field);
    }

    /**
     * {@link HealthDataFieldStatus#NON_UNIQUE_NAME} hatası içeren bir sonuç
     * üretir
     * 
     * @return
     */
    private HealthDataFieldResult createNonUniqueNameResult() {
        HealthDataFieldResult result;
        result = new HealthDataFieldResult();
        result.setStatus(HealthDataFieldStatus.NON_UNIQUE_NAME);
        return result;
    }

}
