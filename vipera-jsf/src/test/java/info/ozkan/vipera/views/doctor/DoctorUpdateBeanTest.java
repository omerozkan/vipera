package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;

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

import com.sun.faces.context.FacesFileNotFoundException;

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
        result.addDoctor(doctor);
        result.setSuccess(true);
        doctorUpdate.setId(doctor.getId());
        doctorUpdate.loadDoctor();
        doctor.setEmail("invalidEmail");
        doctorUpdate.save();
        Mockito.verify(facade).getById(doctor.getId());
        Mockito.verify(context)
                .addMessage(null, DoctorUpdateBean.EMAIL_INVALID);
    }

    /**
     * Yönetici, kayıtlı olmayan bir hekimi düzenlemeye çalışır. 404 Hata
     * sayfasına yönlendirilir.
     * 
     * @throws Exception
     */
    @Test(expected = FacesFileNotFoundException.class)
    public void updateWithInvalidId() throws Exception {
        result.setSuccess(false);
        doctorUpdate.setId(doctor.getId());
        doctorUpdate.loadDoctor();
        Mockito.verify(facade).getById(doctor.getId());
    }

    /**
     * Yönetici, id bilgisi boş olan bir adres ile düzenleme sayfasına gider.
     * 404 hata sayfasına yönlendirilir.
     * 
     * @throws Exception
     */
    @Test(expected = FacesFileNotFoundException.class)
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
    public void updateSuccessfull() throws Exception {
        result.setSuccess(true);
        result.addDoctor(doctor);
        doctor.setName("Editted");
        doctor.setSurname("Doctor");
        doctorUpdate.setId(doctor.getId());
        doctorUpdate.loadDoctor();
        final DoctorManagerResult updateResult = new DoctorManagerResult();
        updateResult.setSuccess(true);
        Mockito.when(facade.update(doctor)).thenReturn(updateResult);
        doctorUpdate.save();
        Mockito.verify(facade).getById(doctor.getId());
        Mockito.verify(facade).update(doctor);
        Mockito.verify(context).addMessage(null, DoctorUpdateBean.SUCCESS);
    }
}
