package info.ozkan.vipera.selenium.doctor;

import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.selenium.AbstractAcceptanceTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * refs #41 Yönetici hekimleri düzenler
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorUpdateAcceptanceTest extends AbstractAcceptanceTest {
    private static final String UPDATED = "güncellendi!";
    private static final String NOT_FOUND = "404";
    private static final String PASSWORD_DONT_MATCH = "Girdiğiniz parolalar birbiri ile uyuşmuyor!";
    private static final String EMPTY_SURNAME = "Hekim soyadı boş bırakılamaz!";
    private static final String EMTPY_NAME = "Hekim adı boş bırakılamaz!";
    private static final String URL = "/doctor/update.html?id=";
    Doctor doctor = DoctorTestData.getTestData(DoctorTestData.DEMIRCI);

    @Before
    public void setUp() throws Exception {
        doAdministratorLogin();
        selenium.open(getFullAdminPanelUrl(URL + doctor.getId()));
        selenium.waitForPageToLoad("30000");
        waitForElements();
    }

    /**
     * Yönetici, hekimi düzenlerken ad ve soyad kısımlarını boş bırakır.
     * Güncelleme işlemi gerçekleşmez. Yöneticiye "Ad ve soyad boş olamaz!" hata
     * mesajı görüntülenir.
     * 
     * @throws Exception
     */
    @Test
    public void updateWithEmptyNameAndSurname() throws Exception {
        selenium.type("id=doctor-update:name", "");
        selenium.type("id=doctor-update:surname", "");
        selenium.click("id=doctor-update:save");
        waitForElements();
        assertContainsBodyText(EMTPY_NAME);
        assertContainsBodyText(EMPTY_SURNAME);
    }

    /**
     * Yönetici, hekimi düzenlerken eposta adresini geçersiz bir şekilde girer.
     * Güncelleme işlemi gerçekleşmez. Yöneticiye
     * "Girdiğiniz eposta adresi geçersiz!" hata mesajı görüntülenir.
     * 
     * 
     * @throws Exception
     */
    @Test
    public void updateWithInvalidEmail() throws Exception {
        selenium.type("id=doctor-update:email", "invalidEmail");
        selenium.click("id=doctor-update:save");
        waitForElements();
        assertContainsBodyText("Girdiğiniz eposta adresi geçersiz!");
    }

    /**
     * Yönetici, parola alanını girer fakat parola tekrarına farklı bir değer
     * girer. Yöneticiye "Parolalar birbiri ile uyuşmuyor!" hata mesajı
     * görüntülenir.
     * 
     * @throws Exception
     */
    @Test
    public void updateWithDifferentPasswords() throws Exception {
        selenium.click("id=doctor-update:password");
        selenium.type("id=doctor-update:password", "blabla");
        selenium.type("id=doctor-update:password2", "blablabla");
        selenium.click("id=doctor-update:save");
        waitForElements();
        assertContainsBodyText(PASSWORD_DONT_MATCH);
    }

    /**
     * Yönetici, parola tekrar alanına değer girer fakat parola alanını boş
     * bırakır. Yöneticiye "Parolalar birbiri ile uyuşmuyor!" hata mesajı
     * gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void updateWithNonemptyPassword2() throws Exception {
        selenium.type("id=doctor-update:password2", "parola");
        selenium.click("id=doctor-update:save");
        waitForElements();
        assertContainsBodyText(PASSWORD_DONT_MATCH);
    }

    /**
     * Yönetici, kayıtlı olmayan bir hekimi düzenlemeye çalışır. 404 Hata
     * sayfasına yönlendirilir.
     * 
     * @throws Exception
     */
    @Test
    public void openPageWithInvalidId() throws Exception {
        selenium.open(URL + "123123123123");
        assertContainsBodyText(NOT_FOUND);
    }

    /**
     * Yönetici, id bilgisi boş olan bir adres ile düzenleme sayfasına gider.
     * 404 hata sayfasına yönlendirilir.
     * 
     * @throws Exception
     */
    @Test
    public void openPageWithEmptyId() throws Exception {
        selenium.open(URL);
        assertContainsBodyText(NOT_FOUND);
    }

    /**
     * Yönetici, hekim bilgilerini değiştirir. Parola alanına dokunmaz ve kaydet
     * butonuna tıklar. Güncelleme işlemi başarı ile gerçekleşir. Yöneticiye
     * "<Hekim Tam Adı> Güncellendi!" mesajı gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void updateSuccessfullyWithoutPassword() throws Exception {
        final String newName = "Ismail Mehmet";
        selenium.type("id=doctor-update:name", newName);
        selenium.click("id=doctor-update:save");
        waitForElements();
        assertContainsBodyText(newName);
    }

    /**
     * Yönetici, hekim bilgilerini değiştirir. Parola alanını da değiştirir.
     * Kayet butonuna tıklar. Güncelleme işlemi başarı ile gerçekleşir.
     * Yöneticiye "<Hekim Tam Adı>" Güncellendi" mesajı gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void updateSuccesfullWithPassword() throws Exception {
        selenium.click("id=doctor-update:password");
        selenium.type("id=doctor-update:password", "parola");
        selenium.type("id=doctor-update:password2", "parola");
        selenium.click("id=doctor-update:save");
        waitForElements();
        assertContainsBodyText(UPDATED);
    }

    @After
    public void tearDown() {
        stopSelenium();
    }
}
