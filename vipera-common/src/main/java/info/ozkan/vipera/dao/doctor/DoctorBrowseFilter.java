package info.ozkan.vipera.dao.doctor;

import java.util.HashMap;
import java.util.Map;

/**
 * Veritabanından hekim arama işlemi için kullanılan filtre sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorBrowseFilter {

    /**
     * Filtreler
     */
    private final Map<String, Object> filters = new HashMap<String, Object>();

    /**
     * Yeni bir filtre ekler
     * 
     * @param key
     *            Column id
     * @param value
     *            Değer
     */
    public void addFilter(final String key, final Object value) {
        filters.put(key, value);
    }

    /**
     * @return the filters
     */
    public Map<String, Object> getFilters() {
        return filters;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof DoctorBrowseFilter) {
            final DoctorBrowseFilter filter = (DoctorBrowseFilter) obj;
            return filter.filters.equals(filters);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return filters.hashCode();
    }

}
