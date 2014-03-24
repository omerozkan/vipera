/**
 * 
 */
package info.ozkan.vipera.dao.doctor;

import info.ozkan.vipera.business.doctor.DoctorManagerError;
import info.ozkan.vipera.entities.Doctor;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

/**
 * Hekim veritabanı üzerinde işlem yapan Dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorDaoImpl implements DoctorDao {
	/**
	 * Persistence nesne
	 */
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.ozkan.vipera.dao.doctor.DoctorDao#add(info.ozkan.vipera
	 * .entities.Doctor)
	 */
	public DoctorDaoResult add(final Doctor doctor) {
		final DoctorDaoResult result = new DoctorDaoResult();
		try {
			em.persist(doctor);
			result.setSuccess(true);
		} catch (final ConstraintViolationException e) {
			result.setSuccess(false);
			result.setError(DoctorManagerError.TCKN_HAS_EXIST);
		}
		return result;
	}

	/**
	 * Persistence nesne
	 * 
	 * @param em
	 */
	public void setEntityManager(final EntityManager em) {
		this.em = em;
	}

}