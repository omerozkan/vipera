package info.ozkan.vipera.dao.doctorpatient;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientManagerResult;
import info.ozkan.vipera.business.doctorpatient.DoctorPatientManagerStatus;
import info.ozkan.vipera.dao.doctor.DoctorDao;
import info.ozkan.vipera.dao.doctorpatient.DoctorPatientDao;
import info.ozkan.vipera.dao.patient.PatientDao;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.doctor.PatientTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.test.IntegrationTest;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

/**
 * DoctorPatientDao entegrasyon testi
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorPatientDaoIntegrationTest extends IntegrationTest {
    /**
     * DoctorDao
     */
    @Inject
    private DoctorDao doctorDao;
    /**
     * PatientDao
     */
    @Inject
    private PatientDao patientDao;
    /**
     * doctorPatientDao
     */
    @Inject
    private DoctorPatientDao doctorPatientDao;
    /**
     * Hasta test verisi
     */
    private final Doctor doctorTest = DoctorTestData
            .getTestData(DoctorTestData.HOUSE);
    /**
     * Hekim test verisi
     */
    private final Patient patientTest = PatientTestData
            .getTestData(PatientTestData.MARVIN);

    /**
     * Veritabanından bir hekim ve hasta sorgulanır. Hekim'e hast atanır. Tekrar
     * sorgulanır ve hekimin hasta listesinde hasta yer alır.
     * 
     * @throws Exception
     */
    @Test
    public void assignPatientToDoctorSuccessfull() throws Exception {
        final Doctor house = doctorDao.getById(doctorTest.getId()).getDoctor();
        final Patient marvin = patientDao.getById(patientTest.getId())
                .getPatient();
        final DoctorPatientManagerResult result = doctorPatientDao
                .addPatientToDoctor(house, marvin);
        assertDoctorPatient(marvin, result);
    }

    /**
     * Atama işleminin başarılı olduğunu ve hastanın hekim hasta listesinde yer
     * aldığını test eder
     * 
     * @param patient
     * @param result
     */
    private void assertDoctorPatient(final Patient patient,
            final DoctorPatientManagerResult result) {
        final DoctorPatientManagerStatus status = result.getStatus();
        final List<Patient> patients = result.getDoctor().getPatients();
        Assert.assertTrue(status.equals(DoctorPatientManagerStatus.SUCCESS));
        Assert.assertTrue(patients.contains(patient));
    }
}
