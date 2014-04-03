package info.ozkan.vipera.business.doctor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;
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
		final DoctorManagerResult result = doctorManager.add(doctor);
		assertTrue(result.isSuccess());
		final DoctorManagerResult getResult = doctorManager.get(doctor
		        .getTckn());
		assertEquals(doctor, getResult.getDoctor());
	}
}
