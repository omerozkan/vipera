package info.ozkan.vipera.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.contains;
import info.ozkan.vipera.business.login.AdministratorLoginStatus;
import info.ozkan.vipera.dao.login.AdministratorLoginDaoImpl;
import info.ozkan.vipera.dao.login.AdministratorLoginDaoResult;
import info.ozkan.vipera.entities.Administrator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

/**
 * LoginDaoImpl sınıfının birim testleri
 * @author Ömer Özkan
 */
public class AdministratorLoginDaoImplTest {
	/**
	 * Kullanıcı adı
	 */
	private String username = "admin";
	/**
	 * Parola
	 */
	private String password = "password";
	/**
	 * EntityManager nesnesi
	 */
	private EntityManager em;
	/**
	 * Query nesnesi
	 */
	private Query query;
	/**
	 * Test edilen dao nesnesi
	 */
	private AdministratorLoginDaoImpl dao;
	/**
	 * Yönetici sorgusu
	 */
	private String jql = "from Administrator a where a.username = :username";

	/**
	 * Test verilerini hazırlar
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		query = mock(Query.class);
		em = mock(EntityManager.class);
		dao = new AdministratorLoginDaoImpl();
		dao.setEntityManager(em);
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını
	 * girip login butonuna tıklar
	 * kullanıcı adı geçersizdir.
	 * @throws Exception
	 */
	@Test
	public void loginInvalidUsername() throws Exception {
		configureMockObjects(Collections.emptyList());
		AdministratorLoginDaoResult result =
				dao.findUser(username, password);
		assertEquals(AdministratorLoginStatus.INVALID_USERNAME,
				result.getStatus());
		verifyMockObjects();
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını
	 * girip login butonuna tıklar
	 * parolası geçersizdir.
	 * @throws Exception
	 */
	@Test
	public void loginInvalidPassword() throws Exception {
		List<Administrator> list =
				createAdminList(username, "different");
		configureMockObjects(list);
		AdministratorLoginDaoResult result =
				dao.findUser(username, password);
		assertEquals(AdministratorLoginStatus.INVALID_PASSWORD,
				result.getStatus());
		verifyMockObjects();
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını
	 * girip login butonuna tıklar
	 * bilgiler geçerlidir ve login işlemi başarılıdır.
	 * @throws Exception
	 */
	@Test
	public void loginSuccessfull() throws Exception {
		List<Administrator> list = createAdminList(username, password);
		configureMockObjects(list);
		AdministratorLoginDaoResult result =
				dao.findUser(username, password);
		assertEquals(AdministratorLoginStatus.SUCCESS,
				result.getStatus());
		assertNotNull(result.getAdministrator());
		verifyMockObjects();
	}
	/**
	 * Tek bir Administrator nesnesi içeren liste oluşturur
	 * @param username Kullanıcı adı
	 * @param password Parola
	 * @return Liste
	 */
	private List<Administrator> createAdminList(String username,
			String password) {
		List<Administrator> list = new ArrayList<Administrator>();
		Administrator admin = new Administrator();
		admin.setUsername(username);
		admin.setPassword(password);
		list.add(admin);
		return list;
	}

	/**
	 * Mock metodlarının çağrıldığını onaylar
	 */
	private void verifyMockObjects() {
		verify(em).createQuery(jql);
		verify(query).setParameter(
				contains("username"), contains(username));
		verify(query).getResultList();
	}

	/**
	 * Mock nesnelerinin metodlarını tanımlar
	 * @param list
	 */
	private void configureMockObjects(List list) {
		when(em.createQuery(jql)).thenReturn(query);
		when(query.setParameter(contains("username"),
				contains(username))).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
	}
}
