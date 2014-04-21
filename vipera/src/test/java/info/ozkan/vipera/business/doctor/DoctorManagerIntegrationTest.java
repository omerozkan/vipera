package info.ozkan.vipera.business.doctor;

import static org.junit.Assert.assertEquals;
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
	@Inject
	private DoctorManager doctorManager;

	/**
	 * Sisteme hekim ekler, ekleme işlemi başarılıdır. Veritabanından TCKN ile
	 * eklenen doktor sorgulanır.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDoctor() throws Exception {
		final Doctor doctor = DoctorTestData.getTestData();
		doctor.setTckn(1111111111l);
		final DoctorManagerResult result = doctorManager.add(doctor);
		assertTrue(result.isSuccess());
		final DoctorManagerResult getResult = doctorManager.get(doctor
		        .getTckn());
		assertEquals(doctor, getResult.getDoctor());
	}

	/**
	 * Ünvanı Uzman Doktor olan hekimler için arama işlemi yapılır Gregory House
	 * ve Ismail Demirci sonuç listesinde yer alır
	 * 
	 * @throws Exception
	 */
	@Test
	public void getDoctorsByTitle() throws Exception {
		final Doctor house = DoctorTestData.getTestData();
		final Doctor demirci = DoctorTestData.getTestData2();
		final DoctorBrowseModel model = new DoctorBrowseModel();
		model.setTitle(DoctorTitle.SPECIALIST);
		final DoctorManagerResult result = doctorManager.search(model);
		assertEquals(2, result.getDoctors().size());
		assertTrue(result.getDoctors().contains(house));
		assertTrue(result.getDoctors().contains(demirci));
	}
}
