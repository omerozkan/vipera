package info.ozkan.vipera.dao.login;

import info.ozkan.vipera.business.login.DoctorLoginResult;
import info.ozkan.vipera.business.login.DoctorLoginStatus;
import info.ozkan.vipera.entities.Authorize;
import info.ozkan.vipera.entities.Doctor;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * {@link DoctorLoginDao} implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorLoginDao")
public class DoctorLoginDaoImpl implements DoctorLoginDao {
    /**
     * EntityManager
     */
    private EntityManager em;

    public DoctorLoginResult find(final Long tckn, final String password) {
        final DoctorLoginResult result = new DoctorLoginResult();
        final Query query = em
                .createQuery("from Doctor d WHERE d.tckn = :tckn AND enabled = :enabled");
        query.setParameter("tckn", tckn);
        query.setParameter("enabled", Authorize.ENABLE);
        final List<Doctor> list = query.getResultList();

        if (list.size() == 0) {
            result.setStatus(DoctorLoginStatus.INVALID_USERNAME);
        } else {
            final Doctor doctor = list.get(0);
            if (doctor.getPassword().equals(password)) {
                result.setStatus(DoctorLoginStatus.SUCCESS);
                result.setDoctor(doctor);
            } else {
                result.setStatus(DoctorLoginStatus.INVALID_PASSWORD);
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
