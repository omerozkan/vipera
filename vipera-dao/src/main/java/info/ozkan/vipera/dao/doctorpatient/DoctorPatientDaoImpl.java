package info.ozkan.vipera.dao.doctorpatient;

import info.ozkan.vipera.business.doctorpatient.DoctorPatientManagerResult;
import info.ozkan.vipera.business.doctorpatient.DoctorPatientManagerStatus;
import info.ozkan.vipera.entities.Doctor;
import info.ozkan.vipera.entities.Patient;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Hekim hasta ilişkileri için veritabanı işlemi yapan dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorPatientDao")
public class DoctorPatientDaoImpl implements DoctorPatientDao {
    /**
     * Hekime göre hasta dönderen jql sorgusu
     */
    private static final String JQL_GET_PATIENTS_BY_DOCTOR = "from Patient p join fetch p.doctors d WHERE d.id = :id";
    /**
     * EntityManager
     */
    private EntityManager em;

    public DoctorPatientManagerResult addPatientToDoctor(final Doctor doctor,
            final Patient patient) {
        assignPatientToDoctor(patient, doctor);
        return createSuccessAssignResult(doctor, patient);
    }

    /**
     * Hekime hasta atar
     * 
     * @param patient
     *            Hekim
     * @param doctor
     *            Hasta
     */
    private void assignPatientToDoctor(final Patient patient,
            final Doctor doctor) {
        final List<Patient> patients = getPatientsByDoctorId(doctor.getId());
        patients.add(patient);
        doctor.setPatients(patients);
        em.merge(doctor);
    }

    /**
     * Hekime atanan hasta listesini dönderir
     * 
     * @param doctorId
     * @return
     */
    private List<Patient> getPatientsByDoctorId(final Long doctorId) {
        final Query query = em.createQuery(JQL_GET_PATIENTS_BY_DOCTOR);
        query.setParameter("id", doctorId);
        return query.getResultList();
    }

    /**
     * Başarılı atama sonuç nesnesi oluşturur
     * 
     * @param doctor
     *            Hekim
     * @param patient
     *            Hasta
     * @return
     */
    private DoctorPatientManagerResult createSuccessAssignResult(
            final Doctor doctor, final Patient patient) {
        final DoctorPatientManagerResult result = new DoctorPatientManagerResult();
        result.setStatus(DoctorPatientManagerStatus.SUCCESS);
        result.setDoctor(doctor);
        result.setPatient(patient);
        return result;
    }

    /**
     * @param em
     *            the em to set
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        em = entityManager;
    }

}
