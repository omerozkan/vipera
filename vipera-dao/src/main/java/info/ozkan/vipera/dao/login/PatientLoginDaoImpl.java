package info.ozkan.vipera.dao.login;

import info.ozkan.vipera.business.login.PatientLoginResult;
import info.ozkan.vipera.business.login.PatientLoginStatus;
import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Patient;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * {@link PatientLoginDao} implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientLoginDao")
public class PatientLoginDaoImpl implements PatientLoginDao {
    private static final String JQL_GET_BY_TCKN =
            "from Patient p WHERE p.tckn = :tckn AND enable = :enabled";
    /**
     * persistence context
     */
    private EntityManager em;

    public PatientLoginResult find(final Long tckn, final String password) {
        final PatientLoginResult result = new PatientLoginResult();
        final Query query = em.createQuery(JQL_GET_BY_TCKN);
        query.setParameter("tckn", tckn);
        query.setParameter("enabled", Authorize.ENABLE);
        final List<Patient> list = query.getResultList();

        if (list.size() == 0) {
            result.setStatus(PatientLoginStatus.INVALID_USERNAME);
        } else {
            final Patient patient = list.get(0);
            if (patient.getPassword().equals(password)) {
                result.setStatus(PatientLoginStatus.SUCCESS);
                result.setPatient(patient);
            } else {
                result.setStatus(PatientLoginStatus.INVALID_PASSWORD);
            }
        }
        return result;
    }

    /**
     * @param entityManager
     *            the entityManager to set
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        em = entityManager;
    }

}
