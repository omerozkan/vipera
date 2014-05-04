package info.ozkan.vipera.selenium.doctor;

import static org.hamcrest.CoreMatchers.containsString;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.selenium.AbstractAcceptanceTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * "refs #36 Yönetici sisteme hekim ekler". Onay-kabul testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorAddAcceptanceTest extends AbstractAcceptanceTest {
    /**
     * Parola alanı boş hata mesajı
     */
    private static final String ERROR_EMPTY_PASSWORD = "Parola alanı boş bırakılamaz!";
    /**
     * Soyadı alanı boş hata mesajı
     */
    private static final String ERROR_EMTPY_SURNAME = "Hekim soyadı boş bırakılamaz!";
    /**
     * Ad alanı boş hata mesajı
     */
    private static final String ERROR_EMPTY_NAME = "Hekim adı boş bırakılamaz!";
    /**
     * TCKN alanı boş hata mesajı
     */
    private static final String ERROR_EMTPY_TCKN = "TC Kimlik Numarası boş bırakılamaz!";
    /**
     * TCKN tekrarlı veri hata mesajı
     */
    private static final String ERROR_DUPLICATE_ENTRY = "Girdiğiniz TC kimlik numarası ile kayıtlı bir başka hekim bulunmaktadır!";
    /**
     * Geçersiz eposta hata mesajı
     */
    private static final String ERROR_INVALID_EMAIL = "Girdiğiniz eposta adresi geçerli değil!";
    /**
     * Parola alanları uyuşmama hata mesajı
     */
    private static final String ERROR_PASSWORDS_DONT_MATCH = "Girdiğiniz parolalar birbiri ile uyuşmuyor!";
    /**
     * Geçersiz TCKN hata mesajı
     */
    private static final String ERROR_INVALID_TCKN = "TC Kimlik Numarası 11 haneli ve sayılardan oluşmalıdır!";

    /**
     * Selenium nesnesini ilkendirir ve login sayfasını açar
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        super.doAdministratorLogin();
        final String url = getFullAdminPanelUrl("doctor/doctorAdd.html");
        selenium.open(url);
        selenium.waitForPageToLoad(TIMEOUT_LIMIT);
        synchronized (selenium) {
            selenium.wait(2000l);
        }
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
        assertContainsBodyText(ERROR_EMTPY_TCKN);
        assertContainsBodyText(ERROR_EMPTY_NAME);
        assertContainsBodyText(ERROR_EMTPY_SURNAME);
        assertContainsBodyText(ERROR_EMPTY_PASSWORD);
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
        assertContainsBodyText(ERROR_INVALID_TCKN);
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
        typeInputMask("id=doctor-add:tckn", "23456789111");
        selenium.click("id=doctor-add:save");
        waitForErrorMessage();
        assertContainsBodyText(ERROR_PASSWORDS_DONT_MATCH);
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
        typeInputMask("id=doctor-add:tckn", "23456789111");
        selenium.click("id=doctor-add:save");
        waitForErrorMessage();
        assertContainsBodyText(ERROR_INVALID_EMAIL);
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
        typeInputMask("id=doctor-add:tckn", doctor.getTckn().toString());
        selenium.click("id=doctor-add:save");
        waitForErrorMessage();
        containsString(ERROR_DUPLICATE_ENTRY);
    }

    /**
     * Yönetici, hekim bilgilerini hatasız ve eksiksiz girer. Hekim veritabanına
     * kaydedilir.
     * 
     * @throws Exception
     */
    @Test
    public void saveDoctorSuccessfull() throws Exception {
        final long tckn = DoctorTestData.getNextTCKN();
        final String name = "New";
        final String surname = "Doctor";
        final String password = "password";
        selenium.type("id=doctor-add:name", name);
        selenium.type("id=doctor-add:surname", surname);
        selenium.type("id=doctor-add:email", "new@doctor.com");
        selenium.type("id=doctor-add:password", password);
        selenium.type("id=doctor-add:password2", password);
        selenium.click("//div[@id='doctor-add:title']/div[3]");
        selenium.click("//div[@id='doctor-add:title_panel']/div/ul/li[2]");
        selenium.type("id=doctor-add:province", "Testing");
        selenium.type("id=doctor-add:diploma", "None");
        selenium.type("id=doctor-add:webpage", "http://vipera.ozkan.info");
        selenium.type("id=doctor-add:phone", "+905555555");
        selenium.type("id=doctor-add:mobile", "+905557777777");
        selenium.type("id=doctor-add:phone", "+905555555555");
        typeInputMask("id=doctor-add:tckn", tckn + "");
        selenium.click("id=doctor-add:save");
        waitForInfoMessage();
        final String expected = String.format("%d - %s %s kaydedildi!", tckn,
                name, surname);
        assertContainsBodyText(expected);
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

    private void waitForErrorMessage() {
        waitForElement(".ui-messages-error");
    }

    private void waitForInfoMessage() {
        waitForElement(".ui-messages-info-summary");
    }
}
