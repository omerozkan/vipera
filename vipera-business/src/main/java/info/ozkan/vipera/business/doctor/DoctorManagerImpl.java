package info.ozkan.vipera.business.doctor;

import info.ozkan.vipera.dao.doctor.DoctorDao;
import info.ozkan.vipera.dao.doctor.DoctorDaoResult;
import info.ozkan.vipera.entities.Doctor;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

/**
 * Hekimler üzerinde işlem yapan işletme katmanı sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorManager")
public class DoctorManagerImpl implements DoctorManager {
	/**
	 * Veritabanı işlemleri
	 */
	@Inject
	private DoctorDao doctorDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.ozkan.vipera.business.doctor.DoctorManager#add(info.ozkan.vipera
	 * .entities.Doctor)
	 */
	@Transactional
	public DoctorManagerResult add(final Doctor doctor) {
		final DoctorManagerResult result = new DoctorManagerResult();
		final DoctorDaoResult daoResult = doctorDao.add(doctor);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.ozkan.vipera.business.doctor.DoctorManager#get(Long)
	 */
	public DoctorManagerResult get(final Long tckn) {
		final DoctorDaoResult daoResult = doctorDao.get(tckn);
		final DoctorManagerResult result = new DoctorManagerResult();
		result.setDoctor(daoResult.getDoctor());
		result.setSuccess(daoResult.isSuccess());
		result.addError(daoResult.getError());
		return result;
	}

}
