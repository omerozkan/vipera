package info.ozkan.vipera.selenium.doctor;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * "refs #36 Yönetici sisteme hekim ekler". Onay-kabul testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorAddAcceptanceTest {

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
        login();
        selenium.open("/vipera/yonetim/doctor/add.html");
        selenium.waitForPageToLoad("20000");
    }

    /**
     * Bütün alanları boş girip kaydet butonuna basar. Zorunlu yerler hata
     * olarak gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void saveWithEmptyFields() throws Exception {
        selenium.click("id=doctor-add:save");
        waitForErrorMessage();
        final String bodyText = selenium.getBodyText();
        assertThat(bodyText,
                containsString("TC Kimlik Numarası boş bırakılamaz!"));
        assertThat(bodyText, containsString("Hekim adı boş bırakılamaz!"));
        assertThat(bodyText, containsString("Hekim adı boş bırakılamaz!"));
        assertThat(bodyText, containsString("Hekim soyadı boş bırakılamaz!"));
        assertThat(bodyText, containsString("Parola alanı boş bırakılamaz!"));
    }

    /**
     * TCKN bilgisi hatalı girilir. İlgili hata mesajı gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void saveWithInvalidTCKN() throws Exception {
        selenium.type("id=doctor-add:name", "name");
        selenium.type("id=doctor-add:surname", "surname");
        selenium.type("id=doctor-add:password", "password");
        selenium.type("id=doctor-add:password2", "password");
        selenium.type("id=doctor-add:tckn", "111");
        selenium.click("css=body");
        selenium.click("id=doctor-add:save");
        waitForErrorMessage();
        assertThat(
                selenium.getBodyText(),
                containsString("TC Kimlik Numarası 11 haneli ve sayılardan oluşmalıdır!"));
    }

    /**
     * TCKN girilir fakat parola ve parola tekrar bilgisi eşleşmez. Parola
     * alanları hatalı olarak gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void saveWithDifferentPasswords() throws Exception {
        selenium.type("id=doctor-add:name", "name");
        selenium.type("id=doctor-add:surname", "surname");
        selenium.type("id=doctor-add:email", "");
        selenium.click("id=doctor-add:password");
        selenium.type("id=doctor-add:password", "blabla");
        selenium.type("id=doctor-add:password2", "blablabla");
        selenium.click("id=doctor-add:tckn");
        selenium.typeKeys("id=doctor-add:tckn", "23456789111");
        selenium.click("id=doctor-add:save");
        waitForErrorMessage();
        assertThat(selenium.getBodyText(),
                containsString("Girdiğiniz parolalar birbiri ile uyuşmuyor!"));
    }

    /**
     * Zorunlu alanlarla birlikte eposta adresi yanlış formatta girilir. Eposta
     * adresinizi yanlış girdiniz hatası gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void saveWithInvalidEmail() throws Exception {
        selenium.type("id=doctor-add:name", "name");
        selenium.type("id=doctor-add:surname", "surname");
        selenium.type("id=doctor-add:email", "invalidemail");
        selenium.click("id=doctor-add:password");
        selenium.type("id=doctor-add:password", "password");
        selenium.type("id=doctor-add:password2", "password");
        selenium.click("id=doctor-add:tckn");
        selenium.typeKeys("id=doctor-add:tckn", "23456789111");
        selenium.click("id=doctor-add:save");
        waitForErrorMessage();
        assertThat(selenium.getBodyText(),
                containsString("Girdiğiniz eposta adresi geçerli değil!"));
    }

    /**
     * TCKN ile kayıtlı sistemde bir hekim bulunmaktadır. Aynı TCKN ile iki ayrı
     * hekim sisteme kaydedilemez. İlgili hata gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void saveWithNonUniqueTCKN() throws Exception {
        final Doctor doctor = DoctorTestData.getTestData(DoctorTestData.HOUSE);
        selenium.type("id=doctor-add:name", "New");
        selenium.type("id=doctor-add:surname", "Doctor");
        selenium.type("id=doctor-add:email", "new@doctor.com");
        selenium.type("id=doctor-add:password", "password");
        selenium.type("id=doctor-add:password2", "password");
        selenium.click("id=doctor-add:tckn");
        selenium.typeKeys("id=doctor-add:tckn", doctor.getTckn().toString());
        selenium.click("id=doctor-add:save");
        waitForErrorMessage();
        assertThat(
                selenium.getBodyText(),
                containsString("Girdiğiniz TC kimlik numarası ile kayıtlı bir başka hekim bulunmaktadır!"));
    }

    /**
     * Yönetici, hekim bilgilerini hatasız ve eksiksiz girer. Hekim veritabanına
     * kaydedilir.
     * 
     * @throws Exception
     */
    @Test
    public void saveDoctorSuccessfull() throws Exception {
        selenium.type("id=doctor-add:name", "New");
        selenium.type("id=doctor-add:surname", "Doctor");
        selenium.type("id=doctor-add:email", "new@doctor.com");
        selenium.type("id=doctor-add:password", "password");
        selenium.type("id=doctor-add:password2", "password");
        selenium.click("//div[@id='doctor-add:title']/div[3]");
        selenium.click("//div[@id='doctor-add:title_panel']/div/ul/li[2]");
        selenium.type("id=doctor-add:province", "Testing");
        selenium.type("id=doctor-add:diploma", "None");
        selenium.type("id=doctor-add:webpage", "http://vipera.ozkan.info");
        selenium.type("id=doctor-add:phone", "+905555555");
        selenium.type("id=doctor-add:mobile", "+905557777777");
        selenium.type("id=doctor-add:phone", "+905555555555");
        selenium.click("id=doctor-add:tckn");
        selenium.typeKeys("id=doctor-add:tckn", "12345678903");
        selenium.click("id=doctor-add:save");
        waitForInfoMessage();
        assertThat(selenium.getBodyText(),
                containsString("12345678903 - New Doctor kaydedildi!"));
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

    private void login() {
        selenium.open("/vipera/yonetim/login.html");
        selenium.type("id=loginForm:username", "admin");
        selenium.type("id=loginForm:password", "password");
        selenium.click("id=loginForm:loginButton");
    }

    private void waitForErrorMessage() {
        selenium.waitForCondition(
                "selenium.browserbot.getCurrentWindow().jQuery('.ui-messages-error') !== undefined;",
                "30000");
        selenium.waitForCondition(
                "selenium.browserbot.getCurrentWindow().jQuery('.ui-messages-error').text() !== '';",
                "30000");
    }

    private void waitForInfoMessage() {
        selenium.waitForCondition(
                "selenium.browserbot.getCurrentWindow().jQuery('.ui-messages-info-summary') !== undefined;",
                "30000");
        selenium.waitForCondition(
                "selenium.browserbot.getCurrentWindow().jQuery('.ui-messages-info-summary').text() !== '';",
                "30000");

    }
}
