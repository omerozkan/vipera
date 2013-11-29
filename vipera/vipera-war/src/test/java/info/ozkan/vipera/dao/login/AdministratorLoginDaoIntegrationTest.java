package info.ozkan.vipera.dao.login;

import static org.junit.Assert.*;
import info.ozkan.vipera.business.login.AdministratorLoginStatus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:WEB-INF/applicationContext.xml")
public class AdministratorLoginDaoIntegrationTest {
	/**
	 * Persistence nesnesi
	 */
	@Autowired
	private AdministratorLoginDao dao;
	/**
	 * Bilgibankasında admin kullanıcı adına sahip bir yönetici bulunmaktadır
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
		assertEquals(password,
				result.getAdministrator().getPassword());
	}
}
