package info.ozkan.vipera.views.login;

import info.ozkan.vipera.business.login.AdministratorLoginManager;
import info.ozkan.vipera.business.login.AdministratorLoginResult;
import info.ozkan.vipera.business.login.AdministratorLoginStatus;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("adminLogin")
@Scope("request")
public class AdministratorLoginBean implements Serializable {
	/**
	 * Logging
	 */
	private static Logger LOGGER = LoggerFactory
			.getLogger(AdministratorLoginBean.class);
	/**
	 * Login sayfası
	 */
	protected static final String LOGIN_PAGE = "login";
	/**
	 * Panel ana sayfa
	 */
	protected static final String INDEX_PAGE = "index";
	/**
	 * Mesaj başlığı
	 */
	private static final String MESSAGE_TITLE = "Uyarı";
	/**
	 * Alanların boş olduğu durumda gösterilecek olan hata mesajı
	 */
	private static final String EMPTY_FIELD_MESSAGE =
			"Lütfen kullanıcı adınızı ve parolanızı giriniz!";
	/**
	 * Girilen bilgilerin geçersiz olduğu durumda gösterilecek
	 * olan hata mesajı
	 */
	private static final String INVALID_LOGIN_MESSAGE =
			"Geçersiz kullanıcı adı ve parola girdiniz."
			+ "Lütfen bilgilerinizi kontrol ederek tekrar deneyin!";
	/**
	 * {@link AdministratorLoginBean#INVALID_LOGIN_MESSAGE}
	 * mesajını içeren FacesMessage nesnesi
	 */
	protected static final FacesMessage INVALID_LOGIN =
			new FacesMessage(MESSAGE_TITLE, INVALID_LOGIN_MESSAGE);
	/**
	 * {@link AdministratorLoginBean#EMPTY_FIELD_MESSAGE}
	 * mesajını içeren FacesMessage nesnesi
	 */
	protected static final FacesMessage EMPTY_FIELD =
			new FacesMessage(MESSAGE_TITLE, EMPTY_FIELD_MESSAGE);

	/**
	 * Kullanıcı adı
	 */
	private String username;
	/**
	 * Parola
	 */
	private String password;
	/**
	 * Business katmanı nesnesi
	 */
	@Autowired
	private AdministratorLoginManager loginManager;
	/**
	 * Kullanıcı giriş işlemi başarılı mı?
	 */
	private boolean isSuccess;

	/**
	 * LoginManager setter metod
	 * @param manager
	 */
	public void setLoginManager(AdministratorLoginManager manager) {
		this.loginManager = manager;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Login işlemi sonucunda
	 * login sayfasına veya yönetici anasayfasına
	 * yönlendirilmesini sağlar
	 * @return yeni sayfa
	 */
	public String login() {

		return isSuccess ? INDEX_PAGE : LOGIN_PAGE;
	}

	/**
	 * Girilen bilgileri kontrol eder
	 * gerektiğinde hata mesajı gösterir
	 * @param ae
	 */
	public void login(ActionEvent ae) {
		FacesContext context = FacesContext.getCurrentInstance();
		isSuccess = false;
		if (username.isEmpty() || password.isEmpty()) {
			context.addMessage(null, EMPTY_FIELD);
			return;
		}
		AdministratorLoginResult result = loginManager.login(username, password);
		if(invalidUsername(result) || invalidPassword(result)) {
			context.addMessage(null, INVALID_LOGIN);
		}
		else if(isLoginSuccess(result)) {
			isSuccess = true;
			context.getExternalContext().getSessionMap()
			 .put("administrator", result.getAdministrator());
		}
	}

	/**
	 * Login başarılı mı?
	 * @param result
	 * @return
	 */
	private boolean isLoginSuccess(AdministratorLoginResult result) {
		return result.getStatus().equals(AdministratorLoginStatus.SUCCESS);
	}

	/**
	 * Parola geçersiz mi?
	 * @param result
	 * @return
	 */
	private boolean invalidPassword(AdministratorLoginResult result) {
		return result.getStatus().equals(
				AdministratorLoginStatus.INVALID_PASSWORD);
	}

	/**
	 * Kullanıcı adı geçersiz mi
	 * @param result
	 * @return
	 */
	private boolean invalidUsername(AdministratorLoginResult result) {
		return result.getStatus().equals(
				AdministratorLoginStatus.INVALID_USERNAME);
	}
}
