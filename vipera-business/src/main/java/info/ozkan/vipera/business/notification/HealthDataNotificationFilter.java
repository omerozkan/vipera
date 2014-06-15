package info.ozkan.vipera.business.notification;

import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.HealthDataValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Sağlık verisi alanları üzerinde filtreleme işlemini gerçekleştirir
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataNotificationFilter {
    /**
     * Filtreleme işlemini gerçekleştirir
     * 
     * @param healthData
     * @return Filtreden sonra dönen sağlık değerleri
     */
    public List<HealthDataValue> filter(final HealthData healthData) {
        final List<HealthDataValue> filtered = new ArrayList<HealthDataValue>();
        final List<HealthDataValue> values = healthData.getValues();
        for (final HealthDataValue value : values) {
            final HealthDataField field = value.getField();
            if (field.getNotification()) {
                checkUpperLimit(filtered, field, value);
                checkLowerLimit(filtered, field, value);
            }
        }
        return filtered;
    }

    /**
     * Değerin altlimit ten büyük olduğunu kontrol eder
     * 
     * @param filtered
     * @param field
     * @param value
     */
    private void checkLowerLimit(final List<HealthDataValue> filtered,
            final HealthDataField field, final HealthDataValue value) {
        final Double fieldValue = value.getValue();
        final Double lowerLimit = field.getLowerLimit();
        if (fieldValue.compareTo(lowerLimit) < 0) {
            filtered.add(value);
        }
    }

    /**
     * değerin üst limitten küçük olduğunu kontrol eder
     * 
     * @param filtered
     * @param field
     * @param value
     */
    private void checkUpperLimit(final List<HealthDataValue> filtered,
            final HealthDataField field, final HealthDataValue value) {
        final Double fieldValue = value.getValue();
        final Double upperLimit = field.getUpperLimit();
        if (fieldValue.compareTo(upperLimit) > 0) {
            filtered.add(value);
        }
    }

}
