package info.ozkan.vipera.views.administrator;

import info.ozkan.vipera.entities.Administrator;

import java.util.List;
import java.util.Map;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

/**
 * Yönetici veri modeli
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorModel extends ListDataModel<Administrator> implements
        SelectableDataModel<Administrator> {

    /**
     * Arama işlemlerini hızlandırmak için
     */
    private final Map<Long, Administrator> administrators;

    /**
     * Yeni bir model oluşturur
     * 
     * @param administrators
     *            yöneticiler
     * @param list
     *            yönetici listesi
     */
    public AdministratorModel(final Map<Long, Administrator> administrators,
            final List<Administrator> list) {
        super(list);
        this.administrators = administrators;
    }

    /**
     * satır seçildiğinde seçilen yöneticiyi dönderir
     */
    public Administrator getRowData(final String id) {
        return administrators.get(new Long(id));
    }

    /**
     * anahtar
     */
    public Object getRowKey(final Administrator row) {
        return row.getId();
    }

}
