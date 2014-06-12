package info.ozkan.vipera.views.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataFieldFacade;
import info.ozkan.vipera.business.healthdata.HealthDataFieldResult;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.jsf.FacesMessage2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * Hasta alanları ile ilgili işlemlerin yapılabildiği ekranın Bean sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataFields")
@Scope("session")
public class HealthDataFieldsBean {
    /**
     * Silme işlemi başarılı mesaj detay deseni
     */
    private static final String DELETING_SUCCESS_MSG_DETAIL_PATTERN =
            "%s silindi!";
    /**
     * Silme işlemi başarılı mesajı
     */
    private static final String DELETING_SUCCESS_MSG = "Silme işlemi başarılı!";
    /**
     * Silme işlemi başarısız mesaj detay deseni
     */
    private static final String DELETING_FAIL_MSG_DETAIL =
            "Silme işlemi gerçekleştirilemedi. Alan daha önce bir başkası tarafından silinmiş olabilir";
    /**
     * Silme işlemi başarısız mesajı
     */
    private static final String DELETING_FAIL_MSG = "Silme işlemi başarısız";
    /**
     * ekleme başaırsız hata mesajı
     */
    private static final String ADDING_FAIL_MSG = "Ekleme Başarısız!";
    /**
     * Yeni alan eklendi detay mesajı deseni
     */
    private static final String NEW_FIELD_ADDED_MSG_DETAIL_PATTERN =
            "%s sisteme eklendi!";
    /**
     * yeni alan eklendi mesajı
     */
    private static final String NEW_FIELD_ADDED_MSG = "Yeni alan eklendi!";
    /**
     * alan adı benzersiz olmalıdır hata mesajı
     */
    private static final String NON_UNIQUE_FIELD_NAME =
            "Alan adı benzersiz olmalıdır.";
    /**
     * güncelleme başarısız hata emsajı
     */
    private static final String UPDATE_UNSUCCESS_MSG = "Güncelleme Başarısız!";
    /**
     * güncelelme başarılı mesaj detayı deseni
     */
    private static final String UPDATE_SUCCESS_MSG_DETAIL_PATTERN =
            "%s güncellendi!";
    /**
     * Güncelleme başarılı hata mesajı
     */
    private static final String UPDATE_SUCCESS_MSG = "Güncelleme Başarılı!";
    /**
     * Seçilen alan
     */
    private HealthDataField selectedField;
    /**
     * Yeni alan (eklenecek olan alan)
     */
    private HealthDataField newField = new HealthDataField();
    /**
     * Primefaces tarafından zorunlu kılınan interface'in implementasyonu model
     * sınıfı
     */
    private HealthDataFieldModel model;
    /**
     * İşletme katmanı
     */
    @Inject
    private HealthDataFieldFacade healthDataFieldFacade;

    /**
     * Verilerin yüklenmesini sağlar
     */
    public void loadData() {
        if (model == null) {
            initializeModel();
        }
    }

    /**
     * model nesnesini ilklendirir
     */
    private void initializeModel() {
        final HealthDataFieldResult result = healthDataFieldFacade.getFields();
        final List<HealthDataField> dataList = result.getHealthDataFields();
        model = new HealthDataFieldModel(createMapFromList(dataList), dataList);
    }

    /**
     * Bir liste den map türetir
     * 
     * @param list
     * @return
     */
    private Map<String, HealthDataField> createMapFromList(
            final List<HealthDataField> list) {
        final Map<String, HealthDataField> fields =
                new HashMap<String, HealthDataField>();
        for (int i = 0; i < list.size(); i++) {
            final HealthDataField field = list.get(i);
            fields.put(field.getName(), field);
        }
        return fields;
    }

    /**
     * Güncelleme işlemi yapar
     */
    public void update() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final HealthDataFieldResult result =
                healthDataFieldFacade.update(getSelectedField());
        if (result.isSuccess()) {
            final String detail =
                    String.format(UPDATE_SUCCESS_MSG_DETAIL_PATTERN,
                            getSelectedField().getTitle());
            createInfoMessage(context, UPDATE_SUCCESS_MSG, detail);
            initializeModel();
        } else {
            createErrorMessage(context, UPDATE_UNSUCCESS_MSG,
                    NON_UNIQUE_FIELD_NAME);
        }
    }

    /**
     * Yeni bir alan ekler
     */
    public void addNew() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final HealthDataFieldResult result =
                healthDataFieldFacade.add(newField);
        if (result.isSuccess()) {
            final String detail =
                    String.format(NEW_FIELD_ADDED_MSG_DETAIL_PATTERN,
                            newField.getTitle());
            createInfoMessage(context, NEW_FIELD_ADDED_MSG, detail);
            updateFieldsForScreen();
        } else {
            createErrorMessage(context, ADDING_FAIL_MSG, NON_UNIQUE_FIELD_NAME);
        }
    }

    /**
     * Silme işlemi gerçekleştirir
     */
    public void delete() {
        final FacesContext context = FacesContext.getCurrentInstance();
        final HealthDataFieldResult result =
                healthDataFieldFacade.remove(selectedField);
        if (result.isSuccess()) {
            final String detail =
                    String.format(DELETING_SUCCESS_MSG_DETAIL_PATTERN,
                            selectedField.getTitle());
            createInfoMessage(context, DELETING_SUCCESS_MSG, detail);
            updateFieldsForScreen();
        } else {
            createErrorMessage(context, DELETING_FAIL_MSG,
                    DELETING_FAIL_MSG_DETAIL);
        }
    }

    /**
     * Hata mesajı oluşturur
     * 
     * @param context
     *            context
     * @param summary
     *            özet
     * @param detail
     *            detay
     */
    private void createErrorMessage(final FacesContext context,
            final String summary, final String detail) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_ERROR,
                summary, detail));
    }

    /**
     * Info mesajı oluşturur
     * 
     * @param context
     *            context
     * @param summary
     *            özet
     * @param detail
     *            detay
     */
    private void createInfoMessage(final FacesContext context,
            final String summary, final String detail) {
        context.addMessage(null, new FacesMessage2(FacesMessage.SEVERITY_INFO,
                summary, detail));
    }

    /**
     * model sınıfını yeniler
     */
    private void updateFieldsForScreen() {
        initializeModel();
        newField = new HealthDataField();
        selectedField = new HealthDataField();
    }

    /**
     * @return the model
     */
    public HealthDataFieldModel getModel() {
        return model;
    }

    /**
     * @param healthDataFieldFacade
     *            the healthDataFieldFacade to set
     */
    public void setHealthDataFieldFacade(
            final HealthDataFieldFacade healthDataFieldFacade) {
        this.healthDataFieldFacade = healthDataFieldFacade;
    }

    /**
     * @return the newField
     */
    public HealthDataField getNewField() {
        return newField;
    }

    /**
     * @param newField
     *            the newField to set
     */
    public void setNewField(final HealthDataField newField) {
        this.newField = newField;
    }

    /**
     * @return the selectedField
     */
    public HealthDataField getSelectedField() {
        return selectedField;
    }

    /**
     * @param selectedField
     *            the selectedField to set
     */
    public void setSelectedField(final HealthDataField selectedField) {
        this.selectedField = selectedField;
    }
}
