package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.dao.doctor.DoctorDao;
import info.ozkan.vipera.dao.doctor.DoctorDaoResult;
import info.ozkan.vipera.entities.Doctor;

/**
 * Hekimler üzerinde işlem yapan işletme katmanı sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class DoctorManagerImpl implements DoctorManager {
	/**
	 * Veritabanı işlemleri
	 */
	private DoctorDao doctorDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.ozkan.vipera.business.doctor.DoctorManager#add(info.ozkan.vipera
	 * .entities.Doctor)
	 */
	public DoctorManagerResult add(final Doctor doctor) {
		final DoctorDaoResult daoResult = doctorDao.add(doctor);
		final DoctorManagerResult result = new DoctorManagerResult();
		if (!daoResult.isSuccess()) {
			result.addError(daoResult.getError());
			result.setSuccess(false);
		} else {
			result.setSuccess(true);
		}
		return result;
	}

	/**
	 * @param doctorDao
	 *            the doctorDao to set
	 */
	public void setDoctorDao(final DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

}
