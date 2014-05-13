package info.ozkan.vipera.healthdata;

import info.ozkan.vipera.entities.HealthDataField;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link HealthDataField} örnek verileri
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataFieldTestData {
    /**
     * Alanlar
     */
    private static final Map<Integer, HealthDataField> fields = new HashMap<Integer, HealthDataField>();

    /**
     * utility class
     */
    private HealthDataFieldTestData() {
    }

    /**
     * Örnek veri üretir.
     * 
     * @param key
     *            0 - 3 arası bir değer giriniz
     * @return
     */
    public static HealthDataField getTestData(final int key) {
        if (fields.isEmpty()) {
            initiAlizeTestData();
        }
        return fields.get(key);
    }

    /**
     * Test verilerini üretir
     */
    private static void initiAlizeTestData() {
        for (int i = 8; i < 12; i++) {
            final HealthDataField field = new HealthDataField();
            field.setId(Long.valueOf(i));
            field.setName("testField" + i);
            field.setTitle("Test Field " + i);
            field.setUnit("tph");
            fields.put(i - 8, field);
        }
    }

}
