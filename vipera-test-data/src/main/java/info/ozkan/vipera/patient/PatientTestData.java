package info.ozkan.vipera.patient;

import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.entities.Sex;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Hastalarla ilgili test verileri
 * 
 * @author Ömer Özkan
 * 
 */
public final class PatientTestData {
    /**
     * Marvin id
     */
    public static final int MARVIN = 0;
    /**
     * Sam id
     */
    public static final int SAM = 1;
    /**
     * Ozkan id
     */
    private static final int OZKAN = 2;
    /**
     * test nesneler
     */
    private static final Map<Integer, Patient> testPatients = new HashMap<Integer, Patient>();

    /**
     * Utilty class
     */
    private PatientTestData() {

    }

    /**
     * Marvin
     * 
     * @return
     */
    private static Patient createMarvin() {
        final Patient patient = new Patient();
        patient.setId(5l);
        patient.setTckn(22345678901l);
        patient.setPassword("password");
        patient.setName("Marvin");
        patient.setSurname("Ericson");
        patient.setEmail("demo@vipera.ozkan.info");
        patient.setSex(Sex.MALE);
        patient.setBirthDate(createDate(2012, Calendar.JANUARY, 1));
        patient.setBirthPlace("New York");
        patient.setFatherName("Marshall");
        patient.setMotherName("Lily");
        patient.setPhone("1123123123");
        patient.setMobilePhone("1123123123");
        patient.setEnable(Authorize.ENABLE);
        return patient;
    }

    /**
     * Sam
     * 
     * @return
     */
    private static Patient createSam() {
        final Patient patient = new Patient();
        patient.setId(6l);
        patient.setTckn(22345678902l);
        patient.setPassword("password");
        patient.setName("Sam");
        patient.setSurname("Goodson");
        patient.setEmail("demo@vipera.ozkan.info");
        patient.setSex(Sex.FEMALE);
        patient.setBirthDate(createDate(2012, Calendar.JANUARY, 1));
        patient.setBirthPlace("Chicago");
        patient.setFatherName("Charlie");
        patient.setMotherName("Jennifer");
        patient.setPhone("1123123123");
        patient.setMobilePhone("1123123123");
        patient.setEnable(Authorize.ENABLE);
        return patient;
    }

    /**
     * Özkan
     * 
     * @return
     */
    private static Patient createOzkan() {
        final Patient patient = new Patient();
        patient.setId(7l);
        patient.setTckn(22345678903l);
        patient.setPassword("password");
        patient.setName("Ömer");
        patient.setSurname("Özkan");
        patient.setEmail("vipera@ozkan.info");
        patient.setSex(Sex.MALE);
        patient.setBirthDate(createDate(1990, Calendar.OCTOBER, 20));
        patient.setBirthPlace("Ceyhan");
        patient.setFatherName("Reşit");
        patient.setMotherName("Nesibe");
        patient.setPhone("1123123123");
        patient.setMobilePhone("1123123123");
        patient.setEnable(Authorize.ENABLE);
        return patient;
    }

    /**
     * Date nesnesi oluşturur
     * 
     * @param year
     *            Gün
     * @param mounth
     *            Ay
     * @param day
     *            Yıl
     * @return
     */
    private static Date createDate(final int year, final int mounth,
            final int day) {
        final Calendar calendar = new GregorianCalendar();
        calendar.set(year, mounth, day);
        final Date date = calendar.getTime();
        return date;
    }

    /**
     * Hasta test verisi oluşturur. id değeri için {@link PatientTestData}
     * sınıfının static değişkenlerini kullanın
     * 
     * @param id
     *            Id
     * @return
     */
    public static Patient getTestData(final int id) {
        if (testPatients.isEmpty()) {
            testPatients.put(MARVIN, createMarvin());
            testPatients.put(SAM, createSam());
            testPatients.put(OZKAN, createOzkan());
        }
        final Patient patient = testPatients.get(id);
        return (Patient) patient.clone();
    }
}