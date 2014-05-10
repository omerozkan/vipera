package info.ozkan.vipera.views.healthdata;

import info.ozkan.vipera.entities.HealthDataField;

import java.util.List;
import java.util.Map;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

/**
 * Sağlık veri alanı ekranında kullanılan model sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataFieldModel extends ListDataModel<HealthDataField>
        implements SelectableDataModel<HealthDataField> {
    /**
     * Arama işlemini hızlandırmak için kullanılan map
     */
    private final Map<String, HealthDataField> fields;

    /**
     * Yeni bir model oluşturur
     * 
     * @param fields
     *            arama yapmak için gereken map nesnesi
     * @param list
     *            alan listesi - fields ile aynı değerlere sahip olması gerekir
     */
    public HealthDataFieldModel(final Map<String, HealthDataField> fields,
            final List<HealthDataField> list) {
        super(list);
        this.fields = fields;
    }

    /**
     * satır seçildiğinde yeni bir nesne seçilir
     */
    public HealthDataField getRowData(final String key) {
        return fields.get(key);
    }

    /**
     * anahtar kelime
     */
    public Object getRowKey(final HealthDataField field) {
        return field.getName();
    }

}
