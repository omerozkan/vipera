package info.ozkan.vipera.views.doctor;

import static org.junit.Assert.assertEquals;
import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerError;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Doktor ekleme bean birim test sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class DoctorAddBeanTest {
	/**
	 * Parola
	 */
	private static final String PASSWORD = "password";
	/**
	 * JSF tarafından kontrol edilen use case ler
	 * 
	 * 1. Bütün alanları boş girip kaydet butonuna basar. Zorunlu yerler hata
	 * olarak gösterilir.
	 * 
	 * 3. TCKN girilir fakat parola ve parola tekrar bilgisi eşleşmez. Parola
	 * alanları hatalı olarak gösterilir.
	 */
	/**
	 * Facescontext
	 */
	private FacesContext context;
	/**
	 * AddDoctor
	 */
	private DoctorAddBean addDoctorBean;

	/**
	 * Test verilerini hazırlama
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		context = Mockito.mock(FacesContext.class);
		PowerMockito.mockStatic(FacesContext.class);
		PowerMockito.doReturn(context).when(FacesContext.class,
		        "getCurrentInstance");
		addDoctorBean = new DoctorAddBean();
		final Doctor doctor = createValidDoctorObject();
		addDoctorBean.setPasswordConfirm(PASSWORD);
		addDoctorBean.setDoctor(doctor);
	}

	/**
	 * Geçerli bir doktor nesnesi oluşturur
	 * 
	 * @return
	 */
	private Doctor createValidDoctorObject() {
		final Doctor doctor = DoctorTestData.getTestData(DoctorTestData.HOUSE);
		addDoctorBean.setEnable(true);
		return doctor;
	}

	/**
	 * TCKN bilgisi hatalı girilir. İlgili hata mesajı gösterilir. NOT: TCKN
	 * geçerlilik kontrolü yapılmamaktadır. Sadece 11 hane olması kontrol
	 * edilmektedir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveWithWrongTCKN() throws Exception {
		addDoctorBean.getDoctor().setTckn(123L);
		addDoctorBean.save();
		verifyFacesMessage(DoctorAddBean.INVALID_TCKN);
	}

	private void verifyFacesMessage(final FacesMessage message) {
		Mockito.verify(context).addMessage(null, message);
	}

	/**
	 * TCKN girilir fakat parola ve parola tekrar bilgisi eşleşmez. Parola
	 * alanları hatalı olarak gösterilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveWithDifferentPasswords() throws Exception {
		addDoctorBean.setPasswordConfirm("differentPassword");
		addDoctorBean.save();
		verifyFacesMessage(DoctorAddBean.PASSWORDS_DONT_MATCH);
	}

	/**
	 * Zorunlu alanlarla birlikte eposta adresi yanlış formatta girilir. Eposta
	 * adresinizi yanlış girdiniz hatası gösterilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveWithWrongEmail() throws Exception {
		addDoctorBean.getDoctor().setEmail("invalidEmail");
		addDoctorBean.save();
		verifyFacesMessage(DoctorAddBean.EMAIL_INVALID);
	}

	/**
	 * TCKN ile kayıtlı sistemde bir hekim bulunmaktadır. Aynı TCKN ile iki ayrı
	 * hekim sisteme kaydedilemez. İlgili hata gösterilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveWithNoneUniqueTCKN() throws Exception {
		final DoctorManagerResult result = new DoctorManagerResult();
		result.setSuccess(false);
		result.addError(DoctorManagerError.TCKN_HAS_EXIST);
		initializeDoctorFacadeMock(result);
		addDoctorBean.save();
		verifyFacesMessage(DoctorAddBean.TCKN_HAS_EXIST);
	}

	private void initializeDoctorFacadeMock(final DoctorManagerResult result) {
		final DoctorFacade facade = Mockito.mock(DoctorFacade.class);
		Mockito.when(facade.add(addDoctorBean.getDoctor())).thenReturn(result);
		addDoctorBean.setDoctorFacade(facade);
	}

	/**
	 * Yönetici, hekim bilgilerini hatasız ve eksiksiz girer. Hekim veritabanına
	 * kaydedilir.
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveDoctorSuccessfull() throws Exception {
		final DoctorManagerResult result = new DoctorManagerResult();
		result.setSuccess(true);
		initializeDoctorFacadeMock(result);
		addDoctorBean.save();
		verifyFacesMessage(DoctorAddBean.SUCCESS);
	}

	/**
	 * Hekimin üyeliğinin aktifleştirilmesi ve pasifleştirilmesi durumunda
	 * doctor domain nesnesine işlenip işlenmediğini test eder
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkDoctorEnabled() throws Exception {
		addDoctorBean.setEnable(true);
		assertEquals(Integer.valueOf(1), addDoctorBean.getDoctor().getEnabled());
		addDoctorBean.setEnable(false);
		assertEquals(Integer.valueOf(0), addDoctorBean.getDoctor().getEnabled());
	}

}
