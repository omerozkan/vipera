package info.ozkan.vipera.dao.doctor;

import info.ozkan.vipera.business.doctor.DoctorManagerError;
import info.ozkan.vipera.entities.Doctor;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Hekim veritabanı üzerinde işlem yapan Dao sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorDao")
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
		System.out.println("---------------KAYDEDIYORUM---------------");
		em.persist(doctor);
		result.setSuccess(true);
		return result;
	}

	/**
	 * Persistence nesne
	 * 
	 * @param em
	 */
	@PersistenceContext
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