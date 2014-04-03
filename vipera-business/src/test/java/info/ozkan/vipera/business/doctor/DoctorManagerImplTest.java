package info.ozkan.vipera.business.doctor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.dao.doctor.DoctorDao;
import info.ozkan.vipera.dao.doctor.DoctorDaoResult;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * DoctorManagerImpl birim testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorManagerImplTest {
	/**
	 * Veri katmanı nesnesi
	 */
	private DoctorDao dao;
	/**
	 * Test edilen sınıfın nesnesi
	 */
	private final DoctorManagerImpl manager = new DoctorManagerImpl();
	/**
	 * Testlerde kullanılan doctor nesnesi
	 */
	private Doctor doctor;

	/**
	 * Test verilerini ilklendirir
	 */
	@Before
	public void setUp() {
		dao = Mockito.mock(DoctorDao.class);
		doctor = DoctorTestData.getTestData();
		manager.setDoctorDao(dao);
	}

	/**
	 * TCKN ile kayıtlı sistemde bir hekim bulunmaktadır. Aynı TCKN ile iki ayrı
	 * hekim sisteme kaydedilemez. İlgili hata gösterilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDoctorWithExistTCKN() throws Exception {
		final DoctorDaoResult daoResult = createDaoResult(false,
		        DoctorManagerError.TCKN_HAS_EXIST);
		setDaoReturnForAdd(daoResult);
		final DoctorManagerResult result = manager.add(doctor);
		assertFalse(result.isSuccess());
		assertTrue(result.getErrors().contains(
		        DoctorManagerError.TCKN_HAS_EXIST));
		verifyDao();
	}

	/**
	 * Yönetici, hekim bilgilerini hatasız ve eksiksiz girer. Hekim veritabanına
	 * kaydedilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDoctorSuccessfull() throws Exception {
		final DoctorDaoResult daoResult = createDaoResult(true, null);
		setDaoReturnForAdd(daoResult);
		final DoctorManagerResult result = manager.add(doctor);
		assertTrue(result.isSuccess());
		assertTrue(result.getErrors().size() == 0);
	}

	/**
	 * Veritabanında belirli bir TCKN ile kayıtlı olan bir hekim, get metodu ile
	 * elde edilir
	 * 
	 * @throws Exception
	 */
	@Test
	public void getDoctor() throws Exception {
		final DoctorDaoResult daoResult = createDaoResult(true, null);
		daoResult.setDoctor(doctor);
		setDaoReturnForGet(daoResult);
		final DoctorManagerResult result = manager.get(doctor.getTckn());
		assertTrue(result.isSuccess());
		assertEquals(doctor, result.getDoctor());
		Mockito.verify(dao).get(doctor.getTckn());
	}

	/**
	 * Dao get metodunu mock'lar
	 * 
	 * @param daoResult
	 */
	private void setDaoReturnForGet(final DoctorDaoResult daoResult) {
		Mockito.when(dao.get(doctor.getTckn())).thenReturn(daoResult);
	}

	/**
	 * dao metodunun çağrıldığını geçerler
	 */
	private void verifyDao() {
		Mockito.verify(dao).add(doctor);
	}

	/**
	 * Dao nesnesinin add metodu çağrıldığında hangi result nesnesini
	 * döndereceğini tanımlar
	 * 
	 * @param daoResult
	 */
	private void setDaoReturnForAdd(final DoctorDaoResult daoResult) {
		Mockito.when(dao.add(doctor)).thenReturn(daoResult);
	}

	/**
	 * Ekleme sonucunu ve hata nesnelerinden DoctorDaoResult nesnesi üretir.
	 * 
	 * @param success
	 *            Başarı durumu
	 * @param error
	 *            Hata
	 * @return DoctorDaoResult nesnesi
	 */
	private DoctorDaoResult createDaoResult(final boolean success,
	        final DoctorManagerError error) {
		final DoctorDaoResult daoResult = new DoctorDaoResult();
		daoResult.setSuccess(success);
		daoResult.setError(error);
		return daoResult;
	}

}
