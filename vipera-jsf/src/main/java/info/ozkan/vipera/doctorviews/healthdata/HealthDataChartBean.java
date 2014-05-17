package info.ozkan.vipera.doctorviews.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataFacade;
import info.ozkan.vipera.business.healthdata.HealthDataFieldFacade;
import info.ozkan.vipera.business.healthdata.HealthDataFieldResult;
import info.ozkan.vipera.business.healthdata.HealthDataResult;
import info.ozkan.vipera.business.healthdata.HealthDataSearchFilter;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Patient;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

/**
 * Birden fazla alanı bar grafiği olarak gösteren ekran
 * 
 * @author Ömer Özkan
 * 
 */
@Named("healthDataChart")
@Scope("session")
public class HealthDataChartBean {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(HealthDataChartBean.class);
    /**
     * grafik oluşturuldu mesajı
     */
    private static final String MSG_CHART_CREATED = "Grafik oluşturuldu!";
    /**
     * Veri bulunamadı mesajı
     */
    private static final String MSG_DATA_NOT_FOUND = "Veri bulunamadı!";
    /**
     * boş string
     */
    private static final String EMPTY = "";
    /**
     * alan seç mesajı
     */
    private static final String MSG_SELECT_FIELD =
            "Lütfen en az bir tane alan seçiniz!";
    /**
     * Hasta seç mesajı
     */
    private static final String MSG_SELECT_PATIENT =
            "Lütfen bir hasta seçiniz!";
    /**
     * Hasta
     */
    private Patient patient;
    /**
     * başlangıç tarihi
     */
    private Date startDate;
    /**
     * bitiş tarihi
     */
    private Date endDate;
    /**
     * alanlar
     */
    private List<HealthDataField> fields;
    /**
     * seçilen alanlar
     */
    private List<String> selectedFields;
    /**
     * chart
     */
    private CartesianChartModel lineChartModel;
    /**
     * chart render edilsin mi
     */
    private boolean renderChart = false;
    /***
     * alan işlemleri için işletme nesnesi
     */
    @Inject
    private HealthDataFieldFacade healthDataFieldFacade;
    /**
     * veriler için işletme nesnesi
     */
    @Inject
    private HealthDataFacade healthDataFacade;

    /**
     * sayfayı ilklendirir
     */
    @PostConstruct
    public void setUp() {
        final HealthDataFieldResult result = healthDataFieldFacade.getFields();
        fields = result.getHealthDataFields();
    }

    /**
     * grafiği gösterir
     */
    public void show() {
        final FacesContext context = FacesContext.getCurrentInstance();
        boolean canDraw = true;
        if (patient == null) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, MSG_SELECT_PATIENT, EMPTY));
            canDraw = false;
        }
        if (selectedFields.size() == 0) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, MSG_SELECT_FIELD, EMPTY));
            canDraw = false;
        }
        if (canDraw) {
            drawChart(context);
        }
    }

    /**
     * verileri hesaplar ve grafiği çizer
     * 
     * @param context
     */
    private void drawChart(final FacesContext context) {
        final HealthDataSearchFilter filter = createSearchFilter();
        final HealthDataResult result = healthDataFacade.find(filter);
        final List<HealthData> datas = result.getHealthDataList();
        if (datas.size() == 0) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, MSG_DATA_NOT_FOUND, EMPTY));
        } else {
            createChart(datas);
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, MSG_CHART_CREATED, EMPTY));
            LOGGER.info("The chart drawed with {} values and {} fields",
                    datas.size(), selectedFields.size());
        }
    }

    /**
     * arama filtresi oluşturur
     * 
     * @return
     */
    private HealthDataSearchFilter createSearchFilter() {
        final HealthDataSearchFilter filter = new HealthDataSearchFilter();
        filter.setPatient(patient);
        filter.setStartDate(startDate);
        filter.setEndDate(endDate);
        return filter;
    }

    /**
     * grafik oluşturur
     * 
     * @param datas
     */
    private void createChart(final List<HealthData> datas) {
        final CartesianChartModel model = new CartesianChartModel();
        for (final String fieldName : selectedFields) {
            final HealthDataField field =
                    healthDataFieldFacade.getField(fieldName);
            final LineChartSeries series = new LineChartSeries();
            series.setLabel(field.getTitle());
            for (final HealthData data : datas) {
                if (data.exist(field)) {
                    final HealthDataValue value = data.getValue(field);
                    final Long id = data.getId();
                    series.set(id, value.getValue());
                }
            }
            model.addSeries(series);
        }
        lineChartModel = model;
        configureChart();
    }

    /**
     * grafiği yapılandırır
     */
    private void configureChart() {
        renderChart = true;
    }

    /**
     * Seçilen hastayı tanımlar
     * 
     * @param patient
     */
    public void selectPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient
     *            the patient to set
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the fields
     */
    public List<HealthDataField> getFields() {
        return fields;
    }

    /**
     * @param fields
     *            the fields to set
     */
    public void setFields(final List<HealthDataField> fields) {
        this.fields = fields;
    }

    /**
     * @return the selectedFields
     */
    public List<String> getSelectedFields() {
        return selectedFields;
    }

    /**
     * @param selectedFields
     *            the selectedFields to set
     */
    public void setSelectedFields(final List<String> selectedFields) {
        this.selectedFields = selectedFields;
    }

    /**
     * @return the lineChartModel
     */
    public CartesianChartModel getLineChartModel() {
        return lineChartModel;
    }

    /**
     * @param lineChartModel
     *            the lineChartModel to set
     */
    public void setLineChartModel(final CartesianChartModel lineChartModel) {
        this.lineChartModel = lineChartModel;
    }

    /**
     * @return the renderChart
     */
    public boolean isRenderChart() {
        return renderChart;
    }

    /**
     * @param renderChart
     *            the renderChart to set
     */
    public void setRenderChart(final boolean renderChart) {
        this.renderChart = renderChart;
    }

}
