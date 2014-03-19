package info.ozkan.vipera.dao.login;

import static org.junit.Assert.assertEquals;
import info.ozkan.vipera.business.login.AdministratorLoginStatus;
import info.ozkan.vipera.test.IntegrationTest;

import javax.inject.Inject;

import org.junit.Test;

/**
 * AdministratorLoginDao entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorLoginDaoIntegrationTest extends IntegrationTest {
	/**
	 * Persistence nesnesi
	 */
	@Inject
	private AdministratorLoginDao dao;

	/**
	 * Bilgibankasında admin kullanıcı adına sahip bir yönetici bulunmaktadır
	 * Dao sınıfı aracılığı ile gereken nesneye erişilir
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUsername() throws Exception {
		final String username = "admin";
		final String password = "password";
		final AdministratorLoginDaoResult result = dao.findUser(username,
		        password);
		assertEquals(AdministratorLoginStatus.SUCCESS, result.getStatus());
		assertEquals(username, result.getAdministrator().getUsername());
	}
}
