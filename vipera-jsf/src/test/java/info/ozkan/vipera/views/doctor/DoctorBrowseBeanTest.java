package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.business.doctor.DoctorBrowseModel;
import info.ozkan.vipera.business.doctor.DoctorFacade;
import info.ozkan.vipera.business.doctor.DoctorManagerResult;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * DoctorBrowseBean sınıfının birim testleri
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorBrowseBeanTest {
	/**
	 * Test edilen sınıfın nesnesi
	 */
	DoctorBrowseBean doctorBrowse = new DoctorBrowseBean();

	/**
	 * Yönetici, TCKN girerek hekim arar. Sistemde kayıtlı bir doktor bulunur.
	 * İlgili doktor listelenir. TCKN girildiğinde diğer alanlara bakılmaz.
	 * 
	 * @throws Exception
	 */
	@Test
	public void searchDoctorByTCKN() throws Exception {
		final DoctorFacade facade = Mockito.mock(DoctorFacade.class);
		final Doctor doctor = DoctorTestData.getTestData(DoctorTestData.HOUSE);
		final DoctorBrowseModel model = new DoctorBrowseModel();

		final DoctorManagerResult result = new DoctorManagerResult();
		final List<Doctor> doctors = new ArrayList<Doctor>();
		doctors.add(doctor);
		result.setDoctors(doctors);
		Mockito.when(facade.search(model)).thenReturn(result);

		model.setTckn(doctor.getTckn());
		doctorBrowse.setDoctorFacade(facade);
		doctorBrowse.setModel(model);
		doctorBrowse.search();
		Assert.assertEquals(1, doctorBrowse.getResult().size());
		Assert.assertEquals(doctor, doctorBrowse.getResult().get(0));
		Mockito.verify(facade).search(model);
	}
}
