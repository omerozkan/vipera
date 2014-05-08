package info.ozkan.vipera.views.healthdata;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

@Named("healthDataFields")
public class HealthDataFieldsBean {
    private List<ExampleData> datas;
    private ExampleData selectedData;
    private final ExampleDataModel model;

    public HealthDataFieldsBean() {
        datas = new ArrayList<ExampleData>();
        for (int i = 0; i < 20; i++) {
            final ExampleData data = new ExampleData();
            data.setId(Long.valueOf(i));
            data.setFieldName("ex" + i);
            data.setTitle("Ex" + i);
            data.setUnit("unit" + i);
            datas.add(data);
        }
        model = new ExampleDataModel(datas);
    }

    /**
     * @return the data
     */
    public List<ExampleData> getDatas() {
        return datas;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setDatas(final List<ExampleData> data) {
        datas = data;
    }

    /**
     * @return the selectedData
     */
    public ExampleData getSelectedData() {
        return selectedData;
    }

    /**
     * @param selectedData
     *            the selectedData to set
     */
    public void setSelectedData(final ExampleData selectedData) {
        this.selectedData = selectedData;
        System.out.println("selected data" + selectedData);
    }

    /**
     * @return the model
     */
    public ExampleDataModel getModel() {
        return model;
    }
}
