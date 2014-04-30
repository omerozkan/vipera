package info.ozkan.vipera.business.doctor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.DoctorTitle;
import info.ozkan.vipera.models.DoctorBrowseModel;
import info.ozkan.vipera.test.IntegrationTest;

import javax.inject.Inject;

import org.junit.Test;

/**
 * DoctorManager entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorManagerIntegrationTest extends IntegrationTest {
    /**
     * Domain object
     */
    @Inject
    private DoctorManager doctorManager;
    /**
     * House
     */
    private final Doctor house = DoctorTestData
            .getTestData(DoctorTestData.HOUSE);
    /**
     * Demirci
     */
    private final Doctor demirci = DoctorTestData
            .getTestData(DoctorTestData.DEMIRCI);

    /**
     * Sisteme hekim ekler, ekleme işlemi başarılıdır. Veritabanından TCKN ile
     * eklenen doktor sorgulanır.
     * 
     * @throws Exception
     */
    @Test
    public void addDoctor() throws Exception {
        house.setId(null);
        house.setTckn(DoctorTestData.getNextTCKN());
        final DoctorManagerResult result = doctorManager.add(house);
        assertTrue(result.isSuccess());
        final DoctorManagerResult getResult = doctorManager.getByTckn(house
                .getTckn());
        assertEquals(house, getResult.getDoctor());
    }

    /**
     * Ünvanı Uzman Doktor olan hekimler için arama işlemi yapılır Gregory House
     * ve Ismail Demirci sonuç listesinde yer alır
     * 
     * @throws Exception
     */
    @Test
    public void getDoctorsByTitle() throws Exception {
        final DoctorBrowseModel model = new DoctorBrowseModel();
        model.setTitle(DoctorTitle.SPECIALIST);
        final DoctorManagerResult result = doctorManager.search(model);
        assertEquals(2, result.getDoctors().size());
        assertTrue(result.getDoctors().contains(house));
        assertTrue(result.getDoctors().contains(demirci));
    }

    /**
     * Veritabanından bir hekim alınır güncellenir ve tekrar sorgulanır. Hekimin
     * adı ve soyadı güncel haldedir.
     * 
     * @throws Exception
     */
    @Test
    public void getByIdAndUpdate() throws Exception {
        final Long id = house.getId();
        final Doctor doctor = doctorManager.getById(id).getDoctor();
        assertNotNull(doctor);
        final String name = "Updated";
        final String surname = "Doctor";
        doctor.setName(name);
        doctor.setSurname(surname);
        doctorManager.update(doctor);
        final Doctor result = doctorManager.getById(id).getDoctor();
        assertEquals(name, result.getName());
        assertEquals(surname, result.getSurname());
    }

    /**
     * Veritabanından bir hekim sorgular ve o hekimi siler. Aynı hekim tekrar
     * sorgulandığında tekrar bulunamaz.
     * 
     * @throws Exception
     */
    @Test
    public void getByIdAndDelete() throws Exception {
        final Doctor ozkan = DoctorTestData.getTestData(DoctorTestData.OZKAN);
        final Doctor doctor = doctorManager.getById(ozkan.getId()).getDoctor();
        final DoctorManagerResult deleteResult = doctorManager.delete(doctor);
        assertTrue(deleteResult.isSuccess());
        final DoctorManagerResult result = doctorManager.getById(ozkan.getId());
        assertFalse(result.isSuccess());
    }
}
