package info.ozkan.vipera.business.notification;

import static java.util.Arrays.asList;
import info.ozkan.vipera.business.notification.NotificationGenerator;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorNotificationSetting;
import info.ozkan.vipera.entities.HealthData;
import info.ozkan.vipera.entities.HealthDataValue;
import info.ozkan.vipera.entities.Notification;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.patient.PatientTestData;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link NotificationGenerator} test sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class NotificationGeneratorTest {
    /**
     * Android
     */
    private static final String ANDROID = "android";
    /**
     * SMS
     */
    private static final String SMS = "sms";
    /**
     * Test edilen sınıf nesnesi
     */
    private final NotificationGenerator generator = new NotificationGenerator();
    /**
     * Hasta
     */
    private Patient patient;

    @Before
    public void setUp() throws Exception {
        patient = PatientTestData.getTestData(PatientTestData.MARVIN);
    }

    /**
     * Hekimler sistemde hiç bir bildirim almak için onay vermemiştir. Hiç bir
     * bildirim üretilmez.
     * 
     * @throws Exception
     */
    @Test
    public void allDoctorsDisabledForNotificationGetNothing() throws Exception {

        final Doctor doctor1 = createDoctor(false, false);
        final Doctor doctor2 = createDoctor(false, false);
        setDoctors(doctor1, doctor2);

        final HealthData healthData = createHealthData();

        final List<HealthDataValue> filteredValues =
                createDummyFilteredValues();

        final List<Notification> result =
                generator.generate(healthData, filteredValues);
        Assert.assertEquals(0, result.size());
    }

    /**
     * Hekimler sistemde iki yöntemle bildirim almaktadır. İki sağlık alanı ile
     * ilgili bildirim gönderilmek istenir. Hastaya ait iki hekim bulunmaktadır.
     * Toplamda 2 bildirim türü * 2 hekim sayısı * 2 sağlık verisi = 8 adet
     * bildirim gönderilir.
     * 
     * @throws Exception
     */
    @Test
    public void allDoctorEnabledForAllNotificationGet8() throws Exception {
        final Doctor doctor1 = createDoctor(true, true);
        final Doctor doctor2 = createDoctor(true, true);
        setDoctors(doctor1, doctor2);
        final HealthData healthData = createHealthData();
        final List<HealthDataValue> filteredValues =
                createDummyFilteredValues();
        final List<Notification> result =
                generator.generate(healthData, filteredValues);
        Assert.assertEquals(2 * 2 * 2, result.size());
    }

    /**
     * Hekimlerinden sadece biri iki yöntemle bildiri alır. Toplamda 4 bildirim
     * gönderilir.
     * 
     * @throws Exception
     */
    @Test
    public void oneDoctorEnabledForTwoTypeNotificationsGet4() throws Exception {
        final Doctor doctor1 = createDoctor(true, true);
        final Doctor doctor2 = createDoctor(false, false);
        setDoctors(doctor1, doctor2);
        final HealthData healthData = createHealthData();
        final List<HealthDataValue> filteredValues =
                createDummyFilteredValues();
        final List<Notification> result =
                generator.generate(healthData, filteredValues);
        Assert.assertEquals(2 * 2, result.size());
    }

    /**
     * Test verileri için sağlık verisi üretir
     * 
     * @return
     */
    private List<HealthDataValue> createDummyFilteredValues() {
        final List<HealthDataValue> filteredValues =
                new ArrayList<HealthDataValue>();
        final HealthDataValue value1 = new HealthDataValue();
        value1.setValue(1.0);
        final HealthDataValue value2 = new HealthDataValue();
        value2.setValue(1.0);
        filteredValues.add(value1);
        filteredValues.add(value2);
        return filteredValues;
    }

    /**
     * Sağlık verisi nesnesi üretir
     * 
     * @return
     */
    private HealthData createHealthData() {
        final HealthData healthData = new HealthData();
        healthData.setPatient(patient);
        return healthData;
    }

    /**
     * Hastaya hekim atar
     * 
     * @param doctor1
     *            1. hekim
     * @param doctor2
     *            2. hekim
     */
    private void setDoctors(final Doctor doctor1, final Doctor doctor2) {
        patient.setDoctors(asList(doctor1, doctor2));
    }

    /**
     * Hekim nesnesi üretir
     * 
     * @param sms
     * @param android
     * @return
     */
    private Doctor createDoctor(final boolean sms, final boolean android) {
        final DoctorNotificationSetting notification1 =
                createNotification(SMS, sms);
        final DoctorNotificationSetting notification2 =
                createNotification(ANDROID, android);
        final Doctor doctor = new Doctor();
        doctor.setSettings(asList(notification1, notification2));
        return doctor;
    }

    /**
     * Bildirim ayarı üretir
     * 
     * @param provider
     * @param enabled
     * @return
     */
    private DoctorNotificationSetting createNotification(final String provider,
            final boolean enabled) {
        final DoctorNotificationSetting setting =
                new DoctorNotificationSetting();
        setting.setEnabled(enabled);
        setting.setProviderId(provider);
        return setting;
    }
}
