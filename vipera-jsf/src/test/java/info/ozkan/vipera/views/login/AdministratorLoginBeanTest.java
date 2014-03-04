package info.ozkan.vipera.views.login;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import info.ozkan.vipera.business.login.AdministratorLoginManager;
import info.ozkan.vipera.business.login.AdministratorLoginResult;
import info.ozkan.vipera.business.login.AdministratorLoginStatus;
import info.ozkan.vipera.entities.Administrator;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * AdministratorLoginBean birim test sınıfı
 * issueNo: 35
 * @author Ömer Özkan
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class AdministratorLoginBeanTest {
	/**
	 * FacesContext mock nesnesi
	 */
	private FacesContext context;
	/**
	 * Kullanıcı adı
	 */
	private String username = "admin";
	/**
	 * Testler için kullanılan boş string
	 */
	private String empty = "";
	/**
	 * parola
	 */
	private String password = "password";
	/**
	 * Test edilen sınıf nesnesi
	 */
	private AdministratorLoginBean loginBean;
	/**
	 * Business nesnesi
	 */
	private AdministratorLoginManager manager;

	/**
	 * Testler için gerekli verileri hazırlar
	 */
	@Before
	public void setUp() throws Exception {
		context = Mockito.mock(FacesContext.class);
		PowerMockito.mockStatic(FacesContext.class);
		PowerMockito.doReturn(context).when(FacesContext.class,
				"getCurrentInstance");
		loginBean = new AdministratorLoginBean();
		manager = Mockito.mock(AdministratorLoginManager.class);
		loginBean.setLoginManager(manager);
	}

	/**
	 * Yönetici kullanıcı adını boş bırakarak
	 * login butonuna basar. Login işlemi başarısızdır.
	 * Lütfen Kullanıcı adınızı ve parolanızı giriniz
	 * hata mesajı gönderilir
	 * @throws Exception
	 */
	@Test
	public void loginEmptyUsername() throws Exception {
		loginBean.setUsername(empty);
		loginBean.setPassword(password);
		loginBean.login(null);
		assertAccessDenied();
		assertEmptyField();
	}

	/**
	 * Yönetici parolasını boş bırakarak
	 * login butonuna basar. Login işlemi başarısızdır.
	 * Lütfen Kullanıcı adınızı ve parolanızı giriniz
	 * hata mesajı gönderilir.
	 * @throws Exception
	 */
	@Test
	public void loginEmptyPassword() throws Exception {
		loginBean.setUsername(username);
		loginBean.setPassword("");
		loginBean.login(null);
		assertAccessDenied();
		assertEmptyField();
	}

	/**
	 * Yönetici kullanıcı adını ve parolasını girerek login
	 * butonuna tıklar. Kullanıcı adı sistemde bulunmaz
	 * "Geçersiz giriş, lütfen kullanıcı adınızı ve parolanızı
	 * tekrar giriniz!" hatası gösterilir.
	 * @throws Exception
	 */
	@Test
	public void loginUsernameInvalid() throws Exception {
		generateLoginResult(AdministratorLoginStatus.INVALID_USERNAME);
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		loginBean.login(null);
		assertAccessDenied();
		verifyMessage(AdministratorLoginBean.INVALID_LOGIN);
		verifyLoginManager();
	}
	/**
	 * Yönetici kullanıcı adını ve parolasını girerek login
	 * butonuna tıklar. Kullanıcı adı sistemde kayıtlıdır
	 * fakat parola yanlıştır
	 * "Geçersiz giriş, lütfen kullanıcı adınızı ve parolanızı
	 * tekrar giriniz!" hatası gösterilir.
	 * @throws Exception
	 */
	@Test
	public void loginPasswordInvalid() throws Exception {
		generateLoginResult(AdministratorLoginStatus.INVALID_PASSWORD);
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		loginBean.login(null);
		verifyMessage(AdministratorLoginBean.INVALID_LOGIN);
		verifyLoginManager();
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını girer.
	 * Bilgiler geçerlidir.
	 * Yönetici yönetim paneline yönlendirilir.
	 * @throws Exception
	 */
	@Test
	public void loginSuccessfull() throws Exception {
		Administrator admin = new Administrator();
		admin.setPassword(username);
		admin.setUsername(password);

		AdministratorLoginResult result =
				new AdministratorLoginResult();
		result.setStatus(AdministratorLoginStatus.SUCCESS);
		result.setAdministrator(admin);

		returnLoginResult(result);

		Map<String, Object> sessionMap = new HashMap<String, Object>();
		ExternalContext externalContext =
				Mockito.mock(ExternalContext.class);
		Mockito.when(context.getExternalContext())
		 .thenReturn(externalContext);
		Mockito.when(externalContext.getSessionMap())
			.thenReturn(sessionMap);

		loginBean.setUsername(username);
		loginBean.setPassword(password);
		loginBean.login(null);

		assertEquals(loginBean.login(),
				AdministratorLoginBean.INDEX_PAGE);
		assertNotEquals(sessionMap.size(), 0);
		assertSame(admin, sessionMap.get("administrator"));
		verifyLoginManager();
		Mockito.verify(context).getExternalContext();
		Mockito.verify(externalContext).getSessionMap();
	}

	/**
	 * LoginManager nesnesini LoginResult nesnesini
	 * dönderecek şekilde ayarlar
	 * @param result
	 */
	private void returnLoginResult(AdministratorLoginResult result) {
		Mockito.when(manager.login(username, password))
			.thenReturn(result);
	}
	/**
	 * {@link AdministratorLoginBeanTest#manager}
	 * nesnesinin login metodunu onaylar
	 */
	private void verifyLoginManager() {
		Mockito.verify(manager).login(username, password);
	}

	/**
	 * İstenilen StatusCode'u içeren LoginResult nesnesi oluşturur
	 * @param statusCode
	 */
	private void generateLoginResult(AdministratorLoginStatus statusCode) {
		AdministratorLoginResult loginResult = new AdministratorLoginResult();
		loginResult.setStatus(statusCode);
		returnLoginResult(loginResult);
	}

	/**
	 * FacesMessage nesnesinin istenilen mesaj
	 * olup olmadığını kontrol eder
	 * @param message
	 */
	private void verifyMessage(FacesMessage message) {
		Mockito.verify(context).addMessage(null, message);
	}

	/**
	 * Kullanıcı ve parola alanlarının boş girilmesi
	 * durumunda test edilecek olan mesaj
	 */
	private void assertEmptyField() {
		verifyMessage(AdministratorLoginBean.EMPTY_FIELD);
	}

	/**
	 * Login işlemi başarısız olduğu durumlarda
	 * tekrar login sayfasına yönlendirilmesini
	 * test eder
	 */
	private void assertAccessDenied() {
		String returnedPage = loginBean.login();
		assertEquals(AdministratorLoginBean.LOGIN_PAGE, returnedPage);
	}
}
