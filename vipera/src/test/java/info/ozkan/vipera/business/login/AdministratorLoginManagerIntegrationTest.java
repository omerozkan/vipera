package info.ozkan.vipera.business.login;

import static org.junit.Assert.assertTrue;
import info.ozkan.vipera.test.IntegrationTest;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * AdministratorLoginManager entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorLoginManagerIntegrationTest extends IntegrationTest {
	/**
	 * Business katmanı nesnesi
	 */
	@Inject
	private AdministratorLoginManager manager;

	/**
	 * Bilgibankasında kullanıcı adı admin olan bir yönetici vardır Kullanıcı
	 * adı ve parola girildiğinde doğru kullanıcıyı Dao katmanından çeker
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUsername() throws Exception {
		final String username = "admin";
		final String password = "password";
		final Authentication authentication = new UsernamePasswordAuthenticationToken(
		        username, password);
		final Authentication result = manager.authenticate(authentication);
		final GrantedAuthority authority = result.getAuthorities().iterator()
		        .next();
		assertTrue(authority.getAuthority().equals(
		        AdministratorLoginManager.ROLE_ADMIN));
	}
}
