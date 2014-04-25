package info.ozkan.vipera.dao.doctor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorTitle;
import info.ozkan.vipera.test.IntegrationTest;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

/**
 * DoctorDao entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorDaoIntegrationTest extends IntegrationTest {
    /**
     * DoctorDao
     */
    @Inject
    private DoctorDao doctorDao;

    /**
     * Yönetici veritabanına hekim ekler
     * 
     * @throws Exception
     */
    @Test
    public void addDoctor() throws Exception {
        final Doctor doctor = DoctorTestData
                .getTestData(DoctorTestData.DEMIRCI);
        doctor.setId(null);
        doctor.setTckn(DoctorTestData.getNextTCKN());
        final DoctorDaoResult result = doctorDao.add(doctor);
        assertTrue(result.isSuccess());
        final Doctor resultDoctor = doctorDao.getByTckn(doctor.getTckn())
                .getDoctor();
        assertEquals(doctor, resultDoctor);
    }

    /**
     * Yönetici, TCKN girerek hekim arar. Sistemde kayıtlı bir doktor bulunur.
     * İlgili doktor listelenir. TCKN girildiğinde diğer alanlara bakılmaz.
     * 
     * @throws Exception
     */
    @Test
    public void findDoctorByTCKN() throws Exception {
        final Doctor doctor = DoctorTestData.getTestData(DoctorTestData.HOUSE);
        final DoctorBrowseFilter model = new DoctorBrowseFilter();
        model.addFilter(Doctor.TCKN, doctor.getTckn());
        final List<Doctor> result = doctorDao.find(model);
        assertEquals(1, result.size());
        assertEquals(doctor, result.get(0));
    }

    /**
     * Yönetici, hekim adına "greg" yazar. Gregory House sonuç listesinde yer
     * alır.
     * 
     * @throws Exception
     */
    @Test
    public void findDoctorByName() throws Exception {
        final Doctor doctor = DoctorTestData.getTestData(DoctorTestData.HOUSE);
        final DoctorBrowseFilter model = new DoctorBrowseFilter();
        model.addFilter(Doctor.NAME, "reg");
        final List<Doctor> result = doctorDao.find(model);
        assertEquals(1, result.size());
        assertEquals(doctor, result.get(0));
    }

    /**
     * Yönetici, ünvana "Uzman Doktor" değerini girer. House ve Demirci listede
     * yer alır.
     * 
     * @throws Exception
     */
    @Test
    public void findDoctorsByTitle() throws Exception {
        final Doctor house = DoctorTestData.getTestData(DoctorTestData.HOUSE);
        final Doctor demirci = DoctorTestData
                .getTestData(DoctorTestData.DEMIRCI);
        final DoctorBrowseFilter model = new DoctorBrowseFilter();
        model.addFilter(Doctor.TITLE, DoctorTitle.SPECIALIST);
        final List<Doctor> result = doctorDao.find(model);
        assertEquals(2, result.size());
        assertTrue(result.contains(house));
        assertTrue(result.contains(demirci));
    }

    /**
     * Yönetici ünvana "Uzman Doktor" ve Soyad değerine "emir" girer sonuç
     * olarak sadece Demirci gösterilir
     * 
     * @throws Exception
     */
    @Test
    public void findDoctorByTitleAndSurname() throws Exception {
        final Doctor doctor = DoctorTestData
                .getTestData(DoctorTestData.DEMIRCI);
        final DoctorBrowseFilter filter = new DoctorBrowseFilter();
        filter.addFilter(Doctor.TITLE, DoctorTitle.SPECIALIST);
        filter.addFilter(Doctor.SURNAME, doctor.getSurname().substring(1, 4));
        final List<Doctor> result = doctorDao.find(filter);
        assertEquals(1, result.size());
        assertEquals(doctor, result.get(0));
    }

    /**
     * Veritabanından bir hekim alınır güncellenir ve tekrar sorgulanır. Hekimin
     * adı ve soyadı güncel haldedir.
     * 
     * @throws Exception
     */
    @Test
    public void getByIdAndUpdateDoctor() throws Exception {
        final Doctor house = DoctorTestData.getTestData(DoctorTestData.HOUSE);
        final Doctor doctor = doctorDao.getById(house.getId()).getDoctor();
        Assert.assertNotNull(doctor);
        final String name = "Updated";
        doctor.setName(name);
        final String surname = "Doctor";
        doctor.setSurname(surname);
        final DoctorDaoResult result = doctorDao.update(doctor);
        assertTrue(result.isSuccess());
        final Doctor updatedDoctor = doctorDao.getById(house.getId())
                .getDoctor();
        Assert.assertNotNull(updatedDoctor);
        assertEquals(name, updatedDoctor.getName());
        assertEquals(surname, updatedDoctor.getSurname());
    }

    /**
     * @param doctorDao
     *            the doctorDao to set
     */
    public void setDoctorDao(final DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }
}
