package info.ozkan.vipera.dao.doctor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.business.doctor.DoctorManagerError;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * DoctorDaoImpl birim testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorDaoImplTest {
	/**
	 * Persistence
	 */
	private EntityManager em;
	/**
	 * Doctor nesnesi
	 */
	private final Doctor doctor = DoctorTestData.getTestData();
	/**
	 * Test edilen sınıfın nesnesi
	 */
	private final DoctorDaoImpl doctorDao = new DoctorDaoImpl();

	/**
	 * Test verileri
	 */
	@Before
	public void setUp() {
		em = Mockito.mock(EntityManager.class);
		doctorDao.setEntityManager(em);
	}

	/**
	 * TCKN ile kayıtlı sistemde bir hekim bulunmaktadır. Aynı TCKN ile iki ayrı
	 * hekim sisteme kaydedilemez. İlgili hata gösterilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addWithNonUniqueTCKN() throws Exception {
		Mockito.doThrow(new ConstraintViolationException(null)).when(em)
		        .persist(doctor);
		final DoctorDaoResult result = doctorDao.add(doctor);
		assertFalse(result.isSuccess());
		assertTrue(result.getError().equals(DoctorManagerError.TCKN_HAS_EXIST));
		verifyPersist();
	}

	/**
	 * Yönetici, hekim bilgilerini hatasız ve eksiksiz girer. Hekim veritabanına
	 * kaydedilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDoctorSuccessfully() throws Exception {
		final DoctorDaoResult result = doctorDao.add(doctor);
		assertTrue(result.isSuccess());
		verifyPersist();
	}

	/**
	 * em.persist metodunun çağrıldığını doğrular
	 */
	private void verifyPersist() {
		Mockito.verify(em).persist(doctor);
	}
}
