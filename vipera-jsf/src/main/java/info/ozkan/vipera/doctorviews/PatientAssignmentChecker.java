package info.ozkan.vipera.doctorviews;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientFacade;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

import java.util.List;

/**
 * Hastanın hekime atanıp atanmadığını kontrol eden utility sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public final class PatientAssignmentChecker {
    /**
     * private constructor
     */
    private PatientAssignmentChecker() {
    }

    /**
     * Hastanın, hekime atanıp atanmadığını test eder
     * 
     * @param doctorPatientFacade
     * @param patient
     * @return
     */
    public static boolean check(final DoctorPatientFacade doctorPatientFacade,
            final Patient patient) {
        final Doctor doctor = DoctorSessionBean.getDoctor();
        doctorPatientFacade.loadPatients(doctor);
        final List<Patient> list = doctor.getPatients();
        return list.contains(patient);
    }
}
