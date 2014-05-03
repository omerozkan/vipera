package info.ozkan.vipera.business.patient;

import java.util.HashMap;
import java.util.Map;

/**
 * Hasta arama filtre sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class PatientSearchFilter {
    /**
     * Filtreler
     */
    private final Map<String, Object> filters = new HashMap<String, Object>();

    /**
     * Filtre ekler
     * 
     * @param attribute
     * @param value
     */
    public void addFilter(final String attribute, final Object value) {
        filters.put(attribute, value);
    }

    /**
     * Filteler
     * 
     * @return
     */
    public Map<String, Object> getFilters() {
        return filters;
    }

}
