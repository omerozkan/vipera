package info.ozkan.vipera.selenium;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * Onay-kabul testleri için kullanılan abstract sınıfı
 * 
 * Onay-kabul testleri için genişletmek yeterlidir. Selenium nesnesi otomatik
 * olarak ilklendirilmektedir.
 * 
 * @author Ömer Özkan
 */
public abstract class AbstractAcceptanceTest {
    private static final int AJAX_TIMEOUT = 500;
    private static final String ADMIN_DEFAULT_PASSWORD = "password";
    private static final String ADMIN_DEFAULT_USER = "admin";
    private static final String ADMIN_LOGIN_PAGE = "login.html";
    // TODO: Sunucu base url gibi bilgiler bir properties dosyasından
    // çekilmelidir.
    /**
     * Admin panelinin url'i
     */
    private static final String ADMINISTRATOR_PANEL_PATH = "yonetim";
    /**
     * Sayfaların yüklenmesi için verilen süre
     */
    protected static final String TIMEOUT_LIMIT = "30000";
    /**
     * başlangıç komutu
     */
    private static final String START_COMMAND = "*chrome";
    /**
     * Host adı
     */
    private final String hostname = "localhost";
    /**
     * Selenium RC sunucu portu
     */
    private final int serverPort = 4444;

    /**
     * Uygulamanın temel adresi
     */
    private final String baseUrl = "http://localhost:8080/vipera";

    /**
     * Selenium nesnesi
     */
    protected Selenium selenium;

    public AbstractAcceptanceTest() {
        initializeSelenium();
    }

    /**
     * Selenium nesnesini ilklendirir ve başlatır
     */
    protected void initializeSelenium() {
        selenium = new DefaultSelenium(hostname, serverPort, START_COMMAND,
                baseUrl);
        selenium.start();
    }

    /**
     * Bağıl adresi tam adrese çevirir
     * 
     * @param relative
     *            Bağıl adres
     * @return tam adres
     */
    protected String getFullUrl(final String relative) {
        return baseUrl + "/" + relative;
    }

    /**
     * Bağıl adresi bir yönetici paneli adresine çevirir
     * 
     * @param relative
     *            Bağıl adres
     * @return tam adres
     */
    protected String getFullAdminPanelUrl(final String relative) {
        return baseUrl + "/" + ADMINISTRATOR_PANEL_PATH + "/" + relative;
    }

    /**
     * Selenium'u durdurur
     */
    protected void stopSelenium() {
        selenium.stop();
    }

    /**
     * Genellikle Ajax işlemlerinin tamamlanması için kullanılır
     * 
     * @param element
     */
    protected void waitForElement(final String element) {
        selenium.waitForCondition(
                String.format(
                        "selenium.browserbot.getCurrentWindow().jQuery('%s') !== undefined;",
                        element), TIMEOUT_LIMIT);
        selenium.waitForCondition(
                String.format(
                        "selenium.browserbot.getCurrentWindow().jQuery('%s').text() !== '';",
                        element), TIMEOUT_LIMIT);
    }

    /**
     * {@link AbstractAcceptanceTest#selenium} nesnesinin getBodyText metodunda
     * metnin içerip içermediğini test eder
     * 
     * @param text
     *            metin
     */
    protected void assertContainsBodyText(final String text) {
        Assert.assertThat(selenium.getBodyText(),
                CoreMatchers.containsString(text));
    }

    /**
     * Primefaces input mask alanları için metin giriş işlemi yapar
     * 
     * @param element
     * @param value
     */
    protected void typeInputMask(final String element, final String value) {
        selenium.click(element);
        selenium.typeKeys(element, value);
    }

    /**
     * Yönetici login işlem gerçekleştirir
     */
    protected void doAdministratorLogin() {
        selenium.open(getFullAdminPanelUrl(ADMIN_LOGIN_PAGE));
        selenium.type("id=loginForm:username", ADMIN_DEFAULT_USER);
        selenium.type("id=loginForm:password", ADMIN_DEFAULT_PASSWORD);
        selenium.click("id=loginForm:loginButton");
    }

    /**
     * HTML elemanlarının yüklenmesini bekler
     * 
     * @throws InterruptedException
     */
    protected void waitForElements() throws InterruptedException {
        synchronized (selenium) {
            selenium.wait(AJAX_TIMEOUT);
        }
    }
}
