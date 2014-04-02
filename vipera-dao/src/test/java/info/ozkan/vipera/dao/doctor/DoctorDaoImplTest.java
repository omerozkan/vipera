package info.ozkan.vipera.dao.doctor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.business.doctor.DoctorManagerError;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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
	 * Veritabanında kayıtlı bir doktoru TCKN ile sorgulayarak elde eder
	 * 
	 * @throws Exception
	 */
	@Test
	public void getDoctorTest() throws Exception {
		final Query query = createMockQuery();
		configureQueryMethods(query);
		Mockito.when(query.getSingleResult()).thenReturn(doctor);
		final DoctorDaoResult result = doctorDao.get(doctor.getTckn());
		assertTrue(result.isSuccess());
		assertEquals(doctor.getTckn(), result.getDoctor());
		verifyGet(query);
	}

	/**
	 * Veritabanında kayıtlı olmayan bir doktor sorgulanır sonuç başarısızdır
	 * 
	 * @throws Exception
	 */
	@Test
	public void getDoctorNonExist() throws Exception {
		final Query query = createMockQuery();
		configureQueryMethods(query);
		Mockito.when(query.getSingleResult())
		        .thenThrow(new NoResultException());
		final DoctorDaoResult result = doctorDao.get(doctor.getTckn());
		assertFalse(result.isSuccess());
		assertEquals(DoctorManagerError.DOCTOR_NOT_EXIST, result.getError());
	}

	/**
	 * {@link DoctorDaoImpl#get(Long)} metodu için gerekli metodların
	 * çağrıldığını doğrular
	 * 
	 * @param query
	 */
	private void verifyGet(final Query query) {
		Mockito.verify(em).createQuery(DoctorDaoImpl.JQL_GET_BY_TCKN);
		Mockito.verify(query)
		        .setParameter(DoctorDaoImpl.TCKN, doctor.getTckn());
		Mockito.verify(query).getSingleResult();
	}

	/**
	 * em.persist metodunun çağrıldığını doğrular
	 */
	private void verifyPersist() {
		Mockito.verify(em).persist(doctor);
	}

	/**
	 * Mock nesnesi oluşturur
	 * 
	 * @return
	 */
	private Query createMockQuery() {
		final Query query = Mockito.mock(Query.class);
		return query;
	}

	/**
	 * Query nesnesinin metodlarını mocklar
	 * 
	 * @param query
	 */
	private void configureQueryMethods(final Query query) {
		Mockito.when(em.createQuery(DoctorDaoImpl.JQL_GET_BY_TCKN)).thenReturn(
		        query);
		Mockito.when(query.setParameter(DoctorDaoImpl.TCKN, doctor.getTckn()))
		        .thenReturn(query);
	}

}
