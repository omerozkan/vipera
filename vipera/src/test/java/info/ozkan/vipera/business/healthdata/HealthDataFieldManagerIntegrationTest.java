package info.ozkan.vipera.business.healthdata;

import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.healthdata.HealthDataFieldTestData;
import info.ozkan.vipera.test.IntegrationTest;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link HealthDataFieldManager} entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataFieldManagerIntegrationTest extends IntegrationTest {
    /**
     * manager
     */
    @Inject
    private HealthDataFieldManager healthDataFieldManager;

    /**
     * Bir sağlık alanının anahtarına göre sorgulanıp sorgulanmayacağını test
     * eder
     * 
     * @throws Exception
     */
    @Test
    public void testGetField() throws Exception {
        final HealthDataField testField =
                HealthDataFieldTestData.getTestData(0);
        final HealthDataField field =
                healthDataFieldManager.getField(testField.getName());
        Assert.assertNotNull(testField.getName());
        Assert.assertEquals(testField.getName(), field.getName());
    }
}
