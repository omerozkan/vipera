package info.ozkan.vipera.dao.login;

import static org.junit.Assert.*;
import info.ozkan.vipera.business.login.AdministratorLoginStatus;
import info.ozkan.vipera.test.IntegrationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * AdministratorLoginDao entegrasyon testi
 * @author Ömer Özkan
 *
 */
public class AdministratorLoginDaoIntegrationTest extends IntegrationTest{
	/**
	 * Persistence nesnesi
	 */
	@Autowired
	private AdministratorLoginDao dao;
	/**
	 * Bilgibankasında admin kullanıcı adına sahip
	 * bir yönetici bulunmaktadır
	 * Dao sınıfı aracılığı ile gereken nesneye erişilir
	 * @throws Exception
	 */
	@Test
	public void testUsername() throws Exception {
		String username = "admin";
		String password = "password";
		AdministratorLoginDaoResult result =
				dao.findUser(username, password);
		assertEquals(AdministratorLoginStatus.SUCCESS,
				result.getStatus());
		assertEquals(username,
				result.getAdministrator().getUsername());
	}
}
