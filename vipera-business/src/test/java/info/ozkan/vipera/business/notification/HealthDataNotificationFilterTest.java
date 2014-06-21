package info.ozkan.vipera.business.notification;

import static java.util.Arrays.asList;
import info.ozkan.vipera.business.notification.HealthDataNotificationFilter;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.HealthDataField;
import info.ozkan.vipera.entities.HealthDataValue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link HealthDataNotificationFilter} sınıfının birim testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class HealthDataNotificationFilterTest {
    /**
     * test edilen sınıf nesnesi
     */
    private final HealthDataNotificationFilter notificationFilter =
            new HealthDataNotificationFilter();
    /**
     * 1. alt limit
     */
    private final double lowerLimit1 = 10.0;
    /**
     * 1. üst limit
     */
    private final double upperLimit1 = 60.0;
    /**
     * 2. alt limit
     */
    private final double lowerLimit2 = 5.0;
    /**
     * 2. üst limit
     */
    private final double upperLimit2 = 100.0;
    /**
     * 1. alan
     */
    private HealthDataField field1;
    /**
     * 2. alan
     */
    private HealthDataField field2;

    /**
     * test verilerini hazırlar
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        field1 = createDataField(lowerLimit1, upperLimit1, true);
        field2 = createDataField(lowerLimit2, upperLimit2, true);
    }

    /**
     * Girilen bütün alanlar için bildirim gönderilme false olarak
     * tanımlanmıştır. Filtreleme sonucunda boş değer döner
     * 
     * @throws Exception
     */
    @Test
    public void getEmptyWithAllNecessaryField() throws Exception {
        field1.setNotification(false);
        field2.setNotification(false);

        final HealthDataValue value1 =
                createDataValue(field1, upperLimit1 + 10);
        final HealthDataValue value2 =
                createDataValue(field2, upperLimit2 - 10);

        final HealthData healthData = createHealthData(value1, value2);

        final List<HealthDataValue> result =
                notificationFilter.filter(healthData);
        Assert.assertEquals(0, result.size());
    }

    /**
     * Girilen bütün alanlar için bildiri gönderme true olarak tanımlıdır. Fakat
     * 
     * @throws Exception
     */
    @Test
    public void getEmptyWithRegularValues() throws Exception {
        final HealthDataValue value1 =
                createDataValue(field1, upperLimit1 - 10);
        final HealthDataValue value2 =
                createDataValue(field2, upperLimit2 - 10);

        final HealthData healthData = createHealthData(value1, value2);

        final List<HealthDataValue> result =
                notificationFilter.filter(healthData);

        Assert.assertEquals(0, result.size());
    }

    /**
     * Bildirim değeri her iki alan için true olarak tanımlıdır. Sadece bir
     * değer üst limitin üstündedir. O değer filtrelemeden geçer
     * 
     * @throws Exception
     */
    @Test
    public void getOneWithOneUpperThanLimit() throws Exception {
        final HealthDataValue value1 =
                createDataValue(field1, upperLimit1 + 10);
        final HealthDataValue value2 =
                createDataValue(field2, upperLimit2 - 10);

        final HealthData healthData = createHealthData(value1, value2);
        final List<HealthDataValue> result =
                notificationFilter.filter(healthData);
        Assert.assertEquals(1, result.size());
    }

    /**
     * Bildirim değeri her iki alan için true olarak tanımlıdır. İki alanında
     * değeri alt limitin altındadır. İki değerde filtreden geçer.
     * 
     * @throws Exception
     */
    @Test
    public void getTwoWithTwoLowerThanLimit() throws Exception {
        final HealthDataValue value1 = createDataValue(field1, lowerLimit1 - 1);
        final HealthDataValue value2 = createDataValue(field2, lowerLimit2 - 1);
        final HealthData healthData = createHealthData(value1, value2);
        final List<HealthDataValue> result =
                notificationFilter.filter(healthData);
        Assert.assertEquals(2, result.size());
    }

    /**
     * Bildirim değerleri true olarak tanımlamış iki alan için ilk değer üst
     * limitten yüksek 2. değer alt limit değerinden düşüktür. Filtreden 2 değer
     * de geçer.
     * 
     * @throws Exception
     */
    @Test
    public void getTwoOneUpperThanLimitOneLowerThanLimit() throws Exception {
        final HealthDataValue value1 =
                createDataValue(field1, upperLimit1 + 10);
        final HealthDataValue value2 = createDataValue(field2, lowerLimit2 - 1);
        final HealthData healthData = createHealthData(value1, value2);
        final List<HealthDataValue> result =
                notificationFilter.filter(healthData);
        Assert.assertEquals(2, result.size());
    }

    /**
     * değeri üretir
     * 
     * @param field1
     * @param value
     * @return
     */
    private HealthDataValue createDataValue(final HealthDataField field1,
            final double value) {
        final HealthDataValue value1 = new HealthDataValue();
        value1.setField(field1);
        value1.setValue(value);
        return value1;
    }

    /**
     * alanı üretir
     * 
     * @param lowerLimit
     * @param upperLimit
     * @param notification
     * @return
     */
    private HealthDataField createDataField(final double lowerLimit,
            final double upperLimit, final boolean notification) {
        final HealthDataField field1 = new HealthDataField();
        field1.setNotification(notification);
        field1.setLowerLimit(lowerLimit);
        field1.setUpperLimit(upperLimit);
        return field1;
    }

    /**
     * sağlık verisi üretir
     * 
     * @param value1
     * @param value2
     * @return
     */
    private HealthData createHealthData(final HealthDataValue value1,
            final HealthDataValue value2) {
        final List<HealthDataValue> values = asList(value1, value2);
        final HealthData healthData1 = new HealthData();
        healthData1.setValues(values);
        final HealthData healthData = healthData1;
        return healthData;
    }
}
