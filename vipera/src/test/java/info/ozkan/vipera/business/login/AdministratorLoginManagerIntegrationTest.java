package info.ozkan.vipera.business.login;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import info.ozkan.vipera.test.IntegrationTest;

/**
 * AdministratorLoginManager entegrasyon testi
 * @author Ömer Özkan
 *
 */
public class AdministratorLoginManagerIntegrationTest extends IntegrationTest{
	/**
	 * Business katmanı nesnesi
	 */
	@Autowired
	private AdministratorLoginManager manager;

	/**
	 * Bilgibankasında kullanıcı adı admin olan bir
	 * yönetici vardır
	 * Kullanıcı adı ve parola girildiğinde doğru
	 * kullanıcıyı Dao katmanından çeker
	 * @throws Exception
	 */
	@Test
	public void testUsername() throws Exception {
		String username = "admin";
		String password = "password";
		AdministratorLoginResult result =
				manager.login(username, password);
		assertEquals(AdministratorLoginStatus.SUCCESS,
				result.getStatus());
		assertEquals(username, result.getAdministrator().getUsername());
	}
}
