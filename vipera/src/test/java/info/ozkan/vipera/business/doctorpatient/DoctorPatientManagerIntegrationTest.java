package info.ozkan.vipera.business.doctorpatient;

import info.ozkan.vipera.business.doctor.DoctorManager;
import info.ozkan.vipera.business.patient.PatientManager;
import info.ozkan.vipera.doctor.DoctorTestData;
import info.ozkan.vipera.doctor.PatientTestData;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;
import info.ozkan.vipera.test.IntegrationTest;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

public class DoctorPatientManagerIntegrationTest extends IntegrationTest {
    /**
     * DoctorDao
     */
    @Inject
    private DoctorManager doctorManager;
    /**
     * PatientDao
     */
    @Inject
    private PatientManager patientManager;
    /**
     * doctorPatientDao
     */
    @Inject
    private DoctorPatientManager doctorPatientManager;
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
        final Doctor house = doctorManager.getById(doctorTest.getId())
                .getDoctor();
        final Patient marvin = patientManager.getById(patientTest.getId())
                .getPatient();
        final DoctorPatientManagerResult result = doctorPatientManager.assign(
                house, marvin);
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
