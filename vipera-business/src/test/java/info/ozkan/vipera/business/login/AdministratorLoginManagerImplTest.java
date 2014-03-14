package info.ozkan.vipera.business.login;

import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.dao.login.AdministratorLoginDao;
import info.ozkan.vipera.dao.login.AdministratorLoginDaoResult;
import info.ozkan.vipera.entities.Administrator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * AdministratorLoginManagerImpl birim testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorLoginManagerImplTest {
	/**
	 * Kullanıcı adı
	 */
	private final String username = "admin";
	/**
	 * Parola
	 */
	private final String password = "password";
	/**
	 * Test edilecek olan nesne
	 */
	private AdministratorLoginManager manager;
	/**
	 * Persistence katmanı nesnesi
	 */
	private AdministratorLoginDao mockDao;
	private Authentication authentication;

	/**
	 * Test verilerini hazırlar
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		manager = new AdministratorLoginManager();
		mockDao = Mockito.mock(AdministratorLoginDao.class);
		manager.setLoginDao(mockDao);
		authentication = Mockito.mock(Authentication.class);
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını girip login butonuna tıklar
	 * kullanıcı adı geçersizdir.
	 * 
	 * @throws Exception
	 */
	@Test(expected = UsernameNotFoundException.class)
	public void loginInvalidUsername() throws Exception {
		final AdministratorLoginDaoResult daoResult = createDaoResult(AdministratorLoginStatus.INVALID_USERNAME);
		setMockReturn(daoResult);
		mockAuthentication();
		manager.authenticate(authentication);
		verify();
	}

	private AdministratorLoginDaoResult createDaoResult(
	        final AdministratorLoginStatus status) {
		final AdministratorLoginDaoResult daoResult = new AdministratorLoginDaoResult();
		daoResult.setStatus(status);
		return daoResult;
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını girip login butonuna tıklar parolası
	 * geçersizdir.
	 * 
	 * @throws Exception
	 */
	@Test(expected = BadCredentialsException.class)
	public void loginInvalidPassword() throws Exception {
		final AdministratorLoginDaoResult daoResult = createDaoResult(AdministratorLoginStatus.INVALID_PASSWORD);
		setMockReturn(daoResult);
		mockAuthentication();
		manager.authenticate(authentication);
		verify();
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını girip login butonuna tıklar bilgiler
	 * geçerlidir ve login işlemi başarılıdır.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginSuccessfull() throws Exception {
		final AdministratorLoginDaoResult daoResult = createDaoResult(AdministratorLoginStatus.SUCCESS);
		final Administrator admin = createAdministrator();
		daoResult.setAdministrator(admin);
		setMockReturn(daoResult);
		mockAuthentication();
		final Authentication result = manager.authenticate(authentication);
		final GrantedAuthority authority = result.getAuthorities().iterator()
		        .next();
		assertTrue(authority.getAuthority().equals(
		        AdministratorLoginManager.ROLE_ADMIN));
	}

	private Administrator createAdministrator() {
		final Administrator admin = new Administrator();
		admin.setUsername(username);
		admin.setPassword(password);
		return admin;
	}

	/**
	 * Metodların çağrılmasını onaylar
	 */
	private void verify() {
		Mockito.verify(mockDao).findUser(username, password);
		Mockito.verify(authentication).getPrincipal();
		Mockito.verify(authentication).getCredentials();
	}

	/**
	 * Mock nesnenin döndermesi gereken değer
	 * 
	 * @param daoResult
	 */
	private void setMockReturn(final AdministratorLoginDaoResult daoResult) {
		Mockito.when(mockDao.findUser(username, password))
		        .thenReturn(daoResult);
	}

	/**
	 * Authentication nesnesinin metodlarını mocklar
	 */
	private void mockAuthentication() {
		Mockito.when(authentication.getPrincipal()).thenReturn(username);
		Mockito.when(authentication.getCredentials()).thenReturn(password);
	}
}
