package info.ozkan.vipera.business.login;

import static org.junit.Assert.*;
import info.ozkan.vipera.dao.login.AdministratorLoginDao;
import info.ozkan.vipera.dao.login.AdministratorLoginDaoResult;
import info.ozkan.vipera.entities.Administrator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * AdministratorLoginManagerImpl birim testleri
 * @author Ömer Özkan
 *
 */
public class AdministratorLoginManagerImplTest {
	/**
	 * Kullanıcı adı
	 */
	private String username = "admin";
	/**
	 * Parola
	 */
	private String password = "password";
	/**
	 * Test edilecek olan nesne
	 */
	private AdministratorLoginManagerImpl manager;
	/**
	 * Persistence katmanı nesnesi
	 */
	private AdministratorLoginDao mockDao;

	/**
	 * Test verilerini hazırlar
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		manager = new AdministratorLoginManagerImpl();
		mockDao = Mockito.mock(AdministratorLoginDao.class);
		manager.setLoginDao(mockDao);
	}
	/**
	 * Yönetici kullanıcı adı ve parolasını
	 * girip login butonuna tıklar
	 * kullanıcı adı geçersizdir.
	 * @throws Exception
	 */
	@Test
	public void loginInvalidUsername() throws Exception {
		AdministratorLoginDaoResult daoResult =
				new AdministratorLoginDaoResult();
		daoResult.setStatus(AdministratorLoginStatus.INVALID_USERNAME);
		setMockReturn(daoResult);
		assertResult(AdministratorLoginStatus.INVALID_USERNAME);
		verify();
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını
	 * girip login butonuna tıklar
	 * parolası geçersizdir.
	 * @throws Exception
	 */
	@Test
	public void loginInvalidPassword() throws Exception {
		AdministratorLoginDaoResult daoResult =
				new AdministratorLoginDaoResult();
		daoResult.setStatus(AdministratorLoginStatus.INVALID_PASSWORD);
		setMockReturn(daoResult);
		assertResult(AdministratorLoginStatus.INVALID_PASSWORD);
		verify();
	}
	/**
	 * Yönetici kullanıcı adı ve parolasını
	 * girip login butonuna tıklar
	 * bilgiler geçerlidir ve login işlemi başarılıdır.
	 * @throws Exception
	 */
	@Test
	public void loginSuccessfull() throws Exception {
		//TODO: sha1 ile şifrelenmeli
		AdministratorLoginDaoResult daoResult =
				new AdministratorLoginDaoResult();
		Administrator admin = new Administrator();
		admin.setUsername(username);
		admin.setPassword(password);
		daoResult.setStatus(AdministratorLoginStatus.SUCCESS);
		daoResult.setAdministrator(admin);

		setMockReturn(daoResult);
		AdministratorLoginResult result =
				assertResult(AdministratorLoginStatus.SUCCESS);
		assertEquals(admin.getUsername(),
				result.getAdministrator().getUsername());
		verify();
	}
	/**
	 * Metodların çağrılmasını onaylar
	 */
	private void verify() {
		Mockito.verify(mockDao).findUser(username, password);
	}
	/**
	 * İstenilen sonucu test eder ve sonucu
	 * tekrar geri dönderir
	 * @param expected
	 * @return
	 */
	private AdministratorLoginResult assertResult(AdministratorLoginStatus expected) {
		AdministratorLoginResult result =
				manager.login(username, password);
		assertEquals(expected, result.getStatus());
		return result;
	}
	/**
	 * Mock nesnenin döndermesi gereken değer
	 * @param daoResult
	 */
	private void setMockReturn(AdministratorLoginDaoResult daoResult) {
		Mockito.when(mockDao.findUser(username, password)).thenReturn(daoResult);
	}
}
