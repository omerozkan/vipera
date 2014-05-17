package info.ozkan.vipera.selenium.login;

import static org.junit.Assert.assertEquals;
import info.ozkan.vipera.selenium.AbstractAcceptanceTest;
import info.ozkan.vipera.views.login.AdministratorLoginBean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * refs #35 Yönetici kullanıcı adı ve parolası ile sisteme giriş yapar kullanıcı
 * hikayesinin onay kabul testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorLoginAcceptanceTest extends AbstractAcceptanceTest {

    /**
     * Selenium nesnesini ilkendirir ve login sayfasını açar
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
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
        assertContainsBodyText(AdministratorLoginBean.INVALID_LOGIN_MESSAGE);
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
        assertContainsBodyText(AdministratorLoginBean.INVALID_LOGIN_MESSAGE);
    }

    /**
     * Yönetici kullanıcı adını boş bırakarak login butonuna basar. Login işlemi
     * başarısızdır. Lütfen Kullanıcı adınızı ve parolanızı giriniz hata mesajı
     * gönderilir.
     */
    @Test
    public void loginWithEmptyUsername() {
        loginAndWaitForGrowl("admin", "");
        assertContainsBodyText(AdministratorLoginBean.EMPTY_FIELD_MESSAGE);
    }

    /**
     * Yönetici parolasını boş bırakarak login butonuna basar. Login işlemi
     * başarısızdır. Lütfen Kullanıcı adınızı ve parolanızı giriniz hata mesajı
     * gönderilir.
     */
    @Test
    public void loginWithEmptyPassword() {
        loginAndWaitForGrowl("", "password");
        assertContainsBodyText(AdministratorLoginBean.EMPTY_FIELD_MESSAGE);
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
        final String expected = getFullAdminPanelUrl("index.html");
        assertEquals(expected, location);
    }

    /**
     * Kullanıcı adı ve parola ile login işlemi yapar
     */
    private void loginAndWaitForGrowl(final String username,
            final String password) {
        selenium.type("id=loginForm:username", username);
        selenium.type("id=loginForm:password", password);
        selenium.click("id=loginForm:loginButton");
        waitForElement(".ui-growl-message");
    }

    /**
     * Selenium u durdurur
     * 
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        stopSelenium();
    }
}
