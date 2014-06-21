package info.ozkan.vipera.views.doctor;

import static org.junit.Assert.assertEquals;
import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.business.notification.NotificationSettingFacade;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.NotificationSetting;
import info.ozkan.vipera.jsf.ViperaViewException;

import java.util.ArrayList;

import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * DoctorUpdateBean birim testleri
 * 
 * @author Ömer Özkan
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class DoctorUpdateBeanTest {
    /**
     * FacesContext
     */
    @Mock
    private FacesContext context;
    /**
     * İşletme katmanı
     */
    @Mock
    private DoctorFacade facade;
    /**
     * Hekim
     */
    private Doctor doctor;
    /**
     * Domain object
     */
    private final DoctorUpdateBean doctorUpdate = new DoctorUpdateBean();
    /**
     * Sonuç sınıfı
     */
    private final DoctorManagerResult result = new DoctorManagerResult();

    @Mock
    private NotificationSettingFacade notificationSettingFacade;

    /**
     * Test verilerini ilklendirir
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(FacesContext.class);
        PowerMockito.doReturn(context).when(FacesContext.class,
                "getCurrentInstance");
        doctor = DoctorTestData.getTestData(DoctorTestData.HOUSE);
        doctorUpdate.setDoctorFacade(facade);
        doctorUpdate.setNotificationSettingFacade(notificationSettingFacade);
        Mockito.when(notificationSettingFacade.getAll()).thenReturn(
                new ArrayList<NotificationSetting>());
        Mockito.when(facade.getById(doctor.getId())).thenReturn(result);
    }

    /**
     * Yönetici, hekimi düzenlerken eposta adresini geçersiz bir şekilde girer.
     * Güncelleme işlemi gerçekleşmez. Yöneticiye
     * "Girdiğiniz eposta adresi geçersiz!" hata mesajı görüntülenir.
     * 
     * @throws Exception
     */
    @Test
    public void updateWithInvalidEmail() throws Exception {
        setDoctor();
        setDoctorIdAndLoad();
        doctor.setEmail("invalidEmail");
        doctorUpdate.save();
        Mockito.verify(facade).getById(doctor.getId());
        Mockito.verify(context)
                .addMessage(null, DoctorUpdateBean.EMAIL_INVALID);
    }

    private void setDoctor() {
        result.addDoctor(doctor);
        result.setSuccess(true);
    }

    /**
     * Yönetici, kayıtlı olmayan bir hekimi düzenlemeye çalışır. 404 Hata
     * sayfasına yönlendirilir.
     * 
     * @throws Exception
     */
    @Test(expected = ViperaViewException.class)
    public void updateWithInvalidId() throws Exception {
        result.setSuccess(false);
        setDoctorIdAndLoad();
        Mockito.verify(facade).getById(doctor.getId());
    }

    /**
     * Yönetici, id bilgisi boş olan bir adres ile düzenleme sayfasına gider.
     * 404 hata sayfasına yönlendirilir.
     * 
     * @throws Exception
     */
    @Test(expected = ViperaViewException.class)
    public void updateWithEmptyId() throws Exception {
        doctorUpdate.setId(null);
        doctorUpdate.loadDoctor();
    }

    /**
     * Yönetici, hekim bilgilerini değiştirir ve kaydet butonuna tıklar.
     * Güncelleme işlemi başarı ile gerçekleşir. Yöneticiye
     * "<Hekim Tam Adı> Güncellendi!" mesajı gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void updateSuccessfullWithoutPassword() throws Exception {
        final DoctorManagerResult updateResult = createSuccessUpdateOperation();
        Mockito.when(facade.update(doctor)).thenReturn(updateResult);
        doctorUpdate.save();
        Mockito.verify(facade).getById(doctor.getId());
        Mockito.verify(facade).update(doctor);
        Mockito.verify(context).addMessage(null, DoctorUpdateBean.SUCCESS);
    }

    /**
     * Yönetici, hekim bilgilerini değiştirir. Parola alanını da değiştirir.
     * Kayet butonuna tıklar. Güncelleme işlemi başarı ile gerçekleşir.
     * Yöneticiye "<Hekim Tam Adı>" Güncellendi" mesajı gösterilir.
     * 
     * @throws Exception
     */
    @Test
    public void updateSuccessfullWithPassword() throws Exception {
        final DoctorManagerResult updateResult = createSuccessUpdateOperation();
        Mockito.when(facade.update(doctor)).thenReturn(updateResult);
        final String newPassword = "newPassword";
        doctorUpdate.setPassword(newPassword);
        doctorUpdate.setPassword2(newPassword);
        doctorUpdate.save();
        assertEquals(newPassword, doctor.getPassword());
        Mockito.verify(facade).getById(doctor.getId());
        Mockito.verify(facade).update(doctor);
        Mockito.verify(context).addMessage(null, DoctorUpdateBean.SUCCESS);
    }

    /**
     * Başarılı update işlemi için gereken işlemleri gerçekleştirir.
     * 
     * @return
     */
    private DoctorManagerResult createSuccessUpdateOperation() {
        setDoctor();
        setDoctorNameAndSurname();
        setDoctorIdAndLoad();
        final DoctorManagerResult updateResult = createSuccessManagerResult();
        return updateResult;
    }

    /**
     * İşlemin başarılı olmasını tanımlayan değerleri atar
     * 
     * @return
     */
    private DoctorManagerResult createSuccessManagerResult() {
        final DoctorManagerResult updateResult = new DoctorManagerResult();
        updateResult.setSuccess(true);
        return updateResult;
    }

    /**
     * DoctorID'si atar ve hekim yükleme işlemini gerçekleştirir.
     */
    private void setDoctorIdAndLoad() {
        doctorUpdate.setId(doctor.getId());
        doctorUpdate.loadDoctor();
    }

    /**
     * Hekimin adı ve soyadını tanımlar
     */
    private void setDoctorNameAndSurname() {
        doctor.setName("Editted");
        doctor.setSurname("Doctor");
    }

}
