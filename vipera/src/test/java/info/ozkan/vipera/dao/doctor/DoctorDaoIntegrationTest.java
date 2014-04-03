package info.ozkan.vipera.dao.doctor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.test.IntegrationTest;

import javax.inject.Inject;

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
		final Doctor doctor = DoctorTestData.getTestData();
		final DoctorDaoResult result = doctorDao.add(doctor);
		assertTrue(result.isSuccess());
		final Doctor resultDoctor = doctorDao.get(doctor.getTckn()).getDoctor();
		assertEquals(doctor, resultDoctor);
	}

	/**
	 * @param doctorDao
	 *            the doctorDao to set
	 */
	public void setDoctorDao(final DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
}
