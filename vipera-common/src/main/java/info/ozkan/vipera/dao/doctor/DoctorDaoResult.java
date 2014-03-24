package info.ozkan.vipera.dao.doctor;

import info.ozkan.vipera.business.doctor.DoctorManagerError;

public class DoctorDaoResult {

	/**
	 * Hata
	 */
	private DoctorManagerError error;

	/**
	 * İşlem başarılı mı?
	 */
	private boolean success;

	/**
	 * @return the status
	 */
	public DoctorManagerError getError() {
		return error;
	}

	/**
	 * @param error
	 *            the status to set
	 */
	public void setError(final DoctorManagerError error) {
		this.error = error;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(final boolean success) {
		this.success = success;
	}

}
