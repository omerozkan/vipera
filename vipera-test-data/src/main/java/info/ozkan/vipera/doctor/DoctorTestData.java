package info.ozkan.vipera.doctor;

import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorNotificationSetting;
import info.ozkan.vipera.entities.DoctorTitle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Hekimlerle ilgili test verileri
 * 
 * @author Ömer Özkan
 * 
 */
public final class DoctorTestData {
    /**
     * Gregory House test id
     */
    public static final int HOUSE = 0;
    /**
     * Ismail Demirci test id
     */
    public static final int DEMIRCI = 1;
    /**
     * Ömer Özkan test id
     */
    public static final int OZKAN = 2;

    /**
     * Test verilerin çakışmaması için edinilen sonraki TCKN
     */
    private static long nextTckn = 12345678904L;

    /**
     * Hekim listesi
     */
    private static Map<Integer, Doctor> doctors;

    /**
     * private constructor
     */
    private DoctorTestData() {
    }

    /**
     * İsmail Demirci adında bir hekim nesnesi oluşturur
     * 
     * @return
     */
    private static Doctor createDemirci() {
        final Doctor demirci = new Doctor();
        demirci.setId(3l);
        demirci.setTckn(12345678902L);
        demirci.setPassword("password");
        demirci.setEmail("ismail@drdemirci.com");
        demirci.setName("Ismail");
        demirci.setSurname("Demirci");
        demirci.setTitle(DoctorTitle.SPECIALIST);
        demirci.setDiplomaNo("12345");
        demirci.setProvince("Dahiliye");
        demirci.setPhone("+905555555");
        demirci.setMobilePhone("+905553333333");
        demirci.setEnabled(Authorize.ENABLE);
        demirci.setSettings(new ArrayList<DoctorNotificationSetting>());
        return demirci;
    }

    /**
     * Gregory House adında bir hekim nesnesi oluşturur
     * 
     * @return
     */
    private static Doctor createHouse() {
        final Doctor house = new Doctor();
        house.setId(2l);
        house.setTckn(12345678901L);
        house.setPassword("password");
        house.setEmail("doctor@doctor.com");
        house.setName("Metin");
        house.setSurname("Özkan");
        house.setTitle(DoctorTitle.SPECIALIST);
        house.setDiplomaNo("12345");
        house.setProvince("Bulaşıcı Hastalıklar ve Nefroloji");
        house.setWebpage("http://www.greghouse.com");
        house.setPhone("+905555555");
        house.setMobilePhone("+905553333333");
        house.setEnabled(Authorize.ENABLE);
        house.setApiKey("eeda2ced4fede33c");
        house.setSettings(new ArrayList<DoctorNotificationSetting>());
        return house;
    }

    /**
     * Ömer Özkan adında bir hekim nesnesi oluşturur
     * 
     * @return
     */
    private static Doctor createOzkan() {
        final Doctor ozkan = new Doctor();
        ozkan.setId(4l);
        ozkan.setTckn(12345678903L);
        ozkan.setPassword("password");
        ozkan.setEmail("omer@ozkan.info");
        ozkan.setName("Ömer");
        ozkan.setSurname("Özkan");
        ozkan.setTitle(DoctorTitle.DOCTOR);
        ozkan.setDiplomaNo("12345");
        ozkan.setProvince("Deletable data");
        ozkan.setWebpage("http://ozkan.info");
        ozkan.setPhone("+905555555");
        ozkan.setMobilePhone("+905553333333");
        ozkan.setEnabled(Authorize.ENABLE);
        ozkan.setSettings(new ArrayList<DoctorNotificationSetting>());
        return ozkan;

    }

    /**
     * Test verilerin çakışmaması için unique bir TC kimlik numarası üretir
     * 
     * @return
     */
    public static long getNextTCKN() {
        return nextTckn++;
    }

    /**
     * Test Hekim verisi al
     * 
     * @param id
     *            0 -> Gregory House, 1 -> Ismail Demirci
     * @return
     */
    public static Doctor getTestData(final int id) {
        final Doctor doctor;
        if (doctors == null) {
            initializeDoctors();
        }
        doctor = doctors.get(id);
        try {
            return (Doctor) doctor.clone();
        } catch (final CloneNotSupportedException e) {
            throw new AssertionError(e.getMessage(), e);
        }
    }

    /**
     * Hekimleri ilklendirir
     */
    private static void initializeDoctors() {
        doctors = new HashMap<Integer, Doctor>();
        doctors.put(HOUSE, createHouse());
        doctors.put(DEMIRCI, createDemirci());
        doctors.put(OZKAN, createOzkan());
    }

}
