package info.ozkan.vipera.business.patient;

import info.ozkan.vipera.dao.patient.PatientDao;
import info.ozkan.vipera.doctor.PatientTestData;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.test.IntegrationTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

/**
 * PatientDao entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class PatientDaoIntegrationTest extends IntegrationTest {
    /**
     * Dao nesnesi
     */
    @Inject
    private PatientDao patientDao;

    /**
     * Sistemde aynı doğum tarihine sahip iki hasta vardır. Sorgulama sonucunda
     * iki hasta yer alır.
     * 
     * @throws Exception
     */
    @Test
    public void findPatients() throws Exception {
        final Patient marvin = PatientTestData
                .getTestData(PatientTestData.MARVIN);
        final Patient sam = PatientTestData.getTestData(PatientTestData.SAM);
        final String birthYear = getBirthYearFromPatient(marvin);
        final PatientSearchFilter filter = new PatientSearchFilter();
        filter.addFilter(Patient.BIRTH_DATE, birthYear);
        final PatientManagerResult result = patientDao.find(filter);
        Assert.assertTrue(result.getPatients().contains(marvin));
        Assert.assertTrue(result.getPatients().contains(sam));
    }

    /**
     * Hastanın doğum tarihini ayıklayarak yıla dönüştürür
     * 
     * @param marvin
     * @return
     */
    private String getBirthYearFromPatient(final Patient marvin) {
        final Date birthDate = marvin.getBirthDate();
        Assert.assertTrue(birthDate.equals(marvin.getBirthDate()));
        final SimpleDateFormat format = new SimpleDateFormat("yyyy");
        final String birthYear = format.format(birthDate);
        return birthYear;
    }
}
