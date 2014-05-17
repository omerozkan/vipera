package info.ozkan.vipera.views.login;

import static org.junit.Assert.assertEquals;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * AdministratorLoginBean birim test sınıfı issueNo: 35
 * 
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
	private final String username = "admin";
	/**
	 * Testler için kullanılan boş string
	 */
	private final String empty = "";
	/**
	 * parola
	 */
	private final String password = "password";
	/**
	 * Test edilen sınıf nesnesi
	 */
	private AdministratorLoginBean loginBean;
	/**
	 * Business nesnesi
	 */
	private AuthenticationManager manager;
	/**
	 * Spring security Authentication nesnesi
	 */
	private Authentication request;

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
		manager = Mockito.mock(AuthenticationManager.class);
		loginBean.setAdminAuthManager(manager);
		request = new UsernamePasswordAuthenticationToken(username, password);
	}

	/**
	 * Yönetici kullanıcı adını boş bırakarak login butonuna basar. Login işlemi
	 * başarısızdır. Lütfen Kullanıcı adınızı ve parolanızı giriniz hata mesajı
	 * gönderilir
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginEmptyUsername() throws Exception {
		setCredentials(empty, password);
		loginBean.login(null);
		assertAccessDenied();
		assertEmptyField();
	}

	/**
	 * Yönetici parolasını boş bırakarak login butonuna basar. Login işlemi
	 * başarısızdır. Lütfen Kullanıcı adınızı ve parolanızı giriniz hata mesajı
	 * gönderilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginEmptyPassword() throws Exception {
		setCredentials(username, empty);
		loginBean.login(null);
		assertAccessDenied();
		assertEmptyField();
	}

	/**
	 * Yönetici kullanıcı adını ve parolasını girerek login butonuna tıklar.
	 * Kullanıcı adı sistemde bulunmaz "Geçersiz giriş, lütfen kullanıcı adınızı
	 * ve parolanızı tekrar giriniz!" hatası gösterilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginUsernameInvalid() throws Exception {
		// generateLoginResult(AdministratorLoginStatus.INVALID_USERNAME);
		final Authentication request = new UsernamePasswordAuthenticationToken(
		        username, password);
		Mockito.when(manager.authenticate(request)).thenThrow(
		        new UsernameNotFoundException(""));
		setCredentials(username, password);
		loginBean.login(null);
		assertAccessDenied();
		verifyMessage(AdministratorLoginBean.INVALID_LOGIN);
		verifyLoginManager();
	}

	/**
	 * Yönetici kullanıcı adını ve parolasını girerek login butonuna tıklar.
	 * Kullanıcı adı sistemde kayıtlıdır fakat parola yanlıştır "Geçersiz giriş,
	 * lütfen kullanıcı adınızı ve parolanızı tekrar giriniz!" hatası
	 * gösterilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginPasswordInvalid() throws Exception {
		final Authentication request = new UsernamePasswordAuthenticationToken(
		        username, password);
		Mockito.when(manager.authenticate(request)).thenThrow(
		        new BadCredentialsException(""));
		setCredentials(username, password);
		loginBean.login(null);
		verifyMessage(AdministratorLoginBean.INVALID_LOGIN);
		verifyLoginManager();
	}

	/**
	 * Yönetici kullanıcı adı ve parolasını girer. Bilgiler geçerlidir. Yönetici
	 * yönetim paneline yönlendirilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginSuccessfull() throws Exception {

		Mockito.when(manager.authenticate(request)).thenReturn(request);

		final SecurityContext securityContext = Mockito
		        .mock(SecurityContext.class);
		SecurityContextHolder.setContext(securityContext);
		setCredentials(username, password);
		loginBean.login(null);
		assertEquals(loginBean.login(), AdministratorLoginBean.INDEX_PAGE);
		Mockito.verify(securityContext).setAuthentication(request);
		verifyLoginManager();
	}

	/**
	 * Manager nesnesinin authenticate metodunu çağırdığını doğrular
	 */
	private void verifyLoginManager() {
		Mockito.verify(manager).authenticate(request);
	}

	/**
	 * FacesMessage nesnesinin istenilen mesaj olup olmadığını kontrol eder
	 * 
	 * @param message
	 *            Message
	 */
	private void verifyMessage(final FacesMessage message) {
		Mockito.verify(context).addMessage(null, message);
	}

	/**
	 * Kullanıcı ve parola alanlarının boş girilmesi durumunda test edilecek
	 * olan mesaj
	 */
	private void assertEmptyField() {
		verifyMessage(AdministratorLoginBean.EMPTY_FIELD);
	}

	/**
	 * Login işlemi başarısız olduğu durumlarda tekrar login sayfasına
	 * yönlendirilmesini test eder
	 */
	private void assertAccessDenied() {
		final String returnedPage = loginBean.login();
		assertEquals(AdministratorLoginBean.LOGIN_PAGE, returnedPage);
	}

	/**
	 * Kullanıcı adı ve parolayı bean nesnesine enjekte eder
	 * 
	 * @param username
	 *            Kullanıcı adı
	 * @param password
	 *            Parola
	 */
	private void setCredentials(final String username, final String password) {
		loginBean.setUsername(username);
		loginBean.setPassword(password);
	}
}
