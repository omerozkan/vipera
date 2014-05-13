package info.ozkan.vipera.dao.healthdata;

import info.ozkan.vipera.business.healthdata.HealthDataResult;
import info.ozkan.vipera.dao.patient.PatientDao;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.healthdata.HealthDataFieldTestData;
import info.ozkan.vipera.patient.PatientTestData;
import info.ozkan.vipera.test.IntegrationTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link HealthDataDao} entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataDaoIntegrationTest extends IntegrationTest {
    /**
     * Sağlık alanı
     */
    @Inject
    private HealthDataDao healthDataDao;
    /**
     * Hasta bilgisi
     */
    @Inject
    private PatientDao patientDao;
    /**
     * Test alanları
     */
    private final List<HealthDataField> testDataFields = new ArrayList<HealthDataField>();
    /**
     * Sisteme eklenen hastalardan biri
     */
    private final Patient marvin = PatientTestData
            .getTestData(PatientTestData.MARVIN);

    /**
     * test verilerini hazırlar
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 4; i++) {
            testDataFields.add(HealthDataFieldTestData.getTestData(i));
        }
    }

    /**
     * Veritabanına yeni bir sağlık alanın eklenebilirliğini test eder
     * 
     * @throws Exception
     */
    @Test
    public void canAdd() throws Exception {
        final HealthData data = createHealthData();

        final HealthDataResult result = healthDataDao.add(data);

        Assert.assertTrue(result.isSuccess());
        Assert.assertNotNull(data.getId());
    }

    /**
     * HealthData nesnesi üretir
     * 
     * @return
     */
    private HealthData createHealthData() {
        final HealthData data = new HealthData();

        final Patient patient = patientDao.getById(marvin.getId()).getPatient();
        data.setPatient(patient);

        final List<HealthDataValue> values = createTestValues(data);
        data.setValues(values);
        data.setSendBy("test");
        data.setDate(new Date());
        return data;
    }

    /**
     * Sağlık değerlerini üretir
     * 
     * @param data
     * @return
     */
    private List<HealthDataValue> createTestValues(final HealthData data) {
        final List<HealthDataValue> values = new ArrayList<HealthDataValue>();
        for (int i = 0; i < 4; i++) {
            final HealthDataValue value = new HealthDataValue();
            final HealthDataField field = testDataFields.get(i);
            final Double dataValue = 12.42;
            value.setField(field);
            value.setData(data);
            value.setValue(dataValue);
        }
        return values;
    }

}
