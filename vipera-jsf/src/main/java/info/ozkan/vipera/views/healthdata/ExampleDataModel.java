package info.ozkan.vipera.views.healthdata;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class ExampleDataModel extends ListDataModel<ExampleData> implements
        SelectableDataModel<ExampleData> {

    public ExampleDataModel(final List<ExampleData> data) {
        super(data);
    }

    public ExampleData getRowData(final String arg0) {
        final List<ExampleData> datas = (List<ExampleData>) getWrappedData();
        for (final ExampleData data : datas) {
            if (data.getFieldName().equals(arg0)) {
                return data;
            }
        }
        return null;
    }

    public Object getRowKey(final ExampleData arg0) {
        return arg0.getFieldName();
    }

}
