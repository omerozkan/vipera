package info.ozkan.vipera.selenium.login;

import static org.junit.Assert.assertEquals;
import info.ozkan.vipera.views.login.AdministratorLoginBean;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * refs #35 Yönetici kullanıcı adı ve parolası ile sisteme giriş yapar kullanıcı
 * hikayesinin onay kabul testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorLoginAcceptanceTest {
    /**
     * Selenium nesnesi
     */
    private Selenium selenium;
    /**
     * Uygulamanın temel adresi
     */
    private final String baseURL = "http://localhost:8080/vipera/yonetim";
    /**
     * Host adı
     */
    private final String hostname = "localhost";
    /**
     * Selenium RC sunucu portu
     */
    private final int serverPort = 4444;

    /**
     * Selenium nesnesini ilkendirir ve login sayfasını açar
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        selenium = new DefaultSelenium(hostname, serverPort, "*chrome", baseURL);
        selenium.start();
        selenium.open("/vipera/yonetim/login.html");
    }

    /**
     * Yönetici kullanıcı adını ve parolasını girerek login butonuna tıklar.
     * Kullanıcı adı geçerlidir fakat parola geçersizdir.
     * "Geçersiz giriş, lütfen kullanıcı adınızı ve parolanızı tekrar giriniz!"
     * yarısı görüntülenir.
     */
    @Test
    public void loginWithWrongPassword() {
        loginAndWaitForGrowl("admin", "blabla");
        assertGrowlMessage(AdministratorLoginBean.INVALID_LOGIN_MESSAGE);
    }

    /**
     * Yönetici kullanıcı adını ve parolasını girerek login butonuna tıklar.
     * Kullanıcı adı sistemde bulunmaz
     * "Geçersiz giriş, lütfen kullanıcı adınızı ve parolanızı tekrar giriniz!"
     * yarısı görüntülenir.
     */
    @Test
    public void loginWithWrongUsername() {
        loginAndWaitForGrowl("blabla", "blabla");
        assertGrowlMessage(AdministratorLoginBean.INVALID_LOGIN_MESSAGE);
    }

    /**
     * Yönetici kullanıcı adını boş bırakarak login butonuna basar. Login işlemi
     * başarısızdır. Lütfen Kullanıcı adınızı ve parolanızı giriniz hata mesajı
     * gönderilir.
     */
    @Test
    public void loginWithEmptyUsername() {
        loginAndWaitForGrowl("admin", "");
        assertGrowlMessage(AdministratorLoginBean.EMPTY_FIELD_MESSAGE);
    }

    /**
     * Yönetici parolasını boş bırakarak login butonuna basar. Login işlemi
     * başarısızdır. Lütfen Kullanıcı adınızı ve parolanızı giriniz hata mesajı
     * gönderilir.
     */
    @Test
    public void loginWithEmptyPassword() {
        loginAndWaitForGrowl("", "password");
        assertGrowlMessage(AdministratorLoginBean.EMPTY_FIELD_MESSAGE);
    }

    /**
     * Yönetici kullanıcı adı ve parolasını girer. Bilgiler geçerlidir. Yönetici
     * yönetim paneline yönlendirilir.
     * 
     * @throws Exception
     */
    @Test
    public void loginSuccessfull() throws Exception {
        selenium.type("id=loginForm:username", "admin");
        selenium.type("id=loginForm:password", "password");
        selenium.click("id=loginForm:loginButton");
        selenium.waitForPageToLoad("30000");
        final String location = selenium.getLocation();
        final String expected = getFullUrl("index.html");
        assertEquals(expected, location);
    }

    /**
     * Oluşan growl mesajını test eder
     * 
     * @param msg
     */
    private void assertGrowlMessage(final String msg) {
        Assert.assertThat(selenium.getBodyText(),
                CoreMatchers.containsString(msg));
    }

    /**
     * Kullanıcı adı ve parola ile login işlemi yapar
     */
    private void loginAndWaitForGrowl(final String username,
            final String password) {
        selenium.type("id=loginForm:username", username);
        selenium.type("id=loginForm:password", password);
        selenium.click("id=loginForm:loginButton");
        selenium.waitForCondition(
                "selenium.browserbot.getCurrentWindow().jQuery('.ui-growl-message') !== undefined;",
                "30000");
        selenium.waitForCondition(
                "selenium.browserbot.getCurrentWindow().jQuery('.ui-growl-message').text() !== '';",
                "30000");
    }

    /**
     * Relative url'e ait tam adresi dönderir
     * 
     * @param relative
     *            URL
     * @return
     */
    private String getFullUrl(final String relative) {
        return baseURL + "/" + relative;
    }

    /**
     * Selenium u durdurur
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        selenium.stop();
    }
}
