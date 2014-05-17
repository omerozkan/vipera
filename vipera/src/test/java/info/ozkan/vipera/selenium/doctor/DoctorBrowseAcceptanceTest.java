package info.ozkan.vipera.selenium.doctor;

import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.selenium.AbstractAcceptanceTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * refs #39 Yönetici hekimleri listeler
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorBrowseAcceptanceTest extends AbstractAcceptanceTest {
    /**
     * Sonuç olmadığında gösterilecek mesaj
     */
    private static final String NO_RESULT = "Aradığınız kriterlere uygun sonuç bulunamadı.";
    /**
     * Test edilecek olan sayfa
     */
    private static final String URL = "/doctor/doctorBrowse.html";
    /**
     * Veritabanında kayıtlı test hekimi
     */
    private final Doctor house = DoctorTestData
            .getTestData(DoctorTestData.HOUSE);
    /**
     * Veritabanında kayıtlı test hekimi
     */
    private final Doctor demirci = DoctorTestData
            .getTestData(DoctorTestData.DEMIRCI);

    /**
     * Login işlemi yapar ilgili sayfayı açarak sayfanın yüklenmesini bekler
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        doAdministratorLogin();
        selenium.open(getFullAdminPanelUrl(URL));
        waitForElements();
    }

    /**
     * Yönetici, TCKN girerek hekim arar. Sistemde kayıtlı bir doktor bulunur.
     * İlgili doktor listelenir. TCKN girildiğinde diğer alanlara bakılmaz.
     * 
     * @throws Exception
     */
    @Test
    public void searchByTCKN() throws Exception {
        typeInputMask("id=doctor-browse:tckn", house.getTckn().toString());
        selenium.click("id=doctor-browse:search");
        waitForElements();
        assertContainsBodyText(house.getFullname());
    }

    /**
     * Yönetici, hekim adına "Greg" yazar. Gregory House sonuç listesinde yer
     * alır.
     * 
     * @throws Exception
     */
    @Test
    public void searchByName() throws Exception {
        selenium.type("id=doctor-browse:name", house.getName().substring(0, 4));
        selenium.click("id=doctor-browse:search");
        waitForElements();
        assertContainsBodyText(house.getFullname());
    }

    /**
     * Yönetici, hekim soyadına "Dem" yazar. "Ismail Demirci" sonuç listesinde
     * yer alır.
     * 
     * @throws Exception
     */
    @Test
    public void searchBySurname() throws Exception {
        selenium.type("id=doctor-browse:surname", demirci.getSurname()
                .substring(0, 3));
        selenium.click("id=doctor-browse:search");
        waitForElements();
        assertContainsBodyText(demirci.getFullname());
    }

    /**
     * Yönetici, eposta adresi alanına "ismail@drdemirci.com" girer
     * "Ismail Demirci" listede yer alır.
     * 
     * @throws Exception
     */
    @Test
    public void searchByEmail() throws Exception {
        selenium.type("id=doctor-browse:email", demirci.getEmail());
        selenium.click("id=doctor-browse:search");
        waitForElements();
        assertContainsBodyText(demirci.getFullname());
    }

    /**
     * Yönetici, ünvana "Uzman Doktor" değerini girer. House ve Demirci hekim
     * listede yer alır.
     * 
     * @throws Exception
     */
    @Test
    public void searchByTitle() throws Exception {
        selenium.click("id=doctor-browse:title_label");
        selenium.click("//div[@id='doctor-browse:title_panel']/div/ul/li[4]");
        selenium.click("id=doctor-browse:search");
        waitForElements();
        assertContainsBodyText(house.getFullname());
        assertContainsBodyText(demirci.getFullname());
    }

    /**
     * Yönetici, sistemde kayıtlı olmayan bir TCKN girer sistemde bir hekim
     * bulunamaz. sonuç yok mesajı gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void searchByTCKNAndGetNoResult() throws Exception {
        final String tckn = DoctorTestData.getNextTCKN() + "";
        typeInputMask("id=doctor-browse:tckn", tckn);
        selenium.click("id=doctor-browse:search");
        waitForElements();
        assertContainsBodyText(NO_RESULT);
    }

    /**
     * Yönetici, Prof. ünvanına sahip hekimleri listelemek ister, sistemde
     * kayıtlı hekim bulunmaz. Sonuç yok mesajı gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void searchProfessorAndGetNoResult() throws Exception {
        selenium.click("id=doctor-browse:title_label");
        selenium.click("//div[@id='doctor-browse:title_panel']/div/ul/li[8]");
        selenium.click("id=doctor-browse:search");
        waitForElements();
        assertContainsBodyText(NO_RESULT);
    }

    /**
     * Selenium durdumaca
     */
    @After
    public void tearDown() {
        stopSelenium();
    }

}
