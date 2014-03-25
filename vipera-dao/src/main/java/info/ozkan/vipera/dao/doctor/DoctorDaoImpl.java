/**
 * 
 */
package info.ozkan.vipera.dao.doctor;

import info.ozkan.vipera.business.doctor.DoctorManagerError;
import info.ozkan.vipera.entities.Doctor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

/**
 * Hekim veritabanı üzerinde işlem yapan Dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorDaoImpl implements DoctorDao {
	protected static final String TCKN = "tckn";
	protected static final String JQL_GET_BY_TCKN = "from Doctor d where d.tckn = :tckn";
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

	/**
	 * Veritabanından TCKN'ye ait hekimi sorgular
	 * 
	 * @param tckn
	 *            TC Kimlik No
	 * @return Doctor nesnesi
	 */
	public DoctorDaoResult get(final Long tckn) {
		final Query query = em.createQuery(JQL_GET_BY_TCKN);
		query.setParameter(TCKN, tckn);
		final DoctorDaoResult result = new DoctorDaoResult();
		try {
			final Doctor doctor = (Doctor) query.getSingleResult();
			result.setSuccess(true);
			result.setDoctor(doctor);
		} catch (final NoResultException e) {
			result.setSuccess(false);
			result.setError(DoctorManagerError.DOCTOR_NOT_EXIST);
		}
		return result;
	}

}